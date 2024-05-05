package `in`.rchandel.focusflow.viewmodel

import `in`.rchandel.focusflow.data.JournalItem
import `in`.rchandel.focusflow.data.TodoItem
import `in`.rchandel.focusflow.repository.TodoRepository
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.viewModelFactory
import java.util.Date

class DashboardViewModel(private val todoRepository: TodoRepository) : ViewModel() {

    val todoLiveDate : LiveData<Pair<MutableList<TodoItem>, JournalItem?>>
    get() = todoRepository.todoItems

    fun getTodoItemsByDate(date: Date) {
        todoRepository.getTodoItems(date)
    }
}