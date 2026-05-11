package com.pedro.kanban.ui.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.pedro.kanban.R
import com.pedro.kanban.databinding.FragmentRegisterBinding
import com.pedro.kanban.util.initToolbar
import com.pedro.kanban.util.showBottomSheet
import kotlin.math.sign

class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    //Variavel auth

    private lateinit var auth: FirebaseAuth




    private fun registerUser(email: String, senha: String){
        try{
            val auth = FirebaseAuth.getInstance()
            auth.createUserWithEmailAndPassword(email,senha)
                .addOnCompleteListener { task ->
                    if(task.isSuccessful){
                        //Encaminha pra proximo tela
                        findNavController().navigate(R.id.homeFragment)
                    } else{
                        //Encaminha pra proximo tela
                        Toast.makeText(requireContext(), task.exception?.message, Toast.LENGTH_SHORT).show()
                    }
                }
        }catch(e: Exception) {
            Toast.makeText(requireContext(), e.message.toString(), Toast.LENGTH_SHORT).show()
        }
    }


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
                binding.progressBar.isVisible=true
                Toast.makeText(requireContext(), "Bem Vindo!", Toast.LENGTH_SHORT).show()
                registerUser(email,senha)
                findNavController().navigate(R.id.action_global_homeFragment)
            } else {

                binding.progressBar.isVisible=false
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