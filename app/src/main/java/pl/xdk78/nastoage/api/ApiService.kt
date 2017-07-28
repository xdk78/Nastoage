package pl.xdk78.nastoage.api

import pl.xdk78.nastoage.model.Article
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


/**
 * Created by xdk78 on 2017-07-28.
 */
interface ApiService {

    @GET("posts")
    fun getNews(): Call<Article>

    companion object Factory {
        fun create(): ApiService {
            val retrofit = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("https://nastoletni.pl/wp-json/wp/v2/")
                    .build()

            return retrofit.create(ApiService::class.java)
        }
    }

}