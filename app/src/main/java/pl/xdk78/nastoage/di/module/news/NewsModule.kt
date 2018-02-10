package pl.xdk78.nastoage.di.module.news


import dagger.Module
import dagger.Provides
import pl.xdk78.nastoage.api.MainApi
import pl.xdk78.nastoage.mvp.presenter.NewsPresenter


@Module
class NewsModule {
    @Provides
    fun provideNewsPresenter(mainApi: MainApi) = NewsPresenter(mainApi)

}