package com.yakogdan.thousandsofcourses.presentation.screens.account

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.yakogdan.thousandsofcourses.R
import com.yakogdan.thousandsofcourses.databinding.FragmentAccountBinding
import com.yakogdan.thousandsofcourses.presentation.activities.MainActivity

class AccountFragment : Fragment(R.layout.fragment_account) {

    private var _binding: FragmentAccountBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentAccountBinding.bind(view)

        (activity as MainActivity).setBottomNavVisibility(isVisible = true)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}