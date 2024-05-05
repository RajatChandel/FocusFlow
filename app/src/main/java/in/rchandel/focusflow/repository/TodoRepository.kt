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

    private val _todoItemPairLiveData = MutableLiveData<Pair<MutableList<TodoItem>, JournalItem?>>()
    val todoItems: LiveData<Pair<MutableList<TodoItem>, JournalItem?>>
        get() = _todoItemPairLiveData

    fun getTodoItems(date: Date) {
        if (isFromSameDay(date)) {
            _todoItemPairLiveData.postValue(getMockPairForToday())
        } else {
            _todoItemPairLiveData.postValue(getMockPairForDate(date))
        }
    }

    private fun isFromSameDay(date: Date): Boolean {
        return DateUtils.isToday(date.time);
    }

    private fun getMockPairForToday(): Pair<MutableList<TodoItem>, JournalItem?> {
        val list = mutableListOf(
            TodoItem(
                "1",
                "Go to that party",
                Date(),
                false
            ),
            TodoItem("2", "Finish project assignment", Date(), true),
            TodoItem("3", "Send email to John", Date(), true),
            TodoItem("4", "Pick Mom at the airport", Date(), false),
            TodoItem("5", "Continue singing lesson", Date(), false),
            TodoItem("6", "Go for a run", Date(), true),

        )
        return Pair(list, getMockJournalItem(Date()))
    }

    private fun getMockPairForDate(date: Date): Pair<MutableList<TodoItem>, JournalItem?> {
        val list = mutableListOf(
            TodoItem(
                "1",
                "Study for exams",
                Date(),
                false
            ),
            TodoItem("2", "Call insurance company", Date(), true),
            TodoItem("3", "Buy Health insurance", Date(), false),
            TodoItem("4", "Do some exercise", Date(), true)
        )
        return Pair(list, getMockJournalItem(date))
    }

    private fun getMockJournalItem(date: Date): JournalItem {
        return JournalItem(
            "1",
            "Today marked the end of a long and challenging journey as I finally submitted my thesis for review. The culmination of months of research, writing, and revisions, this moment feels both surreal and immensely satisfying. As I hit the \"send\" button, I couldn't help but reflect on the countless hours spent poring over literature, designing experiments, and grappling with data analysis. Despite the inevitable setbacks and moments of self-doubt, I emerged stronger and more resilient.",
            date
        )
    }


}