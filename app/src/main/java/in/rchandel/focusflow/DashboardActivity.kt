package `in`.rchandel.focusflow

import `in`.rchandel.focusflow.data.JournalItem
import `in`.rchandel.focusflow.databinding.ActivityDashboardBinding
import `in`.rchandel.focusflow.repository.TodoRepository
import `in`.rchandel.focusflow.viewmodel.DashboardViewModel
import `in`.rchandel.focusflow.viewmodel.DashboardViewModelProvider
import `in`.rchandel.focusflow.views.CustomCardView
import `in`.rchandel.focusflow.views.JournalView
import `in`.rchandel.focusflow.views.ParentCartView
import `in`.rchandel.focusflow.views.TodoListView
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.FrameLayout
import androidx.lifecycle.ViewModelProvider
import java.util.*

class DashboardActivity : AppCompatActivity() {

    lateinit var dashboardViewModelProvider : DashboardViewModelProvider
    lateinit var dashboardViewModel: DashboardViewModel
    private lateinit var binding: ActivityDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        dashboardViewModelProvider = DashboardViewModelProvider(TodoRepository())
        dashboardViewModel = ViewModelProvider(this, dashboardViewModelProvider)[DashboardViewModel::class.java]

        var customCardViewOne = CustomCardView(this)
        var listView = TodoListView(this)
        customCardViewOne.setView(listView, "Today's Todo")

        var customCardViewTwo = CustomCardView(this)
        customCardViewTwo.setView(FrameLayout(this), "Calender")

        var customCardViewThree = CustomCardView(this)
        var journalView = JournalView(this)
        customCardViewThree.setView(journalView, "Today's Journal")

        val parentCartView = ParentCartView(this)
        binding.parentView.addView(parentCartView)

        parentCartView.addViewToList(customCardViewThree, 2)
        parentCartView.addViewToList(customCardViewTwo, 1)
        customCardViewTwo.shiftTitleBottom()
        customCardViewThree.shiftTitleBottom()



        parentCartView.addViewToList(customCardViewOne, 0)


//        parentCartView.bringToTop(2)

        dashboardViewModel.todoLiveDate.observe(this) {
            Log.d("INAPPLOG", "view model observe called ${it.first.size}")
            listView.updateList(it.first)
            journalView.updateJournalItem(it.second)

        }
        dashboardViewModel.getTodoItemsByDate(Date())
    }

}