package com.example.fitfoood

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fitfoood.data.response.ArticleItem
import com.example.fitfoood.databinding.ArtikelRowBinding
import com.example.fitfoood.view.artikel.DetailArtikelActivity

class ArticleAdapter(private var listItem: List<ArticleItem>) : RecyclerView.Adapter<ArticleAdapter.ViewHolder>() {

        class ViewHolder(private val binding: ArtikelRowBinding) : RecyclerView.ViewHolder(binding.root) {
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

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
                val binding = ArtikelRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return ViewHolder(binding)
        }

        override fun getItemCount(): Int {
                return listItem.size
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
                val article = listItem[position]
                holder.bind(article)

                holder.itemView.setOnClickListener {
                        val intent = Intent(holder.itemView.context, DetailArtikelActivity::class.java)
                        intent.putExtra("Artikel", article)
                        holder.itemView.context.startActivity(intent)
                }
        }

        fun updateData(newList: List<ArticleItem>) {
                listItem = newList
                notifyDataSetChanged()
        }
}
