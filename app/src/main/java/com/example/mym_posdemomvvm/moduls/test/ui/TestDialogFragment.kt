package com.example.mym_posdemomvvm.moduls.test.ui

import android.app.Dialog
import android.graphics.Bitmap
import android.graphics.Rect
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.renderscript.Allocation
import android.renderscript.Element
import android.renderscript.RenderScript
import android.renderscript.ScriptIntrinsicBlur
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.mym_posdemomvvm.R
import com.example.mym_posdemomvvm.databinding.DialogFragmentTestBinding


class TestDialogFragment : DialogFragment() {

    lateinit var binding: DialogFragmentTestBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = DialogFragmentTestBinding.inflate(layoutInflater)
        val builder = AlertDialog.Builder(requireActivity())
        builder.setView(binding.root)

        return builder.create()

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            200,
            200
        )
        dialog?.window?.setBackgroundDrawableResource(R.color.transparent)
        dialog?.setCanceledOnTouchOutside(false)
        dialog?.window?.addFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND);

        val view = requireActivity().window.decorView
        view.isDrawingCacheEnabled = true
        val b1 = view.drawingCache

        val frame = Rect()
        requireActivity().window.decorView.getWindowVisibleDisplayFrame(frame)
        val statusBarHeight = frame.top
        val width = requireActivity().window.decorView.width
        val height = requireActivity().window.decorView.height

        val b = Bitmap.createBitmap(b1, 0, 300, width, height - 300)

        //define this only once if blurring multiple times

        //define this only once if blurring multiple times
        val rs = RenderScript.create(activity)

        //this will blur the bitmapOriginal with a radius of 8 and save it in bitmapOriginal

        //this will blur the bitmapOriginal with a radius of 8 and save it in bitmapOriginal
        val input = Allocation.createFromBitmap(
            rs,
            b
        ) //use this constructor for best performance, because it uses USAGE_SHARED mode which reuses memory

        val output = Allocation.createTyped(rs, input.type)
        val script = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs))
        script.setRadius(8f)
        script.setInput(input)
        script.forEach(output)
        output.copyTo(b)

        dialog?.window?.setBackgroundDrawable(BitmapDrawable(resources, b))


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}