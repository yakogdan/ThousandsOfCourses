package com.yakogdan.thousandsofcourses.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.yakogdan.thousandsofcourses.data.database.dao.CourseDao
import com.yakogdan.thousandsofcourses.data.database.models.CourseDBO

@Database(
    entities = [
        CourseDBO::class
    ],
    version = 1,
    exportSchema = false,
)
@TypeConverters(DateConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun courseDao(): CourseDao

    companion object {

        private const val DB_NAME = "courses.db"

        fun getInstance(context: Context): AppDatabase =
            Room.databaseBuilder(
                context = context,
                klass = AppDatabase::class.java,
                name = DB_NAME,
            ).build()
    }
}