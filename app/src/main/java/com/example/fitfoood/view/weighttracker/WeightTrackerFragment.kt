package com.example.fitfoood.view.weighttracker

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.commit
import com.example.fitfoood.R
import com.example.fitfoood.databinding.FragmentProfileBinding
import com.example.fitfoood.databinding.FragmentWeightTrackerBinding
import com.example.fitfoood.view.main.AccountFragment
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter

class WeightTrackerFragment : Fragment(), View.OnClickListener {
    private var _binding: FragmentWeightTrackerBinding? = null
    private val binding get() = _binding!!

    private lateinit var lineChart: LineChart
    private val xValues = listOf("20/05", "21/05", "22/05", "23/05")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWeightTrackerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnAddWeight.setOnClickListener(this)

        lineChart = view.findViewById(R.id.chart)

        val description = Description().apply {
            text = "Weight Record"
            setPosition(150f, 15f)
        }
        lineChart.description = description
        lineChart.axisRight.setDrawLabels(false)

        val xAxis = lineChart.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.valueFormatter = IndexAxisValueFormatter(xValues)
        xAxis.labelCount = 4
        xAxis.granularity = 1f

        val yAxis = lineChart.axisLeft
        yAxis.axisMinimum = 0f
        yAxis.axisMaximum = 100f
        yAxis.axisLineWidth = 2f
        yAxis.axisLineColor = Color.BLACK
        yAxis.labelCount = 10

        val entries1 = listOf(
            Entry(0f, 10f),
            Entry(1f, 10f),
            Entry(2f, 15f),
            Entry(3f, 45f)
        )

        val dataSet1 = LineDataSet(entries1, "Weight").apply {
            color = Color.BLUE
        }

        val lineData = LineData(dataSet1)

        lineChart.data = lineData
        lineChart.invalidate()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnAddWeight -> {
                val addWeightFragment = AddWeightFragment()
                parentFragmentManager.commit {
                    addToBackStack(null)
                    replace(
                        R.id.fragment_container,
                        addWeightFragment,
                        AccountFragment::class.java.simpleName
                    )
                }
            }
        }
    }
}