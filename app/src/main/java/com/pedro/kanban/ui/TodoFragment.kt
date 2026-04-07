package com.pedro.kanban.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.pedro.kanban.R
import com.pedro.kanban.data.model.Status
import com.pedro.kanban.data.model.Task
import com.pedro.kanban.databinding.FragmentDoingBinding
import com.pedro.kanban.databinding.FragmentTodoBinding
import com.pedro.kanban.ui.adapter.TaskAdapter

class TodoFragment : Fragment() {

    private lateinit var taskAdapter: TaskAdapter
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
        initRecyclerViewTask(getTask())

    }


    private fun initListeners() {
        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_formTaskFragment)
        }
    }

    private fun initRecyclerViewTask(taskList: List<Task>) {

        taskAdapter = TaskAdapter(requireContext(), taskList) { task, option -> optionSelected(task, option)}
        binding.recyclerViewTask.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewTask.setHasFixedSize(true)

        binding.recyclerViewTask.adapter = taskAdapter

    }

    private fun optionSelected(task:Task, option: Int){

        when(option){

            TaskAdapter.SELECT_REMOVER -> {
                Toast.makeText(requireContext(), "REMOVENDO", Toast.LENGTH_SHORT).show()
            }
            TaskAdapter.SELECT_EDIT -> {
                Toast.makeText(requireContext(), "EDITANDO", Toast.LENGTH_SHORT).show()
            }
            TaskAdapter.SELECT_DETAILS -> {
                Toast.makeText(requireContext(), "DETALHANDO", Toast.LENGTH_SHORT).show()
            }
            TaskAdapter.SELECT_NEXT -> {
                Toast.makeText(requireContext(), "PROXIMO", Toast.LENGTH_SHORT).show()
            }

        }
    }

    private fun getTask() = listOf(

        Task("1", "Tarefa Teste 1", Status.TODO),
        Task("2", "Tarefa Teste 2", Status.TODO)
    )

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
