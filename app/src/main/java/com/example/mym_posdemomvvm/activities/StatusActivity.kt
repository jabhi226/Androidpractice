package com.example.mym_posdemomvvm.activities

import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import com.example.mym_posdemomvvm.R
import com.example.mym_posdemomvvm.adapters.ViewPagerAdapter
import com.example.mym_posdemomvvm.databinding.ActivityStatusBinding
import com.google.android.material.tabs.TabLayout
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
        binding.tabLayout
        TabLayoutMediator(binding.tabLayout, binding.viewPager
        ) { tab, position ->
        }.attach()
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