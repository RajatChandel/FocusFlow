package `in`.rchandel.focusflow.views

import `in`.rchandel.focusflow.R
import `in`.rchandel.focusflow.data.TodoItem
import `in`.rchandel.focusflow.databinding.ViewCustomCardBinding
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.exp

class CustomCardView : LinearLayout {

    var binding: ViewCustomCardBinding? = null
    lateinit var iCustomView: ICustomView
    var position : Int? = null

    constructor(context: Context) : super(context) {
        View.inflate(context, R.layout.view_custom_card, this)
        binding = ViewCustomCardBinding.bind(this)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        View.inflate(context, R.layout.view_custom_card, this)
        binding = ViewCustomCardBinding.bind(this)
    }

    fun setMeta(iCustomView: ICustomView, pos : Int) {
        this.iCustomView = iCustomView
        this.position = pos
    }


    fun setView(view: View, title: String) {
        binding?.customViewContainer?.removeAllViews()
        binding?.customViewContainer?.addView(view)
        binding?.title?.text = title.uppercase()
        binding?.cvParent?.setOnClickListener{
            shiftTitleUp()
            this.iCustomView.changeTopElement(position ?: 0)
        }
    }

    //shifts title to the top when view is selected
    fun shiftTitleUp() {
        binding?.cvParent?.removeAllViews()
        binding?.cvParent?.addView(binding?.title)
        binding?.cvParent?.addView(binding?.customViewContainer)
    }

    //shifts title to the bottom when view is not selected
    fun shiftTitleBottom() {
        binding?.cvParent?.removeAllViews()
        binding?.cvParent?.addView(binding?.customViewContainer)
        binding?.cvParent?.addView(binding?.title)
    }

}