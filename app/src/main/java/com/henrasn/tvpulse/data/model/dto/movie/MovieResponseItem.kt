package com.henrasn.tvpulse.data.model.dto.movie

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieResponseItem(

    @SerialName("summary")
    val summary: String? = null,

    @SerialName("image")
    val image: Image? = null,

    @SerialName("averageRuntime")
    val averageRuntime: Int? = null,

    @SerialName("dvdCountry")
    val dvdCountry: DvdCountry? = null,

    @SerialName("_links")
    val links: Links? = null,

    @SerialName("premiered")
    val premiered: String? = null,

    @SerialName("rating")
    val rating: Rating? = null,

    @SerialName("runtime")
    val runtime: Int? = null,

    @SerialName("weight")
    val weight: Int? = null,

    @SerialName("language")
    val language: String? = null,

    @SerialName("type")
    val type: String? = null,

    @SerialName("url")
    val url: String? = null,

    @SerialName("officialSite")
    val officialSite: String? = null,

    @SerialName("network")
    val network: Network? = null,

    @SerialName("schedule")
    val schedule: Schedule? = null,

    @SerialName("webChannel")
    val webChannel: WebChannel? = null,

    @SerialName("genres")
    val genres: List<String?>? = null,

    @SerialName("name")
    val name: String? = null,

    @SerialName("ended")
    val ended: String? = null,

    @SerialName("id")
    val id: Int,

    @SerialName("externals")
    val externals: Externals? = null,

    @SerialName("updated")
    val updated: Int? = null,

    @SerialName("status")
    val status: String? = null
)

@Serializable
data class DvdCountry(

    @SerialName("code")
    val code: String? = null,

    @SerialName("timezone")
    val timezone: String? = null,

    @SerialName("name")
    val name: String? = null
)

@Serializable
data class Links(

    @SerialName("self")
    val self: Self? = null,

    @SerialName("previousepisode")
    val previousepisode: Previousepisode? = null
)

@Serializable
data class Country(

    @SerialName("code")
    val code: String? = null,

    @SerialName("timezone")
    val timezone: String? = null,

    @SerialName("name")
    val name: String? = null
)

@Serializable
data class Network(

    @SerialName("country")
    val country: Country? = null,

    @SerialName("name")
    val name: String? = null,

    @SerialName("id")
    val id: Int? = null,

    @SerialName("officialSite")
    val officialSite: String? = null
)

@Serializable
data class Schedule(

    @SerialName("days")
    val days: List<String?>? = null,

    @SerialName("time")
    val time: String? = null
)

@Serializable
data class Externals(

    @SerialName("thetvdb")
    val thetvdb: Int? = null,

    @SerialName("imdb")
    val imdb: String? = null,

    @SerialName("tvrage")
    val tvrage: Int? = null
)

@Serializable
data class Previousepisode(

    @SerialName("name")
    val name: String? = null,

    @SerialName("href")
    val href: String? = null
)

@Serializable
data class WebChannel(

    @SerialName("country")
    val country: Country? = null,

    @SerialName("name")
    val name: String? = null,

    @SerialName("id")
    val id: Int? = null,

    @SerialName("officialSite")
    val officialSite: String? = null
)

@Serializable
data class Self(

    @SerialName("href")
    val href: String? = null
)

@Serializable
data class Rating(

    @SerialName("average")
    val average: Float? = null
)

@Serializable
data class Image(

    @SerialName("original")
    val original: String? = null,

    @SerialName("medium")
    val medium: String? = null
)
