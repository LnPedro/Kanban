package com.pedro.kanban.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.pedro.kanban.R
import com.pedro.kanban.databinding.FragmentDoingBinding
import com.pedro.kanban.databinding.FragmentTodoBinding

class TodoFragment : Fragment() {
        private var _binding: FragmentTodoBinding? = null
        private val binding get() = _binding!!

        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View {
            _binding = FragmentTodoBinding.inflate(inflater, container, false)


            return binding.root
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
            initListeners()
        }


    private fun initListeners(){
        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.formTaskFragment)
        }

    }

        override fun onDestroyView() {
            super.onDestroyView()
            _binding = null
        }

    }
