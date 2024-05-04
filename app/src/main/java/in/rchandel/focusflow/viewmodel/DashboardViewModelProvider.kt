package `in`.rchandel.focusflow.viewmodel

import `in`.rchandel.focusflow.repository.TodoRepository
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class DashboardViewModelProvider(private val repository: TodoRepository) : ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DashboardViewModel(repository) as T
    }
}