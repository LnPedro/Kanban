package com.pedro.kanban.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.pedro.kanban.R
import com.pedro.kanban.data.model.Status
import com.pedro.kanban.data.model.Task
import com.pedro.kanban.databinding.FragmentDoingBinding
import com.pedro.kanban.databinding.FragmentRecoverAccountBinding
import com.pedro.kanban.databinding.FragmentSplashBinding
import com.pedro.kanban.ui.adapter.TaskAdapter

class DoingFragment : Fragment() {
    private lateinit var taskAdapter: TaskAdapter

    private var _binding: FragmentDoingBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDoingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerViewTask()
        getTask()
    }

    private fun initRecyclerViewTask() {
        taskAdapter = TaskAdapter(requireContext()) {
            task, option -> optionSelected(task, option)
    }


        with(binding.recyclerViewTask) {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            adapter = taskAdapter
        }

    }

    private fun optionSelected(task:Task, option: Int){


    }

    private fun getTask() {

        val taskList = listOf(

            Task("1", "Tarefa Teste 1", Status.DOING),
            Task("2", "Tarefa Teste 2", Status.DOING)
        )
        taskAdapter.submitList(taskList)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}