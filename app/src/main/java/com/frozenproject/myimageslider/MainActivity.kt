package com.frozenproject.myimageslider

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.abs

class MainActivity : AppCompatActivity() {

    private var travelLocationAdapter = TravelLocationAdapter(
            listOf(
                TravelLocation(
                    "France",
                    "Eiffel Tower",
                    "https://www.infinityandroid.com/images/france_eiffel_tower.jpg",
                    4.8f
                ),
                TravelLocation(
                    "Indonesia",
                    "Mountain View",
                    "https://www.infinityandroid.com/images/indonesia_mountain_view.jpg",
                    4.8f
                ),
                TravelLocation(
                    "India",
                    "Taj Mahal",
                    "https://www.infinityandroid.com/images/india_taj_mahal.jpg",
                    4.3f
                ),
                TravelLocation(
                    "Canada",
                    "Lake View",
                    "https://www.infinityandroid.com/images/canada_lake_view.jpg",
                    4.2f
                )
            )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        locationViewPager.adapter = travelLocationAdapter

        locationViewPager.clipToPadding = false
        locationViewPager.clipChildren = false
        locationViewPager.offscreenPageLimit = 3
        locationViewPager.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

        val compositePageTransformer = CompositePageTransformer()
        compositePageTransformer.addTransformer(MarginPageTransformer(40))
        compositePageTransformer.addTransformer { page, position ->
            val r = 1 - abs(position)
            page.scaleY = 0.95f + r * 0.05f
        }

        locationViewPager.setPageTransformer(compositePageTransformer)

    }

}

