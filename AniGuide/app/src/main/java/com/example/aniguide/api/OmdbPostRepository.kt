package com.example.aniguide.api

import com.example.aniguide.MainActivity
import com.google.gson.Gson

//class RedditPostRepository(private val redditOmdbApi: OmdbApi) {
//    val gson = Gson()
//
//    fun unpackPosts(response: OmdbApi.ListingResponse): List<OmdbPost>? {
//        // XXX Write me
//
//        val postList: MutableList<OmdbPost> = ArrayList()
//
//        val childResponses = response.data.children
//        childResponses.forEach {
//            postList.add(it.data)
//        }
//        return postList
//
//    }
//
//    suspend fun getPosts(subreddit: String): List<OmdbPost>? {
//
//            return unpackPosts(redditOmdbApi.getPosts(subreddit))
//    }
//
//    suspend fun getSubreddits(): List<OmdbPost>? {
//
//            return unpackPosts(redditOmdbApi.getSubreddits())
//    }
//}

