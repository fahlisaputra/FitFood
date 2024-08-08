package com.example.fitfoood.view.article

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.os.BundleCompat
import com.bumptech.glide.Glide
import com.example.fitfoood.R
import com.example.fitfoood.databinding.ActivityArticleDetailBinding
import com.example.fitfoood.data.response.ArticleItem
import com.example.fitfoood.utils.Constants
import com.example.fitfoood.view.ViewModelFactory
import com.example.fitfoood.view.main.HomeViewModel

class ArticleDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityArticleDetailBinding
    private lateinit var viewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArticleDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelFactory.getInstance(this).create(HomeViewModel::class.java)

        val article : ArticleItem = BundleCompat.getParcelable(intent.extras!!, Constants.EXTRA_ARTICLE, ArticleItem::class.java) as ArticleItem
        showArticleDetails(article)

        binding.btnBack.setOnClickListener {
            finish()
        }
    }

    /**
     * Show the article details
     *
     * This function will show the article details on the screen
     *
     * @param article article data
     */
    private fun showArticleDetails(article: ArticleItem) {
        binding.apply {
            articleTitle.text = article.title

            // use placeholder image if thumbnail is null
            Glide.with(this@ArticleDetailActivity)
                .load(article.thumbnail)
                .placeholder(R.drawable.img_artikel_dummy)
                .into(articleImage)

            // show the article content
            textView10.text = article.content
        }
    }
}
