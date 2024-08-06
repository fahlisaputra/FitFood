package com.example.fitfoood.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fitfoood.R
import com.example.fitfoood.data.response.ArticleItem
import com.example.fitfoood.databinding.Artikel2RowBinding
import com.example.fitfoood.view.artikel.DetailArtikelActivity

class ArticleListAdapter(private var listItem: List<ArticleItem>) : RecyclerView.Adapter<ArticleListAdapter.ViewHolder>() {

    class ViewHolder(private val binding: Artikel2RowBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(artikel: ArticleItem) {
            binding.tvName.text = artikel.title

            Glide.with(binding.root)
                .load(artikel.thumbnail)
                .into(binding.imgPhoto)

            val categoryIcon = when (artikel.category) {
                "hidup sehat" -> R.drawable.icon_hidupsehat
                "olahraga" -> R.drawable.icon_olahraga
                else -> R.drawable.icon_hidupsehat // Optional default icon
            }
            binding.idCategoryArtikel.setImageResource(categoryIcon)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = Artikel2RowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listItem.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val artikel = listItem[position]
        holder.bind(artikel)

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, DetailArtikelActivity::class.java)
            intent.putExtra("Artikel", artikel)
            holder.itemView.context.startActivity(intent)
        }
    }
}
