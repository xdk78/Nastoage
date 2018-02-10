package pl.xdk78.nastoage.di.module.api


import dagger.Module
import dagger.Provides
import pl.xdk78.nastoage.api.MainApi
import pl.xdk78.nastoage.api.MainRepository
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
class ApiModule {

    @Provides
    fun provideMainApi(retrofit: Retrofit): MainApi = MainRepository(retrofit)

    @Provides
    @Singleton
    fun provideRetrofit(
            rxJavaCallAdapterFactory: RxJava2CallAdapterFactory,
            gsonConverterFactory: GsonConverterFactory): Retrofit {
        return Retrofit.Builder()
                .baseUrl("https://nastoletni.pl/wp-json/wp/v2/")
                .addCallAdapterFactory(rxJavaCallAdapterFactory)
                .addConverterFactory(gsonConverterFactory)
                .build()
    }

    @Provides
    @Singleton
    fun provideGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    @Singleton
    fun provideRxJavaCallAdapter(): RxJava2CallAdapterFactory = RxJava2CallAdapterFactory.create()

}