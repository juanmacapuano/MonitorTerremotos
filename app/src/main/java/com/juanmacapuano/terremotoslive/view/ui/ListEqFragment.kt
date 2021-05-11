package com.juanmacapuano.terremotoslive.view.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.juanmacapuano.terremotoslive.R
import com.juanmacapuano.terremotoslive.databinding.FragmentListEqBinding
import com.juanmacapuano.terremotoslive.service.api.StatusResponseAPI
import com.juanmacapuano.terremotoslive.service.data.*
import com.juanmacapuano.terremotoslive.view.adapter.EarthQuakeAdapter
import com.juanmacapuano.terremotoslive.viewmodel.EarthQuakeViewModel

class ListEqFragment : Fragment() {

    lateinit var binding: FragmentListEqBinding
    lateinit var mAdapterEq: EarthQuakeAdapter
    private val earthQuakeViewModel: EarthQuakeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_list_eq,
            container,
            false
        )

        mAdapterEq = EarthQuakeAdapter(
            { selectedItem: EarthQuake -> itemClicked(selectedItem) },
            requireContext()
        )

        //fragment's view lifecycle
        binding.lifecycleOwner = viewLifecycleOwner

        binding.rvEarthQuake.apply {
            adapter = mAdapterEq
            layoutManager = LinearLayoutManager(requireContext())
        }

        // Inflate the layout for this fragment
        return binding.root
    }

    private fun itemClicked(selectedItem: EarthQuake) {
        val bundle = Bundle()
        bundle.putString(COLUMN_EARTHQUAKE_PLACE, selectedItem.place)
        bundle.putDouble(COLUMN_EARTHQUAKE_MAGNITUDE, selectedItem.magnitude)
        bundle.putDouble(COLUMN_EARTHQUAKE_LATITUDE, selectedItem.latitude)
        bundle.putDouble(COLUMN_EARTHQUAKE_LONGITUDE, selectedItem.longitude)
        bundle.putLong(COLUMN_EARTHQUAKE_TIME, selectedItem.time)
        findNavController().navigate(R.id.action_listEqFragment_to_detailEqFragment, bundle)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeUI()
    }

    private fun subscribeUI() {
        earthQuakeViewModel.earthQuakeList.observe(viewLifecycleOwner, {
            mAdapterEq.setListEq(it)
            if (it.size == 0) {
                binding.tvEmptyList.visibility = View.VISIBLE
            } else
                binding.tvEmptyList.visibility = View.GONE
        })

        earthQuakeViewModel.statusLoading.observe(viewLifecycleOwner, { status: StatusResponseAPI ->
            when (status) {
                StatusResponseAPI.LOADING -> {
                    binding.lpbStatus.visibility = View.VISIBLE
                }
                StatusResponseAPI.DONE -> {
                    binding.lpbStatus.visibility = View.GONE
                }
                StatusResponseAPI.ERROR -> {
                    binding.lpbStatus.visibility = View.GONE
                }
            }

        })
    }
}