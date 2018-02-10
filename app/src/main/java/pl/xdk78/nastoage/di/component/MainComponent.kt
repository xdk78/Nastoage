package pl.xdk78.nastoage.di.component

import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import pl.xdk78.nastoage.App
import pl.xdk78.nastoage.di.ActivityBuilder
import pl.xdk78.nastoage.di.module.AppModule
import pl.xdk78.nastoage.di.module.api.ApiModule
import javax.inject.Singleton


@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    AppModule::class,
    ActivityBuilder::class,
    ApiModule::class])

internal interface MainComponent : AndroidInjector<App> {
    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<App>()
}