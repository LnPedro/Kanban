package com.pedro.kanban.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.pedro.kanban.R
import com.pedro.kanban.databinding.FragmentFormTaskBinding
import com.pedro.kanban.util.initToolbar
import com.pedro.kanban.util.showBottomSheet

class FormTaskFragment : Fragment() {

    private var _binding: FragmentFormTaskBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFormTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar(binding.toolbar)
        initListner()
    }

    private fun initListner(){

        binding.btnSave.setOnClickListener {
            validateData()
        }

    }
    private fun validateData(){

        val description = binding.editTextDescricao.text.toString().trim()

        if (description.isNotBlank()){
            Toast.makeText(requireContext(), "Nova tarefa criada", Toast.LENGTH_SHORT).show()
        } else {

            showBottomSheet(menssage = getString(R.string.descriptionEmpty))
        }
    }



    override fun onDestroyView() {
        super.onDestroyView()
    }

}