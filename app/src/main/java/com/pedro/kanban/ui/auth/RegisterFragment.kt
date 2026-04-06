package com.pedro.kanban.ui.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.pedro.kanban.R
import com.pedro.kanban.databinding.FragmentRegisterBinding
import com.pedro.kanban.util.initToolbar
import com.pedro.kanban.util.showBottomSheet

class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar(binding.toolbar)
        initListner()

    }

    private fun initListner() {

        binding.btnEnviar.setOnClickListener {
            validateData()
        }
    }

    private fun validateData() {

        val email = binding.inputEmail.text.toString().trim()
        val senha = binding.inputSenha.text.toString().trim()

        if (email.isNotBlank()) {
            if (senha.isNotBlank()) {
                Toast.makeText(requireContext(), "Bem Vindo!", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_global_homeFragment)
            } else {
                showBottomSheet(menssage = getString(R.string.passwordEmpty))
            }
        } else {
            showBottomSheet(menssage = getString(R.string.emailEmpty))
        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}