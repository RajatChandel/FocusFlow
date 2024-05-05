package `in`.rchandel.focusflow.views

import `in`.rchandel.focusflow.R
import `in`.rchandel.focusflow.databinding.ParentCardViewBinding
import `in`.rchandel.focusflow.databinding.ViewCustomCardBinding
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout

class ParentCartView : FrameLayout, ICustomView {

    private val viewList = mutableListOf<CustomCardView>()
    var binding: ParentCardViewBinding? = null
    var currentPos : Int = 0


    constructor(context: Context) : super(context) {
        View.inflate(context, R.layout.parent_card_view, this)
        binding = ParentCardViewBinding.bind(this)
    }


    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        View.inflate(context, R.layout.parent_card_view, this)
        binding = ParentCardViewBinding.bind(this)
    }

    fun addViewToList(view: CustomCardView, position: Int) {
        view.setMeta(this, viewList.size)
        currentPos = viewList.size
        viewList.add( view)
        binding?.flParent?.addView(view)

        //setting margin for views as per their position, with each positions the margin increases in the stack
        val params = view.layoutParams as ViewGroup.MarginLayoutParams
        params.setMargins(0, 192 * position, 0, 0)
    }

    // this method brings the selected view to the top of the stack
    private fun bringToTop(index1: Int) {
        this.removeAllViews()
        if(index1 < currentPos) {
            for (i in viewList.indices.reversed()) {
                if (i != index1) {
                    this.addView(viewList[i])
                }
            }
        } else {
            for (i in viewList.indices) {
                if (i != index1) {
                    this.addView(viewList[i])
                }
            }
        }
       currentPos = index1
        this.addView(viewList[index1])
    }

    //this method changes the layout based on which position(view) is visible
    override fun changeTopElement(position: Int) {
        if(currentPos == position) {
            return
        }
        var foundTop = false
        viewList.forEachIndexed { index, customCardView ->
            if (foundTop) {
                customCardView.shiftTitleUp()
            } else {
                customCardView.shiftTitleBottom()
            }
            if (index == position) {
                foundTop = true
                customCardView.shiftTitleUp()
            }
        }
        bringToTop(position)
    }

}