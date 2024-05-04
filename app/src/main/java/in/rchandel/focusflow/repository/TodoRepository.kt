package `in`.rchandel.focusflow.repository

import `in`.rchandel.focusflow.data.TodoItem
import android.text.format.DateUtils
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import java.sql.Time
import java.util.Date

class TodoRepository {
    private val mockTodoList: List<TodoItem> = getMockList()

    private val _todoItemLiveData = MutableLiveData<List<TodoItem>>()
    val todoItems: LiveData<List<TodoItem>>
        get() = _todoItemLiveData


    fun getTodoItems(date: Date) {
        Log.d("INAPPLOG", "repo called")
        var resultList = mutableListOf<TodoItem>();
        for (item in mockTodoList) {
            if (isFromDay(item, Date())) {
                resultList.add(item)
            }
        }
        _todoItemLiveData.postValue(resultList)
    }

    fun isFromDay(item: TodoItem, date: Date): Boolean {
        return true;
    }

    fun getMockList(): List<TodoItem> {
        var list = mutableListOf(
            TodoItem(
                "1",
                "Go to that party",
                Date(),
                false
            ),
            TodoItem("2", "Go to that party", Date(), false),
            TodoItem("3", "Go to that party", Date(), false),
            TodoItem("4", "Go to that party", Date(), false),
            TodoItem("4", "Go to that party", Date(), false)
        )
        return list
    }


}