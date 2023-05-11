package com.shangeyun.bottomsheetdiaprac

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class MyBottomSheetDialog : BottomSheetDialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        val view = LayoutInflater.from(context).inflate(R.layout.dialog_my_bottom_sheet, null)
        dialog.setContentView(view)
//        initView(view)
        return dialog
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var tvCancel: TextView = view.findViewById(R.id.tv_cancel)
        tvCancel.setOnClickListener{
            //这个dismiss不起作用啊
            dismiss()
        }
    }

    /*private fun initView(rootView: View) {
        //do something
        rootView.tv_cancel.setOnClickListener { dismiss() }


    }*/
}
