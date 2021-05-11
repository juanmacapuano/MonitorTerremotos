package com.juanmacapuano.terremotoslive.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.juanmacapuano.terremotoslive.databinding.FragmentDetailEqBinding
import com.juanmacapuano.terremotoslive.service.data.*
import com.juanmacapuano.terremotoslive.viewmodel.EarthQuakeViewModel
import java.text.SimpleDateFormat
import java.util.*

class DetailEqFragment : Fragment() {

    private var earthQuake = EarthQuake()

    private lateinit var binding: FragmentDetailEqBinding
    private val viewModelEq: EarthQuakeViewModel by activityViewModels()

    companion object {
        const val EARTHQUAKE = "EarthQuake"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        earthQuake.place = arguments?.getString(COLUMN_EARTHQUAKE_PLACE).toString()
        earthQuake.magnitude = arguments?.getDouble(COLUMN_EARTHQUAKE_MAGNITUDE) ?: 0.0
        earthQuake.time = arguments?.getLong(COLUMN_EARTHQUAKE_TIME) ?: 0
        earthQuake.latitude = arguments?.getDouble(COLUMN_EARTHQUAKE_LATITUDE) ?: 0.0
        earthQuake.longitude = arguments?.getDouble(COLUMN_EARTHQUAKE_LONGITUDE) ?: 0.0
    }

    private fun convertLongTime(time: Long): String {
        val date = Date(time)
        val format = SimpleDateFormat("dd.MM.yyyy")
        return format.format(date)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentDetailEqBinding.inflate(inflater, container, false)
        binding.earthQuake = earthQuake
        binding.tvTime.text = convertLongTime(earthQuake.time)
        return binding.root
    }
}