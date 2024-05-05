package `in`.rchandel.focusflow.repository

import `in`.rchandel.focusflow.data.JournalItem
import `in`.rchandel.focusflow.data.TodoItem
import android.text.format.DateUtils
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import java.sql.Time
import java.util.Date

class TodoRepository {
    private val mockTodoPair: Pair<MutableList<TodoItem>, JournalItem?> = getMockPair()

    private val _todoItemPairLiveData = MutableLiveData<Pair<MutableList<TodoItem>, JournalItem?>>()
    val todoItems: LiveData<Pair<MutableList<TodoItem>, JournalItem?>>
        get() = _todoItemPairLiveData

    fun getTodoItems(date: Date) {
        Log.d("INAPPLOG", "repo called")
        var resultPair = Pair<MutableList<TodoItem>, JournalItem?>(mutableListOf<TodoItem>(), getMockJournalItem())
        for (item in mockTodoPair.first) {
            if (isFromDay(item, date)) {
                resultPair.first.add(item)
            }
        }
        _todoItemPairLiveData.postValue(resultPair)
    }

    fun isFromDay(item: TodoItem, date: Date): Boolean {
        return true;
    }

    private fun getMockPair(): Pair<MutableList<TodoItem>, JournalItem?> {
        var list = mutableListOf(
            TodoItem(
                "1",
                "Go to that party",
                Date(),
                false
            ),
            TodoItem("2", "Finish project assignment", Date(), false),
            TodoItem("3", "Send email to John", Date(), false),
            TodoItem("4", "Pick Mom at the airport", Date(), false)
        )
        return Pair(list, getMockJournalItem())
    }

    private fun getMockJournalItem() : JournalItem {
        return JournalItem("1", "Today marked the end of a long and challenging journey as I finally submitted my thesis for review. The culmination of months of research, writing, and revisions, this moment feels both surreal and immensely satisfying. As I hit the \"send\" button, I couldn't help but reflect on the countless hours spent poring over literature, designing experiments, and grappling with data analysis. Despite the inevitable setbacks and moments of self-doubt, I emerged stronger and more resilient.", Date())
    }


}