package pl.xdk78.nastoage.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import org.jetbrains.anko.find
import pl.xdk78.nastoage.R
import pl.xdk78.nastoage.model.Article

/**
 * Created by xdk78 on 2017-07-28.
 */
class ArticleAdapter(var context: Context, var articles: List<Article>) : RecyclerView.Adapter<ArticleAdapter.ArticleAdapterViewHolder>() {


    init {
        this.context = context
    }

    override fun onBindViewHolder(holder: ArticleAdapterViewHolder, position: Int) {
        val item = articles[position]
        holder.title.text = Html.fromHtml(item.title.rendered)
        holder.content.text = Html.fromHtml(item.content.rendered)

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
        val content: TextView = view.find<TextView>(R.id.content)
    }
}