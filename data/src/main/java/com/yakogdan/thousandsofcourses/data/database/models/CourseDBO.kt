package com.yakogdan.thousandsofcourses.data.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.yakogdan.thousandsofcourses.data.database.models.CourseDBO.Companion.TABLE_NAME
import java.util.Date

@Entity(tableName = TABLE_NAME)
data class CourseDBO(

    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "price")
    val price: String,

    @ColumnInfo(name = "publishDate")
    val publishDate: Date,

    @ColumnInfo(name = "rate")
    val rate: String,

    @ColumnInfo(name = "startDate")
    val startDate: Date,

    @ColumnInfo(name = "text")
    val text: String,

    @ColumnInfo(name = "title")
    val title: String,
) {
    companion object {
        const val TABLE_NAME = "course_table"
    }
}