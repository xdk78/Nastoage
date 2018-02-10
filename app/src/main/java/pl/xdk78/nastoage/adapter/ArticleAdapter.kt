package pl.xdk78.nastoage.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.LevelListDrawable
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.text.Html
import android.text.Spanned
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import org.jetbrains.anko.find
import pl.xdk78.nastoage.R
import pl.xdk78.nastoage.helper.ImageGetterAsyncTask
import pl.xdk78.nastoage.mvp.model.Article


class ArticleAdapter(private var context: Context, private var articles: List<Article>) : RecyclerView.Adapter<ArticleAdapter.ArticleAdapterViewHolder>() {

    @SuppressLint("PrivateResource")
    override fun onBindViewHolder(holder: ArticleAdapterViewHolder, position: Int) {
        val item = articles[position]
        holder.title.text = Html.fromHtml(item.title.rendered)

        val spanned: Spanned
        spanned = Html.fromHtml(item.content.rendered,
                Html.ImageGetter { source ->
                    val d = LevelListDrawable()
                    val empty = ContextCompat.getDrawable(context, R.drawable.abc_btn_check_material)
                    d.addLevel(0, 0, empty)
                    d.setBounds(0, 0, empty!!.intrinsicWidth, empty.intrinsicHeight)
                    ImageGetterAsyncTask(context, source, d).execute(holder.content)
                    d
                }, null)
        holder.content.text = spanned

        /*Glide.with(context)
                .asDrawable()
                .load(item.links.featuredmedia.href.guid.rendered)
                .into((holder).image)
        */
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ArticleAdapterViewHolder {
        val layoutInflater = LayoutInflater.from(parent?.context)
        return ArticleAdapterViewHolder(layoutInflater.inflate(R.layout.article_item, parent, false))
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    class ArticleAdapterViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.find(R.id.title)
        val content: TextView = view.find(R.id.content)
        // val image: ImageView = view.find<ImageView>(R.id.main_image)
    }
}