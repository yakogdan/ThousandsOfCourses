package com.yakogdan.thousandsofcourses.presentation.activities

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.yakogdan.thousandsofcourses.R
import com.yakogdan.thousandsofcourses.app.CoursesApp
import com.yakogdan.thousandsofcourses.databinding.ActivityMainBinding
import com.yakogdan.thousandsofcourses.presentation.navigation.Screens.Account
import com.yakogdan.thousandsofcourses.presentation.navigation.Screens.Favorites
import com.yakogdan.thousandsofcourses.presentation.navigation.Screens.Login
import com.yakogdan.thousandsofcourses.presentation.navigation.Screens.Main
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    private val navigator = AppNavigator(this, R.id.fragmentContainer)

    override fun onCreate(savedInstanceState: Bundle?) {
        (applicationContext as CoursesApp).applicationComponent.inject(this)
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSystemPaddings()
        setColorSystemIcons(isLightIcons = true)
        setNavActiveIndicatorColor()

        if (savedInstanceState == null) router.newRootScreen(Login())
        initBottomNav()
    }

    private fun setNavActiveIndicatorColor() {
        binding.bottomNav.itemActiveIndicatorColor = getColorStateList(R.color.light_grey)
    }

    private fun setSystemPaddings() {
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0)
            insets
        }
    }

    @Suppress("SameParameterValue")
    private fun setColorSystemIcons(isLightIcons: Boolean) {
        val windowInsetsController = WindowCompat.getInsetsController(window, window.decorView)
        windowInsetsController.isAppearanceLightStatusBars = !isLightIcons
        windowInsetsController.isAppearanceLightNavigationBars = !isLightIcons
    }

    private fun initBottomNav() {
        binding.bottomNav.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {

                R.id.menu_main -> router.newRootScreen(Main())

                R.id.menu_favorites -> router.newRootScreen(Favorites())

                R.id.menu_account -> router.newRootScreen(Account())

                else -> error("Unknown navigation tab")
            }

            return@setOnItemSelectedListener true
        }
    }

    fun setBottomNavVisibility(isVisible: Boolean) {
        if (isVisible) {
            binding.bottomNav.visibility = View.VISIBLE
        } else {
            binding.bottomNav.visibility = View.GONE
        }
    }

    override fun onResume() {
        super.onResume()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}