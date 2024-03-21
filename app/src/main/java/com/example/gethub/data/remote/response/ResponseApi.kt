package com.example.gethub.data.remote.response

sealed class ResponseApi<R> (val data: R? = null, val message: String? = null) {

    class Success<T>(data: T): ResponseApi<T>(data)
    class Error<T>(message: String?): ResponseApi<T>(message = message)
    class Loading<T>: ResponseApi<T>()
}