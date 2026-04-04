package com.pedro.kanban.ui.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.pedro.kanban.R
import com.pedro.kanban.databinding.FragmentLoginBinding
import com.pedro.kanban.databinding.FragmentRecoverAccountBinding
import com.pedro.kanban.util.initToolbar
import com.pedro.kanban.util.showBottomSheet


class RecoverAccountFragment : Fragment() {

    private var _binding: FragmentRecoverAccountBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecoverAccountBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initToolbar(binding.toolbar)
        initListner()
    }

    private fun initListner(){

        binding.btnEnviar.setOnClickListener {
            validateData()
        }

    }

    private fun validateData(){

        val email = binding.inputEmail.text.toString().trim()

        if (email.isNotBlank()){
            Toast.makeText(requireContext(), "Um email foi enviado", Toast.LENGTH_SHORT).show()
        } else {
            showBottomSheet(menssage = R.string.emailEmpty)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}