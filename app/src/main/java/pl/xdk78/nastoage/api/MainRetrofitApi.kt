package pl.xdk78.nastoage.api

import io.reactivex.Single
import pl.xdk78.nastoage.mvp.model.Article
import retrofit2.http.GET
import retrofit2.http.Query


interface MainRetrofitApi {

    @GET("posts")
    fun getNews(@Query("categories") id: Int?): Single<List<Article>>

}