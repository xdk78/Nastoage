package pl.xdk78.nastoage.model

import com.google.gson.annotations.SerializedName

/**
 * Created by xdk78 on 2017-07-28.
 */
data class Article(
        @SerializedName("id") val id: Int,
        @SerializedName("title") val title: Title,
        @SerializedName("content") val content: Content,
        @SerializedName("excerpt") val excerpt: Excerpt
        // @SerializedName("_links") val links: Links
)
data class Title (@SerializedName("rendered") val rendered: String)
data class Content (@SerializedName("rendered") val rendered: String)
data class Excerpt (@SerializedName("rendered") val rendered: String) // shortest version of content
/*
data class Links (@SerializedName("wp:featuredmedia") val featuredmedia: FeaturedMedia)
data class FeaturedMedia (@SerializedName("href") val href: Href) // article media
data class Href (@SerializedName("guid") val guid: Guid)
data class Guid (@SerializedName("rendered") val rendered: String) // article main image
*/