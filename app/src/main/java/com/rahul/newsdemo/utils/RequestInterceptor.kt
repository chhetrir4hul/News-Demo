package com.rahul.newsdemo.utils

import com.rahul.newsdemo.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class RequestInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response = chain.run {
        var url = request().url

        val urlBuilder = url.newBuilder()

        urlBuilder.addQueryParameter("apiKey", BuildConfig.NEWS_API_KEY)

        url = urlBuilder.build()

        proceed(request().newBuilder().url(url).build())
    }
}