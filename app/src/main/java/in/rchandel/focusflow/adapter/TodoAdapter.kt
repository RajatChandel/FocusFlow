package `in`.rchandel.focusflow.adapter

import `in`.rchandel.focusflow.data.TodoItem
import `in`.rchandel.focusflow.databinding.TodoItemViewBinding
import android.content.Context
import android.graphics.Paint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class TodoAdapter(private val context: Context) : Adapter<TodoAdapter.TodoViewHolder>() {

    inner class TodoViewHolder(binding: TodoItemViewBinding) : ViewHolder(binding.root) {
        var textField =  binding.todoText
    }

    var todoItemList = ArrayList<TodoItem>()

    fun updateList(newList : List<TodoItem>) {
        todoItemList.clear()
        todoItemList.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(TodoItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.textField.isEnabled = false
        holder.textField.text = todoItemList[position].title
        if(todoItemList[position].isDone) {
            holder.textField.paintFlags =
                holder.textField.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        }
    }

    override fun getItemCount(): Int {
        return todoItemList.size
    }
}