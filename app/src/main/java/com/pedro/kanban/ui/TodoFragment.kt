package com.pedro.kanban.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.pedro.kanban.R
import com.pedro.kanban.databinding.FragmentDoingBinding
import com.pedro.kanban.databinding.FragmentTodoBinding

class TodoFragment : Fragment() {
    class DoingFragment : Fragment() {
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

        override fun onDestroyView() {
            super.onDestroyView()
            _binding = null
        }

    }
}