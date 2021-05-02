package com.juanmacapuano.terremotoslive.view.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.juanmacapuano.terremotoslive.R
import com.juanmacapuano.terremotoslive.databinding.FragmentListEqBinding
import com.juanmacapuano.terremotoslive.service.data.EarthQuake
import com.juanmacapuano.terremotoslive.view.adapter.EarthQuakeAdapter
import com.juanmacapuano.terremotoslive.viewmodel.EarthQuakeViewModel

class ListEqFragment : Fragment() {

    lateinit var binding: FragmentListEqBinding
    lateinit var mAdapterEq : EarthQuakeAdapter
    private val earthQuakeViewModel: EarthQuakeViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_list_eq,
            container,
            false
        )

        mAdapterEq = EarthQuakeAdapter({selectedItem: EarthQuake -> itemClicked(selectedItem)})

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
        Toast.makeText(context, "${selectedItem.place}", Toast.LENGTH_SHORT).show()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        suscribeUI()
    }

    private fun suscribeUI() {
        earthQuakeViewModel.listEqResponseLiveData.observe(viewLifecycleOwner, Observer {
            mAdapterEq.setListEq(it)
        })
    }
}