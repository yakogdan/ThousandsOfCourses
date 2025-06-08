package com.yakogdan.thousandsofcourses.presentation.screens.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.net.toUri
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.github.terrakok.cicerone.Router
import com.yakogdan.thousandsofcourses.R
import com.yakogdan.thousandsofcourses.app.CoursesApp
import com.yakogdan.thousandsofcourses.databinding.FragmentLoginBinding
import com.yakogdan.thousandsofcourses.di.components.DaggerLoginComponent
import com.yakogdan.thousandsofcourses.presentation.activities.MainActivity
import com.yakogdan.thousandsofcourses.presentation.navigation.Screens.Main
import com.yakogdan.thousandsofcourses.presentation.tools.LatinEmailInputFilter
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginFragment : Fragment(R.layout.fragment_login) {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[LoginViewModel::class.java]
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        val applicationComponent =
            (requireContext().applicationContext as CoursesApp).applicationComponent
        val loginComponent =
            DaggerLoginComponent.builder().applicationComponent(applicationComponent).build()
        loginComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentLoginBinding.bind(view)

        (activity as MainActivity).setBottomNavVisibility(isVisible = false)

        binding.apply {

            btnLogin.setOnClickListener {
                router.newRootScreen(Main())
            }

            btnVK.setOnClickListener {
                openWebsite(url = getString(R.string.vk_url))
            }

            btnOK.setOnClickListener {
                openWebsite(url = getString(R.string.ok_url))
            }
        }

        setupEmailInputFilter()

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    observeLoginButtonEnabled()
                }
            }
        }
    }

    private suspend fun observeLoginButtonEnabled(): Nothing {
        binding.apply {
            etEmail.doAfterTextChanged { viewModel.onEmailChanged(it.toString()) }
            etPassword.doAfterTextChanged { viewModel.onPasswordChanged(it.toString()) }

            viewModel.isEmailPasswordValid.collect { isValid ->
                btnLogin.isEnabled = isValid
            }
        }
    }

    private fun setupEmailInputFilter() {
        binding.etEmail.filters = arrayOf(LatinEmailInputFilter())
    }

    private fun openWebsite(url: String) {
        val browserIntent = Intent(
            Intent.ACTION_VIEW,
            url.toUri()
        )
        startActivity(browserIntent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}