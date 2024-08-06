package com.example.fitfoood.view.article

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.fitfoood.adapter.ArticleCardAdapter
import com.example.fitfoood.data.ApiResponse
import com.example.fitfoood.data.response.ArticleItem
import com.example.fitfoood.databinding.ActivityArticleBinding
import com.example.fitfoood.view.ViewModelFactory
import com.example.fitfoood.view.main.HomeViewModel
import com.google.android.material.tabs.TabLayoutMediator


class ArticleActivity : AppCompatActivity() {

    private lateinit var binding: ActivityArticleBinding
    private lateinit var viewModel: HomeViewModel
    private var tabLayoutMediator: TabLayoutMediator? = null

    private val tabTitles = arrayOf("All", "Hidup Sehat", "Olahraga")
    private var articles: List<ArticleItem> = listOf()
    private lateinit var label: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArticleBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelFactory.getInstance(this).create(HomeViewModel::class.java)

        viewModel.getProfile().observe(this) { profile ->
            label = profile.bmiLabel ?: "ideal"

            showRecyclerList()
            fetchArticles()
        }
    }

    /**
     * Fetch articles from the API
     *
     * This function will fetch articles from the API and filter them based on the label.
     *
     * @see HomeViewModel.getArticles
     */
    private fun fetchArticles() {
        viewModel.getArticles().observe(this) { articles ->
            when (articles) {
                is ApiResponse.Success -> {
                    this.articles = articles.data?.data?.articles ?: listOf()
                    this.articles = this.articles.filter { it.label == label }
                    setupViewPager()
                    showRecyclerList()
                }
                is ApiResponse.Error -> {
                    // TODO: Handle error
                }
                is ApiResponse.Loading -> {
                    // TODO: Show loading
                }
            }
        }
    }

    /**
     * Setup the ViewPager
     *
     * This function will setup the ViewPager and the TabLayout for the articles.
     */
    private fun setupViewPager() {
        if (tabLayoutMediator == null) {
            tabLayoutMediator = TabLayoutMediator(
                binding.tabLayout,
                binding.viewPager,
                true,
                true
            ) { tab, position ->
                tab.setText(tabTitles[position])
            }
            val sectionsPagerAdapter =
                SectionsPagerAdapter(supportFragmentManager, this.articles)
            binding.viewPager.adapter = sectionsPagerAdapter
            tabLayoutMediator!!.attach()
        } else {
            (binding.viewPager.adapter as SectionsPagerAdapter).updateArticles(this.articles)
        }
    }

    /**
     * Show the recycler list
     *
     * This function will show the recycler list of articles.
     */
    private fun showRecyclerList() {
        val adapter = ArticleCardAdapter(this.articles)
        with(binding.recyclerView) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            this.adapter = adapter
        }
    }

    /**
     * SectionsPagerAdapter
     *
     * This class is used to manage the fragments in the ViewPager.
     *
     * @property articles list of articles
     */
    inner class SectionsPagerAdapter(fm: FragmentManager, private var articles: List<ArticleItem>) : FragmentStateAdapter(fm, lifecycle) {

        /**
         * Create a new fragment
         *
         * This function will create a new fragment based on the position.
         */
        override fun createFragment(position: Int): Fragment {
            val filteredArticles = when (position) {
                0 -> articles // Show all articles for the "All" tab
                1 -> articles.filter { it.category.equals("healthy_life", ignoreCase = true) }
                2 -> articles.filter { it.category.equals("workout", ignoreCase = true) }
                else -> articles
            }
            return ContentFragment.newInstance(filteredArticles)
        }

        /**
         * Get the item count
         *
         * This function will return the number of tabs.
         */
        override fun getItemCount(): Int {
            return tabTitles.size
        }

        /**
         * Update the articles
         *
         * This function will update the articles and notify the adapter.
         */
        @SuppressLint("NotifyDataSetChanged")
        fun updateArticles(newArticles: List<ArticleItem>) {
            articles = newArticles
            notifyDataSetChanged()
        }
    }
}
