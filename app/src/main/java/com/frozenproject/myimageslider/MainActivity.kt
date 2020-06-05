package com.frozenproject.myimageslider

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.abs

class MainActivity : AppCompatActivity() {

    private var list: ArrayList<SliderItem> = arrayListOf()
    private var sliderHandler: Handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        list.addAll(ImageSlide().listData)

        viewPagerImageSlider.adapter = SliderAdapter(list, viewPagerImageSlider)

        viewPagerImageSlider.clipToPadding = false
        viewPagerImageSlider.clipChildren = false
        viewPagerImageSlider.offscreenPageLimit = 3
        viewPagerImageSlider.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

        val compositePageTransformer = CompositePageTransformer()
        compositePageTransformer.addTransformer(MarginPageTransformer(40))
        compositePageTransformer.addTransformer { page, position ->
            val r = 1 - abs(position)
            page.scaleY = 0.85f + r * 0.15f
        }

        viewPagerImageSlider.setPageTransformer(compositePageTransformer)

        viewPagerImageSlider.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                sliderHandler.removeCallbacks(sliderRunnable)
                sliderHandler.postDelayed(sliderRunnable, 3000)
            }
        })
    }

    private var sliderRunnable =
        Runnable {
            viewPagerImageSlider.currentItem = viewPagerImageSlider.currentItem + 1
        }

    override fun onPause() {
        super.onPause()
        sliderHandler.removeCallbacks(sliderRunnable)
    }

    override fun onResume() {
        super.onResume()
        sliderHandler.postDelayed(sliderRunnable, 3000)
    }
}

