package com.juanmacapuano.terremotoslive.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.juanmacapuano.terremotoslive.databinding.EqListItemBinding
import com.juanmacapuano.terremotoslive.service.data.EarthQuake
import com.juanmacapuano.terremotoslive.service.data.EqResponse

class EarthQuakeAdapter(private val clickListener: (EarthQuake) -> Unit): RecyclerView.Adapter<EarthQuakeAdapter.EarthQuakeHolder>() {

    private var listEarthQuake = ArrayList<EarthQuake>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EarthQuakeHolder {
        val binding = EqListItemBinding.inflate(LayoutInflater.from(parent.context))
        return EarthQuakeHolder(binding)
    }

    override fun onBindViewHolder(holder: EarthQuakeAdapter.EarthQuakeHolder, position: Int) {
        holder.bind(listEarthQuake[position], clickListener)
    }

    override fun getItemCount(): Int {
        return listEarthQuake.size
    }

    fun setListEq(listEq: List<EarthQuake>) {
        listEarthQuake.clear()
        listEarthQuake.addAll(listEq)
        notifyDataSetChanged()
    }

    class EarthQuakeHolder(private val binding: EqListItemBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(earthQuake: EarthQuake, clickListener: (EarthQuake) -> Unit) {
            binding.eqPlaceText.text = earthQuake.place
            binding.eqMagnitudeText.text = earthQuake.magnitude.toString()
            binding.eqArrowImage.setOnClickListener {
                clickListener(earthQuake)
            }
            binding.executePendingBindings()
        }

    }
}
