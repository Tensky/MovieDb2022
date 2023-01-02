package id.tensky.moviedb_2022.util.image_placeholder

import com.facebook.shimmer.Shimmer
import com.facebook.shimmer.ShimmerDrawable


/*
 * Stolen from : https://stackoverflow.com/questions/61076174/how-to-use-a-view-shimmer-as-a-placeholder-for-an-imageview-glide
 */


object ShimmerImagePlaceholder {
    private val shimmer =
        Shimmer.AlphaHighlightBuilder()// The attributes for a ShimmerDrawable is set by this builder
            .setDuration(1800) // how long the shimmering animation takes to do one full sweep
            .setBaseAlpha(0.9f) //the alpha of the underlying children
            .setHighlightAlpha(0.7f) // the shimmer alpha amount
            .setDirection(Shimmer.Direction.LEFT_TO_RIGHT)
            .setAutoStart(true)
            .build()

    // This is the placeholder for the imageView
    val shimmerDrawable = ShimmerDrawable().apply {
        setShimmer(shimmer)
    }

}