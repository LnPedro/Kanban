package com.pedro.kanban.ui.auth

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.pedro.kanban.R
import com.pedro.kanban.databinding.FragmentLoginBinding
import com.pedro.kanban.util.showBottomSheet

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private lateinit var auth: FirebaseAuth


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = FirebaseAuth.getInstance()

        initListner()
    }

    private fun initListner() {

        binding.bntLogin.setOnClickListener {
            validateData()
        }

        binding.bntRegister.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }

        binding.bntRecover.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_recoverAccountFragment)
        }
    }
    private fun validateData() {

        val email = binding.inputEmail.text.toString().trim()
        val senha = binding.inputSenha.text.toString().trim()

        if (email.isNotBlank()) {
            if (senha.isNotBlank()) {
                Toast.makeText(requireContext(), "Bem Vindo!", Toast.LENGTH_SHORT).show()
                loginUser(email,senha)
            } else {
                showBottomSheet(menssage = getString(R.string.passwordEmpty))
            }
        } else {
            showBottomSheet(menssage = getString(R.string.emailEmpty))
        }

    }

    private fun loginUser(email: String, senha: String){
        try {
            auth.signInWithEmailAndPassword(email,senha)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful){
                        findNavController().navigate(R.id.action_global_homeFragment)
                    }else{
                        Toast.makeText(requireContext(), "erro", Toast.LENGTH_SHORT).show()
                    }
                }
        }catch (e: Exception){
            Toast.makeText(requireContext(), e.message.toString(), Toast.LENGTH_SHORT).show()
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}