package `in`.rchandel.focusflow.views

import `in`.rchandel.focusflow.R
import `in`.rchandel.focusflow.adapter.TodoAdapter
import `in`.rchandel.focusflow.data.TodoItem
import `in`.rchandel.focusflow.databinding.TodoListViewBinding
import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager

class TodoListView : LinearLayout{

    var binding : TodoListViewBinding? = null
    lateinit var todoAdapter: TodoAdapter

    constructor(context: Context) : super(context) {
        View.inflate(context, R.layout.todo_list_view, this)
        todoAdapter = TodoAdapter(context)
        binding = TodoListViewBinding.bind(this)

    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        View.inflate(context, R.layout.todo_list_view, this)
        todoAdapter = TodoAdapter(context)
        binding = TodoListViewBinding.bind(this)
    }

    fun updateList(list: List<TodoItem>) {
        Log.d("INAPPLOG", "listView")
        binding?.rvTodoItems?.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding?.rvTodoItems?.adapter = todoAdapter
        todoAdapter.updateList(list)
    }
}