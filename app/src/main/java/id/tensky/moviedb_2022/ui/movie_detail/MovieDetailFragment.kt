package id.tensky.moviedb_2022.ui.movie_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import id.tensky.core.domain.model.MovieDetail
import id.tensky.core.util.constants.BaseUrlConstant
import id.tensky.core.util.resource.Resource
import id.tensky.moviedb_2022.databinding.FragmentMovieDetailBinding
import id.tensky.moviedb_2022.util.base.BaseFragment
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MovieDetailFragment : BaseFragment<FragmentMovieDetailBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentMovieDetailBinding
        get() = FragmentMovieDetailBinding::inflate

    private val navArgs: MovieDetailFragmentArgs by navArgs()
    private val viewModel by viewModels<MovieDetailViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getArgumentsFromNav()
        observeMovieDetailFlow()
    }

    override fun setupView(binding: FragmentMovieDetailBinding) {
        super.setupView(binding)
        binding.apply {
            ibBack.setOnClickListener {
                requireActivity().onBackPressed()
            }
        }
    }

    /**```
     * Get Arguments from NavArgs (Navigation Architecture) and set it into ViewModel
     *
     * Parameters in navargs : movieID(Int)
     * ```
     */
    private fun getArgumentsFromNav() {
        viewModel.movieId = navArgs.movieId
    }

    /**```
     * Ask for fetch, then observe flow that got returned. This function needs Coroutine Scope for collecting.
     * ```
     * */
    private fun observeMovieDetailFlow() {
        if (viewModel.movieId == null) {
            showErrorMessage("No Movie Id Found")
            findNavController().popBackStack()
            return
        }
        lifecycleScope.launch {
            viewModel.fetchMovieDetail(viewModel.movieId!!).collectLatest {
                when (it) {
                    is Resource.Loading -> {
                        showLoadingDialog()
                    }
                    is Resource.Error -> {
                        closeLoadingDialog()
                        showErrorMessage(it.message)
                        findNavController().popBackStack()
                    }
                    is Resource.Success -> {
                        setupMovieData(it.data)
                        closeLoadingDialog()
                    }
                }
            }
        }
    }

    /**```
     *  Show Error Message from fetch movie result.
     *
     *  @param errorMessage -> Error Message that shall be shown to user.
     *  ```
     * */
    private fun showErrorMessage(errorMessage: String) {
        Toast.makeText(requireContext().applicationContext, errorMessage, Toast.LENGTH_LONG).show()
    }

    private fun setupMovieData(movieDetailItem: MovieDetail) {
        binding.apply {
            Glide.with(requireContext())
                .load("${BaseUrlConstant.IMAGE_BASE_URL}${movieDetailItem.posterPath}")
                .into(ivPoster)

            tvTitle.text = movieDetailItem.title
            tvDescription.text = movieDetailItem.overview
        }
    }
}