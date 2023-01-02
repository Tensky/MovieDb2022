package id.tensky.moviedb_2022.ui.movie_detail

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import id.tensky.core.domain.usecase.MovieDetailUseCase
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(private val movieDetailUseCase: MovieDetailUseCase) :
    ViewModel() {
    var movieId: Int? = null

    fun fetchMovieDetail(movieId: Int) =
        movieDetailUseCase.getMovieDetail(movieId)

}
