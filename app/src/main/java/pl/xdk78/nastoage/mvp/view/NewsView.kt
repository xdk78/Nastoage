package pl.xdk78.nastoage.mvp.view

import pl.xdk78.nastoage.base.BaseView
import pl.xdk78.nastoage.mvp.model.Article

interface NewsView : BaseView {

    fun onItemLoaded(items: List<Article>)

}