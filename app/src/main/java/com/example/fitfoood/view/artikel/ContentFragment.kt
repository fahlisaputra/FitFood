package com.example.fitfoood.view.artikel

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fitfoood.adapter.ArticleListAdapter
import com.example.fitfoood.data.response.ArticleItem
import com.example.fitfoood.databinding.FragmentContentBinding

class ContentFragment : Fragment() {
    private var _binding: FragmentContentBinding? = null
    private val binding get() = _binding!!
    private lateinit var articleAdapter: ArticleListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentContentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val articles = arguments?.getParcelableArrayList<ArticleItem>("articles") ?: listOf()
        articleAdapter = ArticleListAdapter(articles)

        binding.recyclerView2.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = articleAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance(articles: List<ArticleItem>) = ContentFragment().apply {
            arguments = Bundle().apply {
                putParcelableArrayList("articles", ArrayList(articles))
            }
            articles.forEach {
                Log.d("Content Fragment", it.toString())
            }
        }
    }
}
