package pl.xdk78.nastoage.di.module

import android.content.Context
import dagger.Binds
import dagger.Module
import pl.xdk78.nastoage.App


@Module
abstract class AppModule {
    @Binds
    abstract fun provideContext(application: App): Context
}
