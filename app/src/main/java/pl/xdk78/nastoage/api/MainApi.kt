package pl.xdk78.nastoage.api

import io.reactivex.Single
import pl.xdk78.nastoage.mvp.model.Article


interface MainApi {
    fun getNews(id: Int?): Single<List<Article>>
}