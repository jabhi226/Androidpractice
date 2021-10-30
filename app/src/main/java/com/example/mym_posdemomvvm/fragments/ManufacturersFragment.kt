package com.example.mym_posdemomvvm.fragments

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.view.allViews
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mym_posdemomvvm.databinding.FragmentManufacturesBinding
import com.example.mym_posdemomvvm.databinding.ItemMedicineItemBinding
import com.example.mym_posdemomvvm.models.Manufacture
import com.example.mym_posdemomvvm.viewmodels.ManufactureViewModel

class ManufacturersFragment : Fragment(), View.OnClickListener, TextWatcher {

    private var mBinding: FragmentManufacturesBinding? = null
    private val binding get() = mBinding

    private var manufactureAdapter: ManufactureAdapter? = null

    private var manufactureViewModel: ManufactureViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentManufacturesBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mBinding = null
    }

    override fun onDestroy() {
        super.onDestroy()
        mBinding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListener()
        initViewModel()
        setManufactureRecycler()
    }

    private fun initListener() {
        binding?.apply {
            save.setOnClickListener(this@ManufacturersFragment)
            delete.setOnClickListener(this@ManufacturersFragment)
            manufactureEmail.addTextChangedListener(this@ManufacturersFragment)
            manufactureName.addTextChangedListener(this@ManufacturersFragment)
            manufactureWebsite.addTextChangedListener(this@ManufacturersFragment)
            isActive.addTextChangedListener(this@ManufacturersFragment)
            isGlobal.addTextChangedListener(this@ManufacturersFragment)
        }
    }

    private fun setManufactureRecycler() {
        manufactureAdapter = ManufactureAdapter(manufactureViewModel, requireContext())
        binding?.manufactureRecycler?.layoutManager = LinearLayoutManager(context)
        binding?.manufactureRecycler?.adapter = manufactureAdapter
    }

    private fun initViewModel() {
        manufactureViewModel = ViewModelProvider(this)[ManufactureViewModel::class.java]
        manufactureViewModel?.allManufactures?.observe(viewLifecycleOwner, {
            if (it != null){
                manufactureAdapter?.submitList(it)
            }
        })
        manufactureViewModel?.isUpdate?.observe(viewLifecycleOwner, { isUpdate ->
            if (isUpdate != null){
                binding?.apply {
                    if (isUpdate == true){
                        save.text = "Update"
                    } else {
                        save.text = "Save"
                    }
                    manufactureViewModel?.manufactureToUpdate?.apply {
                        manufactureName.setText(manufacturerName)
                        manufactureEmail.setText(manufacturerEmail)
                        manufactureWebsite.setText(manufacturer_website)
                        binding?.isGlobal?.setText(if (isGlobal) "0" else "1")
                        binding?.isActive?.setText(if (isActive) "0" else "1")
                    }
                }
            }
        })
    }

    override fun onClick(v: View?) {
        binding.apply {
            when (v?.id) {
                this?.save?.id -> {
                    val m = Manufacture(
                        this?.manufactureName?.text.toString(),
                        this?.manufactureEmail?.text.toString(),
                        this?.manufactureWebsite?.text.toString(),
                        this?.isGlobal?.text.toString().toInt() == 0,
                        this?.isActive?.text.toString().toInt() == 0
                        )
                    manufactureViewModel?.apply {
                        if (isUpdate.value != null && !isUpdate.value!!){
                            repositoryMPos.insertManufacture(m)
                        } else {
                            m.manufactureId = manufactureToUpdate?.manufactureId!!
                            repositoryMPos.updateManufacture(m)
                        }
                    }
                }
                this?.delete?.id -> {
                    manufactureViewModel?.repositoryMPos?.deleteAllManufacture()
                }
            }
        }
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        val all = binding?.root?.allViews
        var isValid = true
        kotlin.run lit@ {
            all?.forEach {
                if (it is EditText && it.text.isEmpty()){
                    isValid = false
                    return@lit
                }
            }
        }
        binding?.save?.isClickable = isValid
        binding?.save?.isEnabled = isValid
    }

    override fun afterTextChanged(s: Editable?) {
    }
}

class ManufactureAdapter(private val manufactureViewModel: ManufactureViewModel?, val context: Context) : ListAdapter<Manufacture, ManufactureAdapter.ManufactureItemHolder>(
    object : DiffUtil.ItemCallback<Manufacture>(){
        override fun areItemsTheSame(oldItem: Manufacture, newItem: Manufacture): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Manufacture, newItem: Manufacture): Boolean {
            return oldItem == newItem
        }

    }) {

    private lateinit var binding: ItemMedicineItemBinding

    class ManufactureItemHolder(private val itemHolder: ItemMedicineItemBinding): RecyclerView.ViewHolder(itemHolder.root) {
        fun bind(
            manufacture: Manufacture?,
            manufactureViewModel: ManufactureViewModel?,
            context: Context
        ) {
            itemHolder.medicineName.text = manufacture?.manufacturerName
            itemHolder.divisor.text = manufacture?.manufacturerEmail
            itemHolder.isH1.text = manufacture?.isActive.toString()
            itemHolder.root.setOnClickListener {
                manufactureViewModel?.manufactureToUpdate = manufacture
                manufactureViewModel?.isUpdate?.value = !manufactureViewModel?.isUpdate?.value!!
            }
            itemHolder.root.setOnLongClickListener {
                val builder: AlertDialog.Builder = AlertDialog.Builder(context)
                builder.setMessage("Are you sure?")
                builder.setTitle("Delete manufacture?")
                builder.setPositiveButton("Yes") { _, _ -> if (manufacture != null) { manufactureViewModel?.repositoryMPos?.deleteManufacture(manufacture) } }
                builder.setNegativeButton("NO") { dialog, _ -> dialog.dismiss() }
                builder.show()
                return@setOnLongClickListener false
            }
            Log.d("MANUFACTURE_ID -> ", "${manufacture?.manufactureId.toString()} | ${manufacture?.manufacturerName}")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ManufactureItemHolder {
        binding = ItemMedicineItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ManufactureItemHolder(binding)
    }

    override fun onBindViewHolder(holder: ManufactureItemHolder, position: Int) {
        holder.bind(getItem(position), manufactureViewModel, context)
    }
}
