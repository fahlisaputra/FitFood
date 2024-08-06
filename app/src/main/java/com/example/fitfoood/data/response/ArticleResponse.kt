package com.example.fitfoood.data.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

/**
 * Data class that captures article information
 *
 * @property data article metadata
 * @see ArticleMetadata for more information
 */
data class ArticleResponse(

	@field:SerializedName("data")
	val data: ArticleMetadata? = null,
)

/**
 * Data class that captures article metadata
 *
 * @property label article label
 * @property articles list of articles
 * @see ArticleItem for more information
 */
data class ArticleMetadata(

	@field:SerializedName("label")
	val label: String? = null,

	@field:SerializedName("articles")
	val articles: List<ArticleItem>? = null,
)

/**
 * Data class that captures article item
 *
 * @property id article id
 * @property title article title
 * @property thumbnail article thumbnail
 * @property category article category
 * @property label article label
 * @property content article content
 * @property source article source
 * @property url article url
 * @property publishDate article publish date
 */
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
