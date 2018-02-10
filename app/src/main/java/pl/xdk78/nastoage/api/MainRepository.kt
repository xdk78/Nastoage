package pl.xdk78.nastoage.api

import io.reactivex.Single
import pl.xdk78.nastoage.mvp.model.Article
import retrofit2.Retrofit


class MainRepository(val retrofit: Retrofit) : MainApi {

    private val mainApi by lazy { retrofit.create(MainRetrofitApi::class.java) }

    override fun getNews(id: Int?): Single<List<Article>> = mainApi.getNews(id)

}