package com.frozenproject.myimageslider

class ImageSlide {
    private val imageSlider = intArrayOf(
        R.drawable.img1,
        R.drawable.img2,
        R.drawable.img3,
        R.drawable.img4,
        R.drawable.img5
    )

    val listData : ArrayList<SliderItem>
        get() {
            val list = ArrayList<SliderItem>()
            for (position in imageSlider.indices){
                val img = SliderItem()
                img.image = imageSlider[position]

                list.add(img)
            }

            return list
        }
}

