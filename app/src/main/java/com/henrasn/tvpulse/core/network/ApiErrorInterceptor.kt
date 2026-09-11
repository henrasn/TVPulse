package com.henrasn.tvpulse.core.network

import com.henrasn.tvpulse.core.error.ApiException
import com.henrasn.tvpulse.data.model.dto.common.ErrorResponse
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.Response

class ApiErrorInterceptor : Interceptor {
    private val json = Json { ignoreUnknownKeys = true }

    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())
        if (!response.isSuccessful) {
            val errorResponse = response.body.string().let { body ->
                try {
                    json.decodeFromString<ErrorResponse>(body)
                } catch (e: Exception) {
                    null
                }
            }

            response.close()
            throw ApiException(
                httpCode = response.code,
                statusCode = errorResponse?.code ?: -1,
                httpMessage = errorResponse?.message
            )
        }

        return response
    }
}