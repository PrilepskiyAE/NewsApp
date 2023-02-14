package com.prilepskiy.newsapp.core


data class CallException(
    val errorCode: Int,
    val errorMessage: String? = null,
) : Exception()