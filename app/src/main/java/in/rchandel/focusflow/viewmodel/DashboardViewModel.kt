package `in`.rchandel.focusflow.viewmodel

import `in`.rchandel.focusflow.data.TodoItem
import `in`.rchandel.focusflow.repository.TodoRepository
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.viewModelFactory
import java.util.Date

class DashboardViewModel(private val todoRepository: TodoRepository) : ViewModel() {

    val todoLiveDate : LiveData<List<TodoItem>>
    get() = todoRepository.todoItems

    fun getTodoItemsByDate(date: Date) {
        todoRepository.getTodoItems(date)
        Log.d("INAPPLOG", "view model called")
    }
}