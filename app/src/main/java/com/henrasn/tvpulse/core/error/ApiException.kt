package com.henrasn.tvpulse.core.error

import java.io.IOException

class ApiException(
    val httpCode: Int,
    val statusCode: Int,
    val httpMessage: String?
) : IOException("$httpCode:$statusCode:$httpMessage")