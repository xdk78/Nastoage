package pl.xdk78.nastoage.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.support.v4.find
import pl.xdk78.nastoage.R
import pl.xdk78.nastoage.adapter.ArticleAdapter
import pl.xdk78.nastoage.api.RepositoryProvider
import pl.xdk78.nastoage.model.Article


class MainFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ArticleAdapter
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getData()
    }

    fun getData() {
        val repository = RepositoryProvider.provideRepository()
        compositeDisposable.add(
                repository.getNews()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe({
                            items ->
                            val articles: List<Article> = items
                            recyclerView = find<RecyclerView>(R.id.articles)
                            recyclerView.layoutManager = LinearLayoutManager(activity)
                            adapter = ArticleAdapter(activity, articles)
                            recyclerView.adapter = adapter
                        }, { error ->
                            Log.e("NewsAPI", error.stackTrace.toString())
                        })
        )
    }

    override fun onDestroyView() {
        compositeDisposable.clear()
        super.onDestroyView()
    }
}
