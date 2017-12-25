package pl.xdk78.nastoage.api

import io.reactivex.Observable
import pl.xdk78.nastoage.model.Article
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


/**
 * Created by xdk78 on 2017-07-28.
 */
interface ApiService {


    @GET("posts")
    fun getArticles(@Query("categories") id: Int?): Observable<List<Article>>
    





    companion object Factory {
        fun create(): ApiService {
            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("https://nastoletni.pl/wp-json/wp/v2/")
                    .build()

            return retrofit.create(ApiService::class.java)
        }
    }


}