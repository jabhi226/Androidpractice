package com.example.mym_posdemomvvm.moduls.test.ui.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.mym_posdemomvvm.databinding.FragmentFirstBinding
import com.example.mym_posdemomvvm.moduls.test.ui.activity.FirstActivity
import com.example.mym_posdemomvvm.moduls.test.ui.viewModel.TestViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class FirstFragment : Fragment() {


    private lateinit var binding: FragmentFirstBinding
    private lateinit var viewModel: TestViewModel

    private val scope =
        CoroutineScope(Dispatchers.Main + CoroutineExceptionHandler { _, throwable ->
            println(throwable.message)
        })

    override fun onAttach(context: Context) {
        super.onAttach(context)
        println("---> FirstFragment onAttach")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        println("---> FirstFragment onCreate")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        println("---> FirstFragment onCreateView")
        binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        println("---> FirstFragment onViewCreated")
        initView()
    }

    @SuppressLint("SetTextI18n")
    private fun initView() {
        viewModel = ViewModelProvider(requireActivity())[TestViewModel::class.java]
        lifecycleScope.launch {
            delay(5000)
            binding.btn1.text =
                "Text changed after 5 Seconds but it will set to default after screen rotation"
        }
        binding.btn1.setOnClickListener {
            (requireActivity() as FirstActivity).replaceFragment(SecondFragment())
        }

        lifecycleScope.launch {
            viewModel.fragment1Count.observe(viewLifecycleOwner) {
                binding.text.text = "Count: $it"
            }
        }
    }

    override fun onStart() {
        super.onStart()
        println("---> FirstFragment onStart")
    }

    override fun onResume() {
        super.onResume()
        println("---> FirstFragment onResume")
    }

    override fun onPause() {
        super.onPause()
        println("---> FirstFragment onPause")
    }

    override fun onStop() {
        super.onStop()
        println("---> FirstFragment onStop")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        println("---> FirstFragment onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        println("---> FirstFragment onDestroy")
    }

    override fun onDetach() {
        super.onDetach()
        println("---> FirstFragment onDetach")
    }
}