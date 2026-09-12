package com.henrasn.tvpulse.data.repository

import com.henrasn.tvpulse.core.network.NetworkResult
import com.henrasn.tvpulse.data.model.dto.movie.MovieResponseItem
import com.henrasn.tvpulse.data.model.dto.movie.SearchMovieResponse
import com.henrasn.tvpulse.data.model.entity.MovieEntity
import com.henrasn.tvpulse.data.source.local.MovieLocalDataSource
import com.henrasn.tvpulse.data.source.remote.MovieDataSource
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class MovieRepositoryImplTest {

    private val movieDataSource: MovieDataSource = mockk()
    private val localDataSource: MovieLocalDataSource = mockk()
    private val dispatcher: CoroutineDispatcher = Dispatchers.Unconfined
    private val repository = MovieRepositoryImpl(movieDataSource, localDataSource, dispatcher)

    private val sampleMovieResponseItem = MovieResponseItem(
        id = 1,
        name = "Test Movie"
    )

    private val sampleSearchMovieResponse = SearchMovieResponse(
        score = 1.0f,
        show = sampleMovieResponseItem
    )

    private val sampleMovieEntity = MovieEntity(
        id = 1,
        title = "Test Movie",
        image = "http://image.com/medium.jpg",
        rate = 8.5f,
        genre = "Action, Drama"
    )

    @Test
    fun `getMovies returns success with mapped list on successful response`() = runTest {
        coEvery { movieDataSource.getMovies() } returns NetworkResult.Success(
            listOf(
                sampleMovieResponseItem
            )
        )

        val mapper = { item: MovieResponseItem -> item.name!! }
        val result = repository.getMovies(mapper).first()

        assertTrue(result.isSuccess)
        assertEquals(listOf("Test Movie"), result.getOrNull())
        coVerify { movieDataSource.getMovies() }
    }

    @Test
    fun `getMovies returns failure on network failure`() = runTest {
        val exception = RuntimeException("Network error")
        coEvery { movieDataSource.getMovies() } returns NetworkResult.Failure(exception)

        val mapper = { item: MovieResponseItem -> item.name!! }
        val result = repository.getMovies(mapper).first()

        assertTrue(result.isFailure)
        assertEquals(exception, result.exceptionOrNull())
        coVerify { movieDataSource.getMovies() }
    }

    @Test
    fun `getMovies limits results to 30 items`() = runTest {
        val movies = List(35) { i -> sampleMovieResponseItem.copy(id = i + 1, name = "Movie $i") }
        coEvery { movieDataSource.getMovies() } returns NetworkResult.Success(movies)

        val mapper = { item: MovieResponseItem -> item.name!! }
        val result = repository.getMovies(mapper).first()

        assertTrue(result.isSuccess)
        assertEquals(30, result.getOrNull()?.size)
        coVerify { movieDataSource.getMovies() }
    }

    @Test
    fun `searchMovies returns success with mapped list on successful response`() = runTest {
        coEvery { movieDataSource.searchMovie("test") } returns NetworkResult.Success(
            listOf(
                sampleSearchMovieResponse
            )
        )

        val mapper = { item: MovieResponseItem -> item.name!! }
        val result = repository.searchMovies("test", mapper).first()

        assertTrue(result.isSuccess)
        assertEquals(listOf("Test Movie"), result.getOrNull())
        coVerify { movieDataSource.searchMovie("test") }
    }

    @Test
    fun `searchMovies filters out null shows and maps remaining`() = runTest {
        val searchResponses = listOf(
            SearchMovieResponse(score = 1.0f, show = sampleMovieResponseItem),
            SearchMovieResponse(score = 0.5f, show = null),
            SearchMovieResponse(
                score = 0.8f,
                show = sampleMovieResponseItem.copy(id = 2, name = "Another Movie")
            )
        )
        coEvery { movieDataSource.searchMovie("test") } returns NetworkResult.Success(
            searchResponses
        )

        val mapper = { item: MovieResponseItem -> item.name!! }
        val result = repository.searchMovies("test", mapper).first()

        assertTrue(result.isSuccess)
        assertEquals(listOf("Test Movie", "Another Movie"), result.getOrNull())
        coVerify { movieDataSource.searchMovie("test") }
    }

    @Test
    fun `searchMovies returns failure on network failure`() = runTest {
        val exception = RuntimeException("Network error")
        coEvery { movieDataSource.searchMovie("test") } returns NetworkResult.Failure(exception)

        val mapper = { item: MovieResponseItem -> item.name!! }
        val result = repository.searchMovies("test", mapper).first()

        assertTrue(result.isFailure)
        assertEquals(exception, result.exceptionOrNull())
        coVerify { movieDataSource.searchMovie("test") }
    }

    @Test
    fun `getDetailMovie returns success with mapped item on successful response`() = runTest {
        coEvery { movieDataSource.getDetailMovie(1) } returns NetworkResult.Success(
            sampleMovieResponseItem
        )

        val mapper = { item: MovieResponseItem -> item.name!! }
        val result = repository.getDetailMovie(1, mapper).first()

        assertTrue(result.isSuccess)
        assertEquals("Test Movie", result.getOrNull())
        coVerify { movieDataSource.getDetailMovie(1) }
    }

    @Test
    fun `getDetailMovie returns failure on network failure`() = runTest {
        val exception = RuntimeException("Network error")
        coEvery { movieDataSource.getDetailMovie(1) } returns NetworkResult.Failure(exception)

        val mapper = { item: MovieResponseItem -> item.name!! }
        val result = repository.getDetailMovie(1, mapper).first()

        assertTrue(result.isFailure)
        assertEquals(exception, result.exceptionOrNull())
        coVerify { movieDataSource.getDetailMovie(1) }
    }

    @Test
    fun `getFavoriteMovie returns mapped list from local data source`() = runTest {
        every { localDataSource.getAllMovies() } returns flowOf(listOf(sampleMovieEntity))

        val mapper = { entity: MovieEntity -> entity.title }
        val result = repository.getFavoriteMovie(mapper).first()

        assertEquals(listOf("Test Movie"), result)
        verify { localDataSource.getAllMovies() }
    }

    @Test
    fun `getFavoriteMovie returns empty list when no favorites`() = runTest {
        every { localDataSource.getAllMovies() } returns flowOf(emptyList())

        val mapper = { entity: MovieEntity -> entity.title }
        val result = repository.getFavoriteMovie(mapper).first()

        assertTrue(result.isEmpty())
        verify { localDataSource.getAllMovies() }
    }

    @Test
    fun `isFavorite delegates to local data source`() = runTest {
        every { localDataSource.isFavorite(1) } returns flowOf(true)

        val result = repository.isFavorite(1).first()

        assertTrue(result)
        verify { localDataSource.isFavorite(1) }
    }

    @Test
    fun `deleteMovie delegates to local data source`() = runTest {
        coEvery { localDataSource.deleteMovie(1) } returns Unit

        repository.deleteMovie(1)

        coVerify { localDataSource.deleteMovie(1) }
    }

    @Test
    fun `addFavorite delegates to local data source`() = runTest {
        coEvery { localDataSource.insertMovie(sampleMovieEntity) } returns Unit

        repository.addFavorite(sampleMovieEntity)

        coVerify { localDataSource.insertMovie(sampleMovieEntity) }
    }
}