package com.henrasn.tvpulse.data.model.dto.common

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ErrorResponse(

    @SerialName("code")
    val code: Int? = null,

    @SerialName("name")
    val name: String? = null,

    @SerialName("message")
    val message: String? = null,

    @SerialName("status")
    val status: Int? = null
)
