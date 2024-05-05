package `in`.rchandel.focusflow.views

import `in`.rchandel.focusflow.R
import `in`.rchandel.focusflow.data.JournalItem
import `in`.rchandel.focusflow.databinding.JournalViewBinding
import `in`.rchandel.focusflow.databinding.ParentCardViewBinding
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Date

class JournalView : LinearLayout {

    var binding: JournalViewBinding? = null
    lateinit var journalItem: JournalItem

    constructor(context: Context) : super(context) {
        View.inflate(context, R.layout.journal_view, this)
        binding = JournalViewBinding.bind(this)
    }


    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        View.inflate(context, R.layout.journal_view, this)
        binding = JournalViewBinding.bind(this)
    }

    fun updateJournalItem(journalItem: JournalItem?) {
        if(journalItem != null) {
            binding?.journalDescription?.text = journalItem.text
            binding?.journalDate?.text = formatDate(journalItem.date)
        }
    }

    private fun formatDate(date: Date) : String {
        return SimpleDateFormat("MMMM dd, yyyy").format(date)
    }
}