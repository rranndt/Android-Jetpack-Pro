package com.kotlin.academyreposinject.ui.reader.content

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.kotlin.academyreposinject.data.source.local.entity.ModuleEntity
import com.kotlin.academyreposinject.databinding.FragmentModuleContentBinding
import com.kotlin.academyreposinject.ui.reader.CourseReaderViewModel
import com.kotlin.academyreposinject.viewmodel.ViewModelFactory

class ModuleContentFragment : Fragment() {

    private var _binding: FragmentModuleContentBinding? = null
    private val binding get() = _binding!!

    companion object {
        val TAG: String = ModuleContentFragment::class.java.simpleName
        fun newInstance(): ModuleContentFragment = ModuleContentFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentModuleContentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel =
                ViewModelProvider(requireActivity(), factory)[CourseReaderViewModel::class.java]
            val module = viewModel.getSelectedModule()
            populateWebView(module)
        }
    }

    private fun populateWebView(module: ModuleEntity) {
        binding.webView.loadData(module.contentEntity?.content ?: "", "text/html", "UTF-8")
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}