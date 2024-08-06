package com.example.fitfoood.view.artikel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.core.os.BundleCompat
import com.bumptech.glide.Glide
import com.example.fitfoood.R
import com.example.fitfoood.databinding.ActivityDetailArtikelBinding
import com.example.fitfoood.data.response.ArticleItem
import com.example.fitfoood.utils.Constants
import com.example.fitfoood.view.ViewModelFactory
import com.example.fitfoood.view.main.HomeViewModel

class DetailArtikelActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailArtikelBinding
    private lateinit var viewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailArtikelBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelFactory.getInstance(this).create(HomeViewModel::class.java)

        val article : ArticleItem = BundleCompat.getParcelable(intent.extras!!, Constants.EXTRA_ARTICLE, ArticleItem::class.java) as ArticleItem
        showArticleDetails(article)

        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
        val tbTitle = findViewById<TextView>(R.id.title_toolbar)
        tbTitle.text = "Artikel Detail"

        binding.toolbar.setOnClickListener {
            finish()
        }
    }

    private fun showArticleDetails(article: ArticleItem) {
        binding.apply {
            articleTitle.text = article.title

            // use placeholder image if thumbnail is null
            Glide.with(this@DetailArtikelActivity)
                .load(article.thumbnail)
                .placeholder(R.drawable.img_artikel_dummy)
                .into(articleImage)

            // show the article content
            textView10.text = article.content
        }
    }
}
