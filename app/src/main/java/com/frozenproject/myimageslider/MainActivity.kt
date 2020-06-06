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

    private val introSliderAdapter = SliderAdapter(
        listOf(
            IntroSlide(
                "Painting School",
                "Painting School is a Paint learning platform online",
                R.drawable.img1
            ),
            IntroSlide(
                "Class Online",
                "You can paint your imagination",
                R.drawable.img2
            ),
            IntroSlide(
                "Portfolio",
                "You can share your portfolio",
                R.drawable.img3
            )
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        introSliderViewPager.adapter = introSliderAdapter

        setupIndicators()
        setCurrentIndicator(0)

        introSliderViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setCurrentIndicator(position)
            }
        })

        buttonNext.setOnClickListener {
            if (introSliderViewPager.currentItem + 1 < introSliderAdapter.itemCount){
                introSliderViewPager.currentItem += 1
            }else{
                startActivity(Intent(applicationContext, AnotherActivity::class.java))
            }
        }

        textSkipIntro.setOnClickListener {
            startActivity(Intent(applicationContext, AnotherActivity::class.java))
        }
    }

    private fun setupIndicators(){
        val indicators = arrayOfNulls<ImageView>(introSliderAdapter.itemCount)
        val layoutParams: LinearLayout.LayoutParams = LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)

        layoutParams.setMargins(8, 0, 8,0)
        for (i in indicators.indices){
            indicators[i] = ImageView(applicationContext)
            indicators[i].apply {
                this?.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive
                    )
                )
                this?.layoutParams = layoutParams
            }

            indicatorsContainer.addView(indicators[i])
        }
    }

    private fun setCurrentIndicator(index: Int){
        val childCount = indicatorsContainer.childCount

        for (i in 0 until childCount){
            val imageView = indicatorsContainer[i] as ImageView
            if (i == index){
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_active
                    )
                )
            }else{
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive
                    )
                )
            }
        }

        if(index == 2){
            buttonNext.visibility = View.VISIBLE
        }else{
            buttonNext.visibility = View.INVISIBLE
        }
    }
}

