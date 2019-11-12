package com.example.aniguide.api

import com.google.gson.GsonBuilder
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

//interface OmdbApi {
//
//    @GET("/r/{subreddit}/hot.json?limit=100")
//    suspend fun getPosts(@Path("subreddit") subreddit: String): ListingResponse
//
//    @GET("/subreddits/popular.json")
//    suspend fun getSubreddits(): ListingResponse
//
//    class ListingResponse(val data: ListingData)
//
//    class ListingData(
//        val children: List<RedditChildrenResponse>,
//        val after: String?,
//        val before: String?
//    )
//    data class RedditChildrenResponse(val data: OmdbPost)
//
//    companion object {
//        private fun buildGsonConverterFactory(): GsonConverterFactory {
//            val gsonBuilder = GsonBuilder()
//            return GsonConverterFactory.create(gsonBuilder.create())
//        }
//        //private const val BASE_URL = "https://www.reddit.com/"
//        var httpurl = HttpUrl.Builder()
//            .scheme("https")
//            .host("www.omdb.com")
//            .build()
//        fun create(): OmdbApi = create(httpurl)
//        private fun create(httpUrl: HttpUrl): OmdbApi {
//
//            val client = OkHttpClient.Builder()
//                .addInterceptor(HttpLoggingInterceptor().apply {
//                    this.level = HttpLoggingInterceptor.Level.BASIC
//                })
//                .build()
//            return Retrofit.Builder()
//                .baseUrl(httpUrl)
//                .client(client)
//                .addConverterFactory(buildGsonConverterFactory())
//                .build()
//                .create(OmdbApi::class.java)
//        }
//    }
//}