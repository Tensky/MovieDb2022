package id.tensky.moviedb_2022.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import id.tensky.core.domain.usecase.MovieListUseCase
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val movieListUseCase: MovieListUseCase) :
    ViewModel() {

    var posterPath: String? = null

    fun fetchTrendingMovieList() =
        movieListUseCase.getTrendingMovieListPaging().cachedIn(viewModelScope)

    fun fetchDiscoverMovieList() =
        movieListUseCase.getDiscoverMovieListPaging().cachedIn(viewModelScope)
}