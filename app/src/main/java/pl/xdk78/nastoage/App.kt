package pl.xdk78.nastoage


import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import pl.xdk78.nastoage.di.component.DaggerMainComponent


class App : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
            DaggerMainComponent.builder().create(this)

}