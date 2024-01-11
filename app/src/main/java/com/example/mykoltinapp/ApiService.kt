package com.example.mykoltinapp
import retrofit2.Response
import retrofit2.http.GET

// ApiService.kt
interface ApiService {
    @GET("chart/0/tracks")
    suspend fun getTopTracks(): Response<DeezerResponse>

    @GET("chart/0/tracks?limit=300")
    suspend fun getTop100Tracks(): Response<DeezerResponse>
}
