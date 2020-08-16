package com.bman.kotlin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bman.kotlin.R
import com.bman.kotlin.db.TaskEntity

class AllTasksFragment : Fragment(), TasksAdapter.TaskInteraction {

    private var  isDoneFragment:Boolean = false
    lateinit var list: RecyclerView

    companion object {
        fun newInstance(isDone:Boolean) = AllTasksFragment()
            .apply {
            arguments = Bundle().apply {
                putBoolean("is_done", isDone)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.apply {
            isDoneFragment = getBoolean("is_done")
        }
    }

    private lateinit var viewModel: AllTasksViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.all_tasks_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        list = view.findViewById(R.id.rv_tasks)
        list.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val adapter = TasksAdapter(
            requireContext(),
            isDoneFragment,
            this
        );
        list.adapter = adapter
        viewModel = ViewModelProvider(this).get(AllTasksViewModel::class.java)
        viewModel.getTasks(isDoneFragment).observe(viewLifecycleOwner, Observer {
            adapter.updateTasks(it)
        })

    }

    override fun onTaskStateChanged(task: TaskEntity) {
        viewModel.updateTask(task = task)
    }
}