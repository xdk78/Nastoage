package pl.xdk78.nastoage.mvp.presenter


import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import pl.xdk78.nastoage.api.MainApi
import pl.xdk78.nastoage.base.BasePresenter
import pl.xdk78.nastoage.mvp.view.NewsView


class NewsPresenter(val mainApi: MainApi) : BasePresenter<NewsView>() {

    fun loadData(id: Int?) {
        compositeObservable.add(
                mainApi
                        .getNews(id)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(
                                { items -> view?.onItemLoaded(items) },
                                { error -> view?.onError(error) })
        )
    }
}
