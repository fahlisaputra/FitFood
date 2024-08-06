package com.example.fitfoood.view.artikel

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.fitfoood.adapter.ArticleCardAdapter
import com.example.fitfoood.data.ApiResponse
import com.example.fitfoood.data.response.ArticleItem
import com.example.fitfoood.databinding.ActivityArtikelBinding
import com.example.fitfoood.view.ViewModelFactory
import com.example.fitfoood.view.main.HomeViewModel
import com.google.android.material.tabs.TabLayoutMediator


class ArtikelActivity : AppCompatActivity() {

    private val tabTitles = arrayOf("All", "Hidup Sehat", "Olahraga")

    private lateinit var binding: ActivityArtikelBinding
    private lateinit var viewModel: HomeViewModel
    private var articles: List<ArticleItem> = listOf()
    private var tabLayoutMediator: TabLayoutMediator? = null
    private lateinit var label: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArtikelBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelFactory.getInstance(this).create(HomeViewModel::class.java)

        viewModel.getProfile().observe(this) { profile ->
            label = profile.bmiLabel ?: "ideal"

            showRecyclerList()
            fetchArticles()
        }
    }


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
                    // Handle error
                }
                is ApiResponse.Loading -> {
                    // Show loading
                }
            }
        }
    }


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

    private fun showRecyclerList() {
        val adapter = ArticleCardAdapter(this.articles)
        with(binding.recyclerView) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            this.adapter = adapter
        }
    }

    inner class SectionsPagerAdapter(fm: FragmentManager, private var articles: List<ArticleItem>) : FragmentStateAdapter(fm, lifecycle) {
        override fun createFragment(position: Int): Fragment {
            val filteredArticles = when (position) {
                0 -> articles // Show all articles for the "All" tab
                1 -> articles.filter { it.category.equals("hidup-sehat", ignoreCase = true) }
                2 -> articles.filter { it.category.equals("olahraga", ignoreCase = true) }
                else -> articles
            }
            return ContentFragment.newInstance(filteredArticles)
        }

        override fun getItemCount(): Int {
            return tabTitles.size
        }

        fun updateArticles(newArticles: List<ArticleItem>) {
            articles = newArticles
            notifyDataSetChanged()
        }
    }
}
