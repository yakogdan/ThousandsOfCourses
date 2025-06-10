package com.yakogdan.thousandsofcourses.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.yakogdan.thousandsofcourses.data.database.models.CourseDBO
import com.yakogdan.thousandsofcourses.data.database.models.CourseDBO.Companion.TABLE_NAME

@Dao
interface CourseDao {

    @Query("SELECT * FROM $TABLE_NAME")
    fun getAllCourses(): List<CourseDBO>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCourse(course: CourseDBO)

}