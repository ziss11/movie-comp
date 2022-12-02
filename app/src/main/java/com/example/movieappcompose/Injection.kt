package com.example.movieappcompose

import com.example.movieappcompose.data.datasources.service.ApiConfig
import com.example.movieappcompose.data.datasources.service.ApiService

object Injection {
    fun provideApiService(): ApiService {
        return ApiConfig.getApiService()
    }
}