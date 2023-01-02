package id.tensky.moviedb_2022.util.adapter.recycler_paging

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.tensky.core.domain.model.Movie
import id.tensky.core.util.constants.BaseUrlConstant
import id.tensky.moviedb_2022.R
import id.tensky.moviedb_2022.util.image_placeholder.ShimmerImagePlaceholder

class MovieCardPagingRVAdapter(private val onItemClicked: (Movie) -> Unit) :
    PagingDataAdapter<Movie, MovieCardPagingRVAdapter.ViewHolder>(DiffUtilCallBack()) {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivMovie: ImageView = itemView.findViewById(R.id.iv_movie)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.recycler_movie_card, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.apply {
            Glide
                .with(ivMovie)
                .load("${BaseUrlConstant.IMAGE_BASE_URL}/${item?.posterPath}")
                .placeholder(ShimmerImagePlaceholder.shimmerDrawable)
                .centerCrop()
                .into(ivMovie)

            ivMovie.setOnClickListener {
                item?.let { it1 -> onItemClicked(it1) }
            }
        }
    }

    fun getItemByPosition(position: Int): Movie? {
        return getItem(position)
    }

    class DiffUtilCallBack : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }
    }
}