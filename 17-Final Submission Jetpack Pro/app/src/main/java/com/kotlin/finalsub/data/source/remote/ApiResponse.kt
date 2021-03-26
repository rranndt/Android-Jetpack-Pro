package com.kotlin.finalsub.data.source.remote

import com.kotlin.finalsub.data.source.remote.StatusResponse.*

/**
 *@author Rizki Rian Anandita
 * Create By rizki
 */
class ApiResponse<T>(val status: StatusResponse, val body: T, val message: String?) {

    companion object {
        fun <T> success(body: T): ApiResponse<T> =
            ApiResponse(SUCCESS, body, null)

        fun <T> empty(msg: String, body: T): ApiResponse<T> =
            ApiResponse(EMPTY, body, msg)

        fun <T> error(msg: String, body: T): ApiResponse<T> =
            ApiResponse(ERROR, body, msg)
    }

}