package pl.xdk78.nastoage.api

/**
 * Created by xdk78 on 2017-08-12.
 */
object RepositoryProvider {

    fun provideRepository(): Repository {
        return Repository(ApiService.Factory.create())
    }

}