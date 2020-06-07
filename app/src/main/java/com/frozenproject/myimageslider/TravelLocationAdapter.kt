package com.frozenproject.myimageslider

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_container_location.view.*

class TravelLocationAdapter(private val travelLocation: List<TravelLocation>): RecyclerView.Adapter<TravelLocationAdapter.TravelLocationViewHolder>() {

    inner class TravelLocationViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(data: TravelLocation){
            itemView.textTitle.text = data.title
            itemView.textLocation.text = data.location
            itemView.textStarRating.text = data.starRating.toString()

            Glide.with(itemView.context)
                .load(data.imageUrl)
                .into(itemView.kbvLocation)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TravelLocationViewHolder {
        return TravelLocationViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_container_location,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return travelLocation.size
    }

    override fun onBindViewHolder(holder: TravelLocationViewHolder, position: Int) {
        holder.bind(travelLocation[position])
    }
}