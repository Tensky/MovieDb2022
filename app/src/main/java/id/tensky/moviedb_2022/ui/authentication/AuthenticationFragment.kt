package id.tensky.moviedb_2022.ui.authentication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import id.tensky.moviedb_2022.R
import id.tensky.moviedb_2022.databinding.FragmentAuthenticationBinding
import id.tensky.moviedb_2022.util.base.BaseFragment
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AuthenticationFragment : BaseFragment<FragmentAuthenticationBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentAuthenticationBinding
        get() = FragmentAuthenticationBinding::inflate
    private val viewModel: AuthenticationViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObserver()
    }

    override fun setupView(binding: FragmentAuthenticationBinding) {
        super.setupView(binding)
        binding.apply {
            buttonLoginGuest.setOnClickListener {
                viewModel.authenticateGuest()
                showLoadingDialog()
            }
        }
    }

    private fun setupObserver() {
        lifecycleScope.launch {
            viewModel.authenticationStateFlow.collect {
                when (it) {
                    is AuthenticationPageState.AuthenticationSuccess -> {
                        closeLoadingDialog()
                        openHomePage()
                    }
                    is AuthenticationPageState.Error -> {
                        closeLoadingDialog()
                    }
                    is AuthenticationPageState.Empty -> {
                        closeLoadingDialog()
                    }
                }
            }
        }
    }

    private fun openHomePage() {
        findNavController().navigate(R.id.homeFragment)
    }
}