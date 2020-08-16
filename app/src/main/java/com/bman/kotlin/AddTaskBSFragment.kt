package com.bman.kotlin

import android.os.Bundle
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.bman.kotlin.R
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText

class AddTaskBSFragment : BottomSheetDialogFragment() {

    private lateinit var viewModel: AllTasksViewModel;
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_task_b_s_list_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val btn = view.findViewById<MaterialButton>(R.id.btn_add_task)
        val etTask = view.findViewById<TextInputEditText>(R.id.tiet_task)
        btn.setOnClickListener {
//            if ()
//            viewModel.addTask(etTask.text.toString())
//            dismiss()
            etTask.text.toString().apply {
                if (isNotEmpty()) {
                    viewModel.addTask(this)
                    dismiss()
                }
            }
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AllTasksViewModel::class.java)
    }

}