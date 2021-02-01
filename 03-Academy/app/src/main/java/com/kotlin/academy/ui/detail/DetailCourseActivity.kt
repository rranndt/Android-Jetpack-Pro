package com.kotlin.academy.ui.detail

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.kotlin.academy.R
import com.kotlin.academy.data.CourseEntity
import com.kotlin.academy.databinding.ActivityDetailCourseBinding
import com.kotlin.academy.databinding.ContentDetailCourseBinding
import com.kotlin.academy.ui.reader.CourseReaderActivity
import com.kotlin.academy.utils.DataDummy

class DetailCourseActivity : AppCompatActivity() {

    private lateinit var binding: ContentDetailCourseBinding

    companion object {
        const val EXTRA_COURSE = "extra_course"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityDetailCourseBinding = ActivityDetailCourseBinding.inflate(layoutInflater)
        binding = activityDetailCourseBinding.detailContent
        setContentView(activityDetailCourseBinding.root)
        setSupportActionBar(activityDetailCourseBinding.toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val adapter = DetailCourseAdapter()

        val extras = intent.extras
        if (extras != null) {
            val courseId = extras.getString(EXTRA_COURSE)
            if (courseId != null) {
                val modules = DataDummy.generateDummyModules(courseId)
                adapter.setModules(modules)
                for (course in DataDummy.generateDummyCourse()) {
                    if (course.courseId == courseId) {
                        populateCourse(course)
                    }
                }
            }
        }

        with(binding.rvModule) {
            isNestedScrollingEnabled = false
            layoutManager = LinearLayoutManager(this@DetailCourseActivity)
            setHasFixedSize(true)
            this.adapter = adapter
            val divideritemDecoration =
                DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL)
            addItemDecoration(divideritemDecoration)
        }
    }

    private fun populateCourse(courseEntity: CourseEntity) {
        binding.textTitle.text = courseEntity.title
        binding.textDescription.text = courseEntity.description
        binding.textDate.text = resources.getString(R.string.deadline_date, courseEntity.deadline)

        Glide.with(this)
            .load(courseEntity.imagePath)
            .transform(RoundedCorners(20))
            .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
            .error(R.drawable.ic_error)
            .into(binding.imagePoster)

        binding.btnStart.setOnClickListener {
            val intent = Intent(this@DetailCourseActivity, CourseReaderActivity::class.java)
            intent.putExtra(CourseReaderActivity.EXTRA_COURSE_ID, courseEntity.courseId)
            startActivity(intent)
        }
    }
}