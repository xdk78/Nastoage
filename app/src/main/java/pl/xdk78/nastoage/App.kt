package pl.xdk78.nastoage

import android.app.Application

/**
 * Created by xdk78 on 2017-07-23.
 */
class App : Application() {

    private var instance: App? = null

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    fun getInstance(): App {
        if (instance == null) {
            instance = App()
        }
        return instance as App
    }
}