package com.example.mym_posdemomvvm.moduls.test.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.lifecycle.ViewModelProvider
import com.example.mym_posdemomvvm.R
import com.example.mym_posdemomvvm.databinding.ActivityFirstBinding
import com.example.mym_posdemomvvm.moduls.test.ui.fragments.FirstFragment
import com.example.mym_posdemomvvm.moduls.test.ui.fragments.SecondFragment
import com.example.mym_posdemomvvm.moduls.test.ui.viewModel.TestViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class FirstActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFirstBinding
    private lateinit var viewModel: TestViewModel
    private val scope =
        CoroutineScope(Dispatchers.Main + CoroutineExceptionHandler { _, throwable ->
            println(throwable.message)
        })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        println("---> onCreate")
        binding = ActivityFirstBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        initView()
        observeData()
    }

    private fun observeData() {
        scope.launch {
//            if (viewModel.currentFragment.value == null){
//                viewModel.currentFragment.emit(FirstFragment())
//            }
            viewModel.currentFragment.collectLatest {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.frame_first, it, it.javaClass.simpleName)
                    .addToBackStack(null)
                    .commit()
            }
//            viewModel.currentFragmentName.collectLatest {
//                val fragment: Fragment? = when (it){
//                    FirstFragment().javaClass.simpleName -> {
//                        FirstFragment()
//                    }
//                    SecondFragment().javaClass.simpleName -> {
//                        SecondFragment()
//                    }
//                    else -> {
//                        null
//                    }
//                }
//                fragment?.let {f ->
//                    supportFragmentManager.beginTransaction()
//                        .replace(R.id.frame_first, f, f.javaClass.simpleName)
//                        .addToBackStack(null)
//                        .commit()
//                }
//            }
        }
    }

    private fun initView() {
        viewModel = ViewModelProvider(this)[TestViewModel::class.java]
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

    override fun onBackPressed() {
        super.onBackPressed()
        supportFragmentManager.findFragmentById(R.id.frame_first).let {
            scope.launch {
                when (it) {
                    is FirstFragment -> {
                        this@FirstActivity.finish()
                    }
                    is SecondFragment -> {
                        viewModel.currentFragment.emit(FirstFragment())
//                        viewModel.currentFragmentName.emit(FirstFragment().javaClass.simpleName)
                    }
                }
            }
        }
    }
}