package pl.xdk78.nastoage.api

import io.reactivex.Observable
import pl.xdk78.nastoage.model.Article

/**
 * Created by xdk78 on 2017-08-12.
 */
class Repository(val apiService: ApiService) {

    fun getNews(): Observable<List<Article>> {
        return apiService.getNews()
    }

}