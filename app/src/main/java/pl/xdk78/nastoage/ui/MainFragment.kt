package pl.xdk78.nastoage.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.jetbrains.anko.support.v4.find
import pl.xdk78.nastoage.R
import pl.xdk78.nastoage.adapter.ArticleAdapter
import pl.xdk78.nastoage.api.ApiService
import pl.xdk78.nastoage.model.Article
import java.util.*


class MainFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ArticleAdapter
    private var articles: LinkedList<Article> = LinkedList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val apiService = ApiService.create()
        val callResponse = apiService.getNews()
        val response = callResponse.execute()
        if (response.isSuccessful) {
            articles.add(response.body())
            //TODO: Fix this
            recyclerView = find<RecyclerView>(R.id.articles)
            recyclerView.setHasFixedSize(true)
            recyclerView.layoutManager = LinearLayoutManager(activity)
            adapter = ArticleAdapter(activity, articles)
            recyclerView.adapter = adapter
        }
        else {
            Log.e("NewsAPI", response.errorBody().toString())
        }

    }


}
