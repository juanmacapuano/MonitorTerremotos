package com.juanmacapuano.terremotoslive.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.juanmacapuano.terremotoslive.R
import com.juanmacapuano.terremotoslive.databinding.EqListItemBinding
import com.juanmacapuano.terremotoslive.service.data.EarthQuake
import com.juanmacapuano.terremotoslive.service.data.EqResponse

class EarthQuakeAdapter(private val clickListener: (EarthQuake) -> Unit, private val context: Context): RecyclerView.Adapter<EarthQuakeAdapter.EarthQuakeHolder>() {

    private var listEarthQuake = ArrayList<EarthQuake>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EarthQuakeHolder {
        val binding = EqListItemBinding.inflate(LayoutInflater.from(parent.context))
        return EarthQuakeHolder(binding)
    }

    override fun onBindViewHolder(holder: EarthQuakeAdapter.EarthQuakeHolder, position: Int) {
        holder.bind(listEarthQuake[position], clickListener, context)
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

        fun bind(earthQuake: EarthQuake, clickListener: (EarthQuake) -> Unit, context: Context) {
            binding.eqPlaceText.text = earthQuake.place
            binding.eqMagnitudeText.text = context.getString(R.string.magnitude_format,earthQuake.magnitude)
            binding.eqArrowImage.setOnClickListener {
                clickListener(earthQuake)
            }
            binding.executePendingBindings()
        }

    }
}
