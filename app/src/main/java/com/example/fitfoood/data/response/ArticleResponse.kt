package com.example.fitfoood.data.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class ArticleResponse(

	@field:SerializedName("data")
	val data: ArticleMetadata? = null,
)

data class ArticleMetadata(

	@field:SerializedName("label")
	val label: String? = null,

	@field:SerializedName("articles")
	val articles: List<ArticleItem>? = null,
)

@Parcelize
data class ArticleItem(

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("image_url")
	val thumbnail: String? = null,

	@field:SerializedName("category")
	val category: String? = null,

	@field:SerializedName("label")
	val label: String? = null,

	@field:SerializedName("text")
	val content: String? = null,

	@field:SerializedName("article_source")
	val source: String? = null,

	@field:SerializedName("article_url")
	val url: String? = null,

	@field:SerializedName("article_createdAt")
	val publishDate: String? = null,

) : Parcelable
