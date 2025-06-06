package com.yakogdan.thousandsofcourses.presentation.navigation


import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.yakogdan.thousandsofcourses.presentation.screens.account.AccountFragment
import com.yakogdan.thousandsofcourses.presentation.screens.course.CourseFragment
import com.yakogdan.thousandsofcourses.presentation.screens.favorites.FavoritesFragment
import com.yakogdan.thousandsofcourses.presentation.screens.login.LoginFragment
import com.yakogdan.thousandsofcourses.presentation.screens.main.MainFragment

object Screens {

    fun Login(): FragmentScreen = FragmentScreen { LoginFragment() }

    fun Main(): FragmentScreen = FragmentScreen { MainFragment() }

    fun Favorites(): FragmentScreen = FragmentScreen { FavoritesFragment() }

    fun Account(): FragmentScreen = FragmentScreen { AccountFragment() }

    fun Course(): FragmentScreen = FragmentScreen { CourseFragment() }
}