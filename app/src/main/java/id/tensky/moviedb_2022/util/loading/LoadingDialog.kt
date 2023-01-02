package id.tensky.moviedb_2022.util.loading

import android.app.Dialog
import android.content.Context
import android.view.Window
import id.tensky.moviedb_2022.R

class LoadingDialog(context: Context){
    private val dialog by lazy {
        Dialog(context).apply {
            setCancelable(false)
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setContentView(R.layout.dialog_loading)
            window?.setBackgroundDrawableResource(R.color.transparent)
            dismiss()
        }
    }

    fun showDialog(){
        dialog.show()
    }

    fun dismissDialog(){
        dialog.dismiss()
    }
}