package com.frozenproject.myimageslider

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.slide_item_container.view.*

class SliderAdapter(
    private val sliderItem: List<IntroSlide>
): RecyclerView.Adapter<SliderAdapter.SliderViewHolder>() {

    inner class SliderViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(data: IntroSlide){
            itemView.txt_title.text = data.title
            itemView.txt_description.text = data.description

            Glide.with(itemView.context)
                .load(data.icon)
                .into(itemView.imageSlideIcon)


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

    }

//    private var runnable = Runnable {
//        sliderItem.addAll(sliderItem)
//        notifyDataSetChanged()
//    }
}
