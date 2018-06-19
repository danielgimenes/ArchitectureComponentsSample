package com.dgimenes.architecturesample.data.model

import com.dgimenes.architecturesample.movieslist.LoadingState

data class Resource<out T>(val data: T, val loadingState: LoadingState, val errorMessage: String? = null)