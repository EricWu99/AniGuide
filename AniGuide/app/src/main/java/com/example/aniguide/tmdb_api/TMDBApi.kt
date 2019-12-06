package com.example.aniguide.tmdb_api

import com.google.gson.GsonBuilder
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TMDBApi {

    @GET ("/3/search/tv?api_key=ba626da3057b248f95ac15ff17f03268")
    suspend fun searchShow(@Query("query") title: String): TMDBSearchMeta

    @GET("/3/tv/{id}?api_key=ba626da3057b248f95ac15ff17f03268")
    suspend fun getNumSeasons(@Path("id") id: String): TMDBShowMeta

    @GET("/3/tv/{id}/season/{season}?api_key=ba626da3057b248f95ac15ff17f03268")
    //https://api.themoviedb.org/3/tv/65930/season/4?api_key=ba626da3057b248f95ac15ff17f03268
    suspend fun getSeason(@Path("id") id: String, @Path("season") season: String): TMDBSeasonMeta

    companion object {
        private fun buildGsonConverterFactory(): GsonConverterFactory {
            val gsonBuilder = GsonBuilder()
            return GsonConverterFactory.create(gsonBuilder.create())
        }
        var httpurl = HttpUrl.Builder()
            .scheme("https")
            .host("api.themoviedb.org")
            .build()
        fun create(): TMDBApi = create(httpurl)
        private fun create(httpUrl: HttpUrl): TMDBApi {

            val client = OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().apply {
                    this.level = HttpLoggingInterceptor.Level.BASIC
                })
                .build()
            return Retrofit.Builder()
                .baseUrl(httpUrl)
                .client(client)
                .addConverterFactory(buildGsonConverterFactory())
                .build()
                .create(TMDBApi::class.java)
        }
    }
}