package com.example.aniguide.kitsu_api

import com.google.gson.annotations.SerializedName

data class Shows (
	@SerializedName("data") val data: Data
)

data class Data (

	@SerializedName("id") val id : Int,
	@SerializedName("type") val type : String,
	@SerializedName("attributes") val attributes : Attributes
)

data class Attributes (

	@SerializedName("createdAt") val createdAt : String,
	@SerializedName("updatedAt") val updatedAt : String,
	@SerializedName("slug") val slug : String,
	@SerializedName("synopsis") val synopsis : String,
	@SerializedName("coverImageTopOffset") val coverImageTopOffset : Int,
	@SerializedName("titles") val titles : Titles,
	@SerializedName("canonicalTitle") val canonicalTitle : String,
	@SerializedName("averageRating") val averageRating : Double,
	@SerializedName("userCount") val userCount : Int,
	@SerializedName("favoritesCount") val favoritesCount : Int,
	@SerializedName("startDate") val startDate : String,
	@SerializedName("endDate") val endDate : String,
	@SerializedName("nextRelease") val nextRelease : String,
	@SerializedName("popularityRank") val popularityRank : Int,
	@SerializedName("ratingRank") val ratingRank : Int,
	@SerializedName("ageRating") val ageRating : String,
	@SerializedName("ageRatingGuide") val ageRatingGuide : String,
	@SerializedName("subtype") val subtype : String,
	@SerializedName("status") val status : String,
	@SerializedName("tba") val tba : String,
	@SerializedName("posterImage") val posterImage : PosterImage,
	@SerializedName("coverImage") val coverImage : CoverImage,
	@SerializedName("episodeCount") val episodeCount : Int,
	@SerializedName("episodeLength") val episodeLength : Int,
	@SerializedName("totalLength") val totalLength : Int,
	@SerializedName("youtubeVideoId") val youtubeVideoId : String,
	@SerializedName("showType") val showType : String,
	@SerializedName("nsfw") val nsfw : Boolean
){
	fun getShowFields(): String {

		return "$canonicalTitle ${titles.en} ${titles.en_jp} ${titles.en_jp} $synopsis"
	}
}

data class Titles (

	@SerializedName("en") val en : String,
	@SerializedName("en_jp") val en_jp : String,
	@SerializedName("ja_jp") val ja_jp : String
)

data class PosterImage (

	@SerializedName("tiny") val tiny : String,
	@SerializedName("small") val small : String,
	@SerializedName("medium") val medium : String,
	@SerializedName("large") val large : String,
	@SerializedName("original") val original : String
)

data class CoverImage (

	@SerializedName("tiny") val tiny : String,
	@SerializedName("small") val small : String,
	@SerializedName("medium") val medium : String,
	@SerializedName("large") val large : String,
	@SerializedName("original") val original : String
)

