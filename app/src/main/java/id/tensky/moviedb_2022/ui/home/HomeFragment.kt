package id.tensky.moviedb_2022.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import id.tensky.core.domain.model.Movie
import id.tensky.core.util.constants.BaseUrlConstant
import id.tensky.moviedb_2022.databinding.FragmentHomeBinding
import id.tensky.moviedb_2022.util.adapter.recycler_paging.MovieCardPagingRVAdapter
import id.tensky.moviedb_2022.util.base.BaseFragment
import id.tensky.moviedb_2022.util.image_transformator.BlurTransformation
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentHomeBinding
        get() = FragmentHomeBinding::inflate
    private val viewModel by viewModels<HomeViewModel>()
    private val trendingMovieAdapter = MovieCardPagingRVAdapter(::onMovieItemClicked)
    private val discoverMovieAdapter = MovieCardPagingRVAdapter(::onMovieItemClicked)
    private var currentImagePosition = -1

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fetchPagingTrending()
        fetchPagingDiscover()
    }

    override fun onResume() {
        super.onResume()

        viewModel.posterPath?.let { changeTrendingBackground(it) }
    }

    override fun setupView(binding: FragmentHomeBinding) {
        super.setupView(binding)
        binding.apply {
            rvTrending.adapter =
                trendingMovieAdapter
            rvTrending.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

            rvDiscover.adapter =
                discoverMovieAdapter
            rvDiscover.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

            listenToTrendingMovieScrollChanges()
        }
    }

    private fun fetchPagingTrending() {
        lifecycleScope.launch {
            viewModel.fetchTrendingMovieList().collectLatest {
                trendingMovieAdapter.submitData(it)
            }
        }
    }

    private fun fetchPagingDiscover() {
        lifecycleScope.launch {
            viewModel.fetchDiscoverMovieList().collectLatest {
                discoverMovieAdapter.submitData(it)
                if(viewModel.posterPath == null && trendingMovieAdapter.itemCount != 0){
                    onRvItemShownUpdated(0)
                }
            }
        }
    }

    private fun listenToTrendingMovieScrollChanges() {
        binding.apply {
            rvTrending.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    val currentRvPosition =
                        (rvTrending.layoutManager as LinearLayoutManager).findFirstCompletelyVisibleItemPosition()

                    // If position is null (-1) or is same with current shown, no need to update.
                    if (currentRvPosition == -1 || currentImagePosition == currentRvPosition) return
                    currentImagePosition = currentRvPosition

                    onRvItemShownUpdated(currentRvPosition)
                }
            })
        }
    }

    /**
     * @param currentRvPosition is current RV item that was shown fully on screen.
     */
    private fun onRvItemShownUpdated(currentRvPosition: Int) {
        //Get item from adapter, then get path from item.
        val posterPath =
            trendingMovieAdapter.getItemByPosition(currentRvPosition)?.posterPath
                ?: return

        viewModel.posterPath = posterPath
        changeTrendingBackground(posterPath)
    }


    /**
     * @param backgroundPosterPath is the path of poster image, not full URL. The path is intended to be concat with Movie DB Base Url.
     */
    private fun changeTrendingBackground(backgroundPosterPath: String) {
        binding.apply {
            Glide.with(requireContext())
                .load("${BaseUrlConstant.IMAGE_BASE_URL}/$backgroundPosterPath")
                .transform(BlurTransformation(requireContext()))
                .into(ivTrendingBackground)
        }
    }

    /**
     * Called when any movie item in RecyclerView is clicked
     * Passed as parameter to adapter that accepts it.
     */
    private fun onMovieItemClicked(item: Movie) {
        val direction = HomeFragmentDirections.actionHomeFragmentToMovieDetailFragment(item.id)
        findNavController().navigate(direction)
    }
}