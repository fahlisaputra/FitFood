package com.example.fitfoood.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fitfoood.R
import com.example.fitfoood.data.response.ArticleItem
import com.example.fitfoood.databinding.ArticleRowCardBinding
import com.example.fitfoood.utils.Constants
import com.example.fitfoood.view.article.ArticleDetailActivity

/**
 * Article Card Adapter
 *
 * This adapter is used to show article data in card view
 *
 * @property listItem list of article data
 * @see ArticleItem
 */
class ArticleCardAdapter(private var listItem: List<ArticleItem>) : RecyclerView.Adapter<ArticleCardAdapter.ViewHolder>() {

        /**
         * ViewHolder class
         *
         * This class is used to bind article data to the view
         *
         * @param binding article row card binding
         */
        class ViewHolder(private val binding: ArticleRowCardBinding) : RecyclerView.ViewHolder(binding.root) {
                fun bind(article: ArticleItem) {
                        binding.tvName.text = article.title

                        Glide.with(binding.root)
                                .load(article.thumbnail)
                                .into(binding.imgPhoto)

                        val categoryIcon = when (article.category) {
                                "hidup-sehat" -> R.drawable.icon_hidupsehat
                                "olahraga" -> R.drawable.icon_olahraga
                                else -> R.drawable.icon_hidupsehat // Optional default icon
                        }
                        binding.idCategoryArtikel.setImageResource(categoryIcon)
                }
        }

        /**
         * Create ViewHolder
         *
         * @param parent parent view group
         * @param viewType view type
         * @return ViewHolder
         */
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
                var binding = ArticleRowCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return ViewHolder(binding)
        }

        /**
         * Get item count
         *
         * @return item count
         */
        override fun getItemCount(): Int {
                return listItem.size
        }

        /**
         * Bind data to the view
         *
         * @param holder view holder
         * @param position item position
         */
        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
                val article = listItem[position]
                holder.bind(article)

                holder.itemView.setOnClickListener {
                        val intent = Intent(holder.itemView.context, ArticleDetailActivity::class.java)
                        intent.putExtra(Constants.EXTRA_ARTICLE, article)
                        holder.itemView.context.startActivity(intent)
                }
        }
}
