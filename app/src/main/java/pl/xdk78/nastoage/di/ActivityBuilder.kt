package pl.xdk78.nastoage.di


import dagger.Module
import dagger.android.ContributesAndroidInjector
import pl.xdk78.nastoage.di.module.news.NewsFragmentProvider
import pl.xdk78.nastoage.di.module.news.NewsModule
import pl.xdk78.nastoage.ui.MainActivity


@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [
        NewsModule::class, NewsFragmentProvider::class])
    abstract fun bindMainActivity(): MainActivity

}
