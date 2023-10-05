package com.example.mym_posdemomvvm.moduls.test.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.mym_posdemomvvm.R
import com.example.mym_posdemomvvm.databinding.ActivityFirstBinding
import com.example.mym_posdemomvvm.moduls.test.ui.fragments.FirstFragment
import com.example.mym_posdemomvvm.moduls.test.ui.viewModel.TestViewModel
import com.example.mym_posdemomvvm.utils.Utils.getStoredFragment
import kotlinx.coroutines.launch

class FirstActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFirstBinding
    private val viewModel by viewModels<TestViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        println("---> onCreate")
        binding = ActivityFirstBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        initView()
        observeData()

        if (savedInstanceState != null) {
            savedInstanceState.getStoredFragment(supportFragmentManager, "myFragmentName")?.let {
                replaceFragment(it)
            }
        } else {
            replaceFragment(FirstFragment())
        }
    }

    private fun observeData() {
        lifecycleScope.launch {
            viewModel.progress.observe(this@FirstActivity){
                binding.pBar3.progress = it
                when {
                    it >= 100 -> {
                        binding.ivP3.setImageResource(R.drawable.pokemon_black)
                    }
                    it >= 50 -> {
                        binding.ivP2.setImageResource(R.drawable.pokemon_black)
                    }
                    it >= 10 -> {
                        binding.ivP1.setImageResource(R.drawable.pokemon_black)
                    }
                    else -> {
                        binding.ivP1.setImageResource(R.drawable.pokemon_grey)
                        binding.ivP2.setImageResource(R.drawable.pokemon_grey)
                        binding.ivP3.setImageResource(R.drawable.pokemon_grey)
                    }
                }
            }
        }
    }

    fun replaceFragment(it: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(binding.frameFirst.id, it, it.javaClass.simpleName)
            .addToBackStack(it.javaClass.simpleName)
            .commit()
    }

    private fun initView() {
        binding.btn1.setOnClickListener {
            val i = Intent(this, SecondActivity::class.java)
            startActivity(i)
        }
    }

    override fun onStart() {
        super.onStart()
        println("---> onStart")
    }

    override fun onResume() {
        super.onResume()
        println("---> onResume")
    }

    override fun onPause() {
        super.onPause()
        println("---> onPause")
    }

    override fun onStop() {
        super.onStop()
        println("---> onStop")
    }

    override fun onRestart() {
        super.onRestart()
        println("---> onRestart")
    }

    override fun onDestroy() {
        super.onDestroy()
        println("---> onDestroy")
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)

        supportFragmentManager.findFragmentById(binding.frameFirst.id)?.let {
            supportFragmentManager.putFragment(outState, "myFragmentName", it)
        }
    }

    /*

    Example an activity have 2 fragments and we use FragmentManager to replace/add with addToBackstack each fragment to a layout in activity

    Use replace

    Go Fragment1

    Fragment1: onAttach
    Fragment1: onCreate
    Fragment1: onCreateView
    Fragment1: onActivityCreated
    Fragment1: onStart
    Fragment1: onResume

    Go Fragment2

    Fragment2: onAttach
    Fragment2: onCreate
    Fragment1: onPause
    Fragment1: onStop
    Fragment1: onDestroyView
    Fragment2: onCreateView
    Fragment2: onActivityCreated
    Fragment2: onStart
    Fragment2: onResume

    Pop Fragment2

    Fragment2: onPause
    Fragment2: onStop
    Fragment2: onDestroyView
    Fragment2: onDestroy
    Fragment2: onDetach
    Fragment1: onCreateView
    Fragment1: onStart
    Fragment1: onResume

    Pop Fragment1

    Fragment1: onPause
    Fragment1: onStop
    Fragment1: onDestroyView
    Fragment1: onDestroy
    Fragment1: onDetach

    Use add

    Go Fragment1

    Fragment1: onAttach
    Fragment1: onCreate
    Fragment1: onCreateView
    Fragment1: onActivityCreated
    Fragment1: onStart
    Fragment1: onResume

    Go Fragment2

    Fragment2: onAttach
    Fragment2: onCreate
    Fragment2: onCreateView
    Fragment2: onActivityCreated
    Fragment2: onStart
    Fragment2: onResume

    Pop Fragment2

    Fragment2: onPause
    Fragment2: onStop
    Fragment2: onDestroyView
    Fragment2: onDestroy
    Fragment2: onDetach

    Pop Fragment1

    Fragment1: onPause
    Fragment1: onStop
    Fragment1: onDestroyView
    Fragment1: onDestroy
    Fragment1: onDetach

     */
}