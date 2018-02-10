package pl.xdk78.nastoage.di.module.news

import dagger.Module
import dagger.android.ContributesAndroidInjector
import pl.xdk78.nastoage.ui.NewsFragment


@Module
abstract class NewsFragmentProvider {
    @ContributesAndroidInjector(modules = [NewsModule::class])
    abstract fun provideNewsFragment(): NewsFragment

}