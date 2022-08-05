package com.rahul.newsdemo.extensions

import com.google.gson.Gson
import com.rahul.newsdemo.data.remote.ErrorResponse
import com.rahul.newsdemo.utils.Result
import retrofit2.HttpException
import java.io.IOException

fun <T> String.asJson(type: Class<T>): T? =
    try {
        Gson().fromJson(this, type)
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }

fun Exception.toApiError(): Result.Error =
    when (this) {
        is IOException -> {
            Result.Error("Please check internet connection")
        }
        is HttpException -> {
            val jsonError = this.response()?.errorBody()?.string().orEmpty()
            val errorResponse = jsonError.asJson(ErrorResponse::class.java)
            errorResponse?.let {
                val error = it.message ?: "Unknown error"
                Result.Error(error)
            } ?: run {
                Result.Error("Unknown error")
            }
        }
        else -> {
            Result.Error("Unknown error")
        }
    }