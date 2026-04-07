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
import com.pedro.kanban.databinding.FragmentDoneBinding
import com.pedro.kanban.databinding.FragmentRecoverAccountBinding
import com.pedro.kanban.databinding.FragmentSplashBinding
import com.pedro.kanban.ui.adapter.TaskAdapter

class DoneFragment : Fragment() {

    private lateinit var taskAdapter: TaskAdapter
    private var _binding: FragmentDoneBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDoneBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerViewTask(getTask())
    }

    private fun initRecyclerViewTask(taskList: List<Task>) {

        taskAdapter = TaskAdapter(requireContext(), taskList) { task, option -> optionSelected(task, option)}
        binding.recyclerViewTask.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewTask.setHasFixedSize(true)

        binding.recyclerViewTask.adapter = taskAdapter

    }

    private fun optionSelected(task:Task, option: Int){




    }

    private fun getTask() = listOf(

        Task("5", "Tarefa Teste 5",Status.DONE),
        Task("6", "Tarefa Teste 6",Status.DONE)
    )

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}