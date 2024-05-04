package `in`.rchandel.focusflow.views

import `in`.rchandel.focusflow.R
import `in`.rchandel.focusflow.data.TodoItem
import `in`.rchandel.focusflow.databinding.ViewCustomCardBinding
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.exp

class CustomCardView : LinearLayout, ICustomView {

     var binding : ViewCustomCardBinding? = null


    constructor(context: Context) : super(context) {
        View.inflate(context, R.layout.view_custom_card, this)
        binding = ViewCustomCardBinding.bind(this)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        View.inflate(context, R.layout.view_custom_card, this)
        binding = ViewCustomCardBinding.bind(this)
    }

    fun setView(view: View, title: String) {
        binding?.customViewContainer?.removeAllViews()
        binding?.customViewContainer?.addView(view)
        binding?.title?.text = title
    }

    override fun changeVisibility(expanded: Boolean) {
        if(expanded) {
            binding?.customViewContainer?.visibility = View.VISIBLE
        } else {
            binding?.customViewContainer?.visibility = View.VISIBLE
        }
    }

}