package pl.xdk78.nastoage.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import org.jetbrains.anko.find
import pl.xdk78.nastoage.R
import pl.xdk78.nastoage.model.Article
import java.util.*

/**
 * Created by xdk78 on 2017-07-28.
 */
class ArticleAdapter(var context: Context, articles: LinkedList<Article>) : RecyclerView.Adapter<ArticleAdapter.ArticleAdapterViewHolder>() {

    private var articles = LinkedList<Article>()

    init {
        this.articles = articles
        this.context = context

    }

    override fun onBindViewHolder(holder: ArticleAdapterViewHolder, position: Int) {
        val item = articles[position]

        holder.title.text = item.title

    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ArticleAdapterViewHolder {
        val layoutInflater = LayoutInflater.from(parent?.context)
        return ArticleAdapterViewHolder(layoutInflater.inflate(R.layout.article_item, parent, false))
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    class ArticleAdapterViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.find<TextView>(R.id.title)
    }
}