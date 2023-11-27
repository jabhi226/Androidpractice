package com.example.mym_posdemomvvm.moduls.mposPoc.ui.activities

import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import androidx.appcompat.app.AppCompatActivity
import com.example.mym_posdemomvvm.databinding.ActivityStatusBinding
import com.example.mym_posdemomvvm.moduls.mposPoc.ui.adapters.ViewPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class StatusActivity : AppCompatActivity() {

    lateinit var binding: ActivityStatusBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStatusBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViewPager()
    }

    private fun initViewPager() {
        binding.viewPager.adapter = ViewPagerAdapter(getImages())
        CoroutineScope(Dispatchers.Default).launch {
            var i = 0
            while (i != 5) {
                delay(3000L)
                runOnUiThread {
                    binding.viewPager.currentItem = i
                }
                if (i == 4) {
                    this@StatusActivity.finish()
                } else {
                    i++
                }
            }
        }
        binding.tabLayout.setOnTabSelectedListener(object: OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.view?.let { scaleView(it, 1.0f, 1.3f) }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                tab?.view?.let { scaleView(it, 1.3f, 1.0f) }
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                tab?.view?.let { scaleView(it, 1.0f, 1.3f) }
            }

        })
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
        }.attach()
    }

    fun scaleView(v: View, startScale: Float, endScale: Float) {
        val anim: Animation = ScaleAnimation(
            1f, 1f,  // Start and end values for the X axis scaling
            startScale, endScale,  // Start and end values for the Y axis scaling
            Animation.RELATIVE_TO_SELF, 0f,  // Pivot point of X scaling
            Animation.RELATIVE_TO_SELF, 1f
        ) // Pivot point of Y scaling
        anim.fillAfter = true // Needed to keep the result of the animation
        anim.duration = 500
        v.startAnimation(anim)
    }


    private fun getImages(): List<Int> {
        val l = arrayListOf<Int>()
        for (i in 0..4) {
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                l.add(resources.getColor(R.color.design_default_color_primary_dark, null))
//            }

        }
        return listOf(1, 2, 3, 4, 5)
    }


}