package com.shangeyun.bottomsheetdiaprac

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog

class MainActivity : AppCompatActivity() {
    private lateinit var btnShowBottomSheet: Button
    private lateinit var btnShowBottomSheetDialog: Button
    private lateinit var btnShowBtShtDialogFragment: Button
    private lateinit var llBottomSheet:LinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnShowBottomSheet = findViewById(R.id.btn_show_bottom_sheet)
        llBottomSheet = findViewById(R.id.ll_bottom_sheet)
        btnShowBottomSheetDialog = findViewById(R.id.btn_show_bottom_sheet_dialog)
        btnShowBtShtDialogFragment = findViewById(R.id.btn_show_bt_sht_dialog_fragment)
        btnShowBottomSheet.setOnClickListener(View.OnClickListener {
            val behavior = BottomSheetBehavior.from(llBottomSheet)
            if (behavior.state == BottomSheetBehavior.STATE_EXPANDED) {
                //如果是展开状态，则关闭，反之亦然
                behavior.state = BottomSheetBehavior.STATE_COLLAPSED
            } else {
                behavior.state = BottomSheetBehavior.STATE_EXPANDED
            }
        })
        btnShowBottomSheetDialog.setOnClickListener(View.OnClickListener {
            val bottomSheetDialog = BottomSheetDialog(this)
            bottomSheetDialog.setContentView(R.layout.dialog_bottom_sheet)
            bottomSheetDialog.show()

        })
        btnShowBtShtDialogFragment.setOnClickListener{
            MyBottomSheetDialog().show(supportFragmentManager, "MyBottomSheetDialog")
        }


    }
}