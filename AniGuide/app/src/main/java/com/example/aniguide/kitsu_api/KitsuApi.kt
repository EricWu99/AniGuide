package com.example.aniguide.kitsu_api

import com.google.gson.GsonBuilder
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface KitsuApi {

    @GET ("/api/edge/anime?page%5Dlimit%5D=20")
    suspend fun getAllShows(@Query("filter[seasonYear]") year: String, @Query("page[offset]") offset: Int): ListingData

    @GET ("/api/edge/anime?page%5Dlimit%5D=20")
    suspend fun getSeasonalShows(@Query("filter[season]") season: String, @Query("filter[seasonYear]") year: String, @Query("page[offset]") offset: Int): ListingData

    class ListingData(
        val data: List<Data>
    )

    companion object {
        private fun buildGsonConverterFactory(): GsonConverterFactory {
            val gsonBuilder = GsonBuilder()
            return GsonConverterFactory.create(gsonBuilder.create())
        }
        var httpurl = HttpUrl.Builder()
            .scheme("https")
            .host("kitsu.io")
            .build()
        fun create(): KitsuApi = create(httpurl)
        private fun create(httpUrl: HttpUrl): KitsuApi {

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
                .create(KitsuApi::class.java)
        }
    }
}