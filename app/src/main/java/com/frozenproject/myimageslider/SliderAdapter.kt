package com.frozenproject.myimageslider

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.makeramen.roundedimageview.RoundedImageView

class SliderAdapter(
    private var sliderItem: ArrayList<SliderItem>,
    private var viewPager2: ViewPager2
): RecyclerView.Adapter<SliderAdapter.SliderViewHolder>() {

    inner class SliderViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var imageView: RoundedImageView? = null

        fun bind(data: SliderItem){
            imageView = itemView.findViewById(R.id.imageSlide)
            imageView?.setImageResource(data.image)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderViewHolder {
        return SliderViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.slide_item_container,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return sliderItem.size
    }

    override fun onBindViewHolder(holder: SliderViewHolder, position: Int) {
        holder.bind(sliderItem[position])
        if (position == sliderItem.size - 2){
            viewPager2.post(runnable)
        }
    }

    private var runnable = Runnable {
        sliderItem.addAll(sliderItem)
        notifyDataSetChanged()
    }
}
