package pl.xdk78.nastoage.ui

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_main.*
import org.jetbrains.anko.support.v4.find
import pl.xdk78.nastoage.R
import pl.xdk78.nastoage.adapter.ArticleAdapter
import pl.xdk78.nastoage.base.BaseFragment
import pl.xdk78.nastoage.mvp.model.Article
import pl.xdk78.nastoage.mvp.presenter.NewsPresenter
import pl.xdk78.nastoage.mvp.view.NewsView
import java.io.IOException
import javax.inject.Inject


class NewsFragment : BaseFragment(), NewsView {

    lateinit var adapter: ArticleAdapter

    @Inject
    lateinit var presenter: NewsPresenter

    lateinit var recyclerView: RecyclerView

    private var article_Id: Int? = null

    companion object {
        fun newInstance(): NewsFragment {
            return NewsFragment()
        }
    }

    private fun readBundle(bundle: Bundle?) {
        if (bundle != null) {
            article_Id = bundle.getInt("id")
            if (article_Id == 0) {
                article_Id = null
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        readBundle(arguments)
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        swiperefresh.setOnRefreshListener {
            presenter.subscribe(this)
            swiperefresh.isRefreshing = true
            presenter.loadData(article_Id)
        }
        presenter.subscribe(this)
        presenter.loadData(article_Id)
    }

    override fun onItemLoaded(items: List<Article>) {
        recyclerView = find(R.id.articles)
        recyclerView.layoutManager = LinearLayoutManager(context!!)
        adapter = ArticleAdapter(context!!, items)
        recyclerView.adapter = adapter

        swiperefresh.isRefreshing = false
    }

    override fun onError(e: Throwable?) {
        when (e) {
            is IOException -> {
                Log.e("NewsAPI", e.message)
                Snackbar.make(this.view!!, e.toString(), Snackbar.LENGTH_LONG).show()
                swiperefresh.isRefreshing = false
            }
            else -> Log.e("NewsAPI", "List is empty")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.unsubscribe()
    }

    override fun onDetach() {
        super.onDetach()
        presenter.unsubscribe()
    }
}
