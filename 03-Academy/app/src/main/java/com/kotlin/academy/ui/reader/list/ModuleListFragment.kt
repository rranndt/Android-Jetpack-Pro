package com.kotlin.academy.ui.reader.list

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.kotlin.academy.data.ModuleEntity
import com.kotlin.academy.databinding.FragmentModuleListBinding
import com.kotlin.academy.ui.reader.CourseReaderActivity
import com.kotlin.academy.ui.reader.CourseReaderCallback
import com.kotlin.academy.utils.DataDummy

class ModuleListFragment : Fragment(), ModuleListAdapter.MyAdapterClicklistener {

    private var _binding: FragmentModuleListBinding? = null
    private val binding get() = _binding!!

    companion object {
        val TAG: String = ModuleListFragment::class.java.simpleName
        fun newInstance(): ModuleListFragment = ModuleListFragment()
    }

    private lateinit var adapter: ModuleListAdapter
    private lateinit var courseReaderCallback: CourseReaderCallback

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentModuleListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        adapter = ModuleListAdapter(this)
        populateRecyclerView(DataDummy.generateDummyModules("a14"))
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        courseReaderCallback = context as CourseReaderActivity
    }

    private fun populateRecyclerView(modules: List<ModuleEntity>) {
        with(binding) {
            progressBar.visibility = View.GONE
            adapter.setModules(modules)
            rvModule.layoutManager = LinearLayoutManager(context)
            rvModule.setHasFixedSize(true)
            rvModule.adapter = adapter
            val divideritemDecoration =
                DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
            rvModule.addItemDecoration(divideritemDecoration)
        }
    }

    override fun onItemClicked(position: Int, moduleId: String) {
        courseReaderCallback.moveTo(position, moduleId)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}