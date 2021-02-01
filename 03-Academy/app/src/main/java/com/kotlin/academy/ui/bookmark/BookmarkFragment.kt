package com.kotlin.academy.ui.bookmark

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ShareCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.kotlin.academy.R
import com.kotlin.academy.data.CourseEntity
import com.kotlin.academy.databinding.FragmentBookmarkBinding
import com.kotlin.academy.utils.DataDummy

class BookmarkFragment : Fragment(), BookmarkFragmentCallback {

    private var _binding: FragmentBookmarkBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentBookmarkBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
//            val courses = DataDummy.generateDummyCourse()

            val viewModel = ViewModelProvider(
                this,
                ViewModelProvider.NewInstanceFactory()
            )[BookmarkViewModel::class.java]
            val courses = viewModel.getBookmarks()

            val adapter = BookmarkAdapter(this)
            adapter.setCourses(courses)

            with(binding.rvBookmark) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                this.adapter = adapter
            }
        }
    }

    override fun onShareClick(course: CourseEntity) {
        if (activity != null) {
            val mimeType = "text/plain"
            ShareCompat.IntentBuilder
                .from(requireActivity())
                .setType(mimeType)
                .setChooserTitle("Bagikan aplikasi ini sekarang")
                .setText(resources.getString(R.string.share_text, course.title))
                .startChooser()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}