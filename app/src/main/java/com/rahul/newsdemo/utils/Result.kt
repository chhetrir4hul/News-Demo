package com.rahul.newsdemo.utils

sealed class Result<out R> {
    data class Success<T>(val data: T?) : Result<T>()
    data class Error(val message: String? = null) : Result<Nothing>()
}