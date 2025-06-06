package com.yakogdan.thousandsofcourses.presentation.screens.favorites

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.yakogdan.thousandsofcourses.R
import com.yakogdan.thousandsofcourses.databinding.FragmentFavoritesBinding
import com.yakogdan.thousandsofcourses.presentation.activities.MainActivity

class FavoritesFragment : Fragment(R.layout.fragment_favorites) {

    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentFavoritesBinding.bind(view)

        (activity as MainActivity).setBottomNavVisibility(isVisible = true)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}