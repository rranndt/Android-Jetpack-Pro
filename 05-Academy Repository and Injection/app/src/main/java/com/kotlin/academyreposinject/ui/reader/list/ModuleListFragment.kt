package com.kotlin.academyreposinject.ui.reader.list

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.kotlin.academyreposinject.data.source.local.entity.ModuleEntity
import com.kotlin.academyreposinject.databinding.FragmentModuleListBinding
import com.kotlin.academyreposinject.ui.reader.CourseReaderActivity
import com.kotlin.academyreposinject.ui.reader.CourseReaderCallback
import com.kotlin.academyreposinject.ui.reader.CourseReaderViewModel
import com.kotlin.academyreposinject.viewmodel.ViewModelFactory

class ModuleListFragment : Fragment(), ModuleListAdapter.MyAdapterClicklistener {

    private var _binding: FragmentModuleListBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: CourseReaderViewModel

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

        val factory = ViewModelFactory.getInstance(requireActivity())
        viewModel = ViewModelProvider(
            requireActivity(),
            factory
        )[CourseReaderViewModel::class.java]
        adapter = ModuleListAdapter(this)
        populateRecyclerView(viewModel.getModules())
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
            val dividerItemDecoration =
                DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
            rvModule.addItemDecoration(dividerItemDecoration)
        }
    }

    override fun onItemClicked(position: Int, moduleId: String) {
        courseReaderCallback.moveTo(position, moduleId)
        viewModel.setSelectedModule(moduleId)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}