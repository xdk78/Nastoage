package pl.xdk78.nastoage.ui

import android.os.Bundle
import android.support.design.widget.Snackbar
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
import kotlinx.android.synthetic.main.fragment_main.*
import org.jetbrains.anko.support.v4.find
import pl.xdk78.nastoage.R
import pl.xdk78.nastoage.adapter.ArticleAdapter
import pl.xdk78.nastoage.api.RepositoryProvider
import pl.xdk78.nastoage.model.Article


class CuriositesFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ArticleAdapter
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    companion object {
        fun newInstance(): CuriositesFragment {
            val fragment = CuriositesFragment()
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_curiosites, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        swiperefresh.setOnRefreshListener {

            swiperefresh.isRefreshing = true
            getData(view!!)
        }
        getData(view!!)
    }

    fun getData(view: View) {
        val repository = RepositoryProvider.provideRepository()
        compositeDisposable.add(
                repository.getCuriosities()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe({ items ->
                            val articles: List<Article> = items
                            recyclerView = find<RecyclerView>(R.id.curiosities_articles)
                            recyclerView.layoutManager = LinearLayoutManager(activity)
                            adapter = ArticleAdapter(activity, articles)

                            recyclerView.adapter = adapter

                            swiperefresh.isRefreshing = false
                        }, { error ->
                            Log.e("Curiosities Api", error.toString())
                            Snackbar.make(view, error.toString(), Snackbar.LENGTH_LONG).show()
                            swiperefresh.isRefreshing = false
                        })
        )
    }

    override fun onDestroyView() {
        compositeDisposable.clear()
        super.onDestroyView()
    }
}
