package com.example.fitfoood.view.weighttracker

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.fitfoood.R
import com.example.fitfoood.databinding.FragmentAddWeightBinding
import com.example.fitfoood.databinding.FragmentChatBinding

class AddWeightFragment : Fragment() {
    private var _binding: FragmentAddWeightBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAddWeightBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbar.setOnClickListener {
            requireActivity().onBackPressed()
        }

        val tbTitle = view.findViewById<TextView>(R.id.title_toolbar)
        tbTitle.text = getString(R.string.tambah_berat)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}