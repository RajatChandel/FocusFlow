package `in`.rchandel.focusflow

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
import android.widget.CalendarView
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

        val customCardViewOne = CustomCardView(this)
        val listView = TodoListView(this)
        customCardViewOne.setView(listView, "Today's Todo")

        val customCardViewTwo = CustomCardView(this)
        val calendarView = CalendarView(this)
        customCardViewTwo.setView(calendarView, "Calendar")

        val customCardViewThree = CustomCardView(this)
        val journalView = JournalView(this)
        customCardViewThree.setView(journalView, "Today's Journal")
        
        calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            val calendar = Calendar.getInstance()
            calendar.set(year, month, dayOfMonth)
            dashboardViewModel.getTodoItemsByDate(calendar.time)
        }

        val parentCartView = ParentCartView(this)
        binding.parentView.addView(parentCartView)

        parentCartView.addViewToList(customCardViewThree, 2)
        parentCartView.addViewToList(customCardViewTwo, 1)
        customCardViewTwo.shiftTitleBottom()
        customCardViewThree.shiftTitleBottom()


        parentCartView.addViewToList(customCardViewOne, 0)

        dashboardViewModel.todoLiveDate.observe(this) {
            Log.d("INAPPLOG", "view model observe called ${it.first.size}")
            listView.updateList(it.first)
            journalView.updateJournalItem(it.second)

        }
        dashboardViewModel.getTodoItemsByDate(Date())
    }

}