package com.pedro.kanban.util

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.DialogTitle
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.pedro.kanban.R
import com.pedro.kanban.databinding.BottomSheetBinding

fun Fragment.initToolbar(toolbar: Toolbar){
    (activity as AppCompatActivity).setSupportActionBar(toolbar)
    (activity as AppCompatActivity).title=""
    (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
    toolbar.setNavigationOnClickListener {
        activity?.onBackPressedDispatcher?.onBackPressed()


    }


}


fun Fragment.showBottomSheet(

    titleDialog: Int? = null,
    titlebutton: Int? = null,
    menssage: Int,
    onClick: () -> Unit ={}

){

    val bottomSheetDialog = BottomSheetDialog(requireContext(), R.style.BottomSheetDialog)
    val binding: BottomSheetBinding = BottomSheetBinding.inflate(layoutInflater, null, false)

    binding.textViewTitle.text = getText(titleDialog?: R.string.text_title_warning)
    binding.textViewMenssage.text = getText(menssage)
    binding.btnOK.text = getText(titleDialog?: R.string.btnText)
    binding.btnOK.setOnClickListener {
        onClick()
        bottomSheetDialog.dismiss()
    }

    bottomSheetDialog.setContentView(binding.root)
    bottomSheetDialog.show()



}