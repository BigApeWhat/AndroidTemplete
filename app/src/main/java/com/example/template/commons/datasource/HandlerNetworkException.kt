package com.example.template.commons.datasource

import com.example.template.commons.exceptions.BadRequestException
import com.example.template.commons.exceptions.GenericNetworkException
import com.example.template.commons.exceptions.NetworkConnectionException
import retrofit2.HttpException
import java.io.IOException
import java.lang.Exception
import java.net.UnknownHostException

fun handleNetworkException(ex: Exception): Exception {
    return when (ex) {
        is IOException -> NetworkConnectionException()
        is UnknownHostException -> NetworkConnectionException()
        is HttpException -> apiErrorFromCodeException(ex.code())
        else -> GenericNetworkException()
    }
}

private fun apiErrorFromCodeException(code: Int): Exception {
    return if (code == 400) {
        BadRequestException()
    } else {
        GenericNetworkException()
    }
}