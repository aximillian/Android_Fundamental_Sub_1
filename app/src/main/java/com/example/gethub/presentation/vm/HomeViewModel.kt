package com.example.gethub.presentation.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.example.gethub.domain.repository.UserRepository
import com.example.gethub.util.LiveDataSource

class HomeViewModel(private val repository: UserRepository): ViewModel() {
    private val temp = MutableLiveData<String>()
    private val fetchSearchUsers = MutableLiveData<Unit>()
    private val randomUsers = MutableLiveData<Unit>()

    fun fetchSearchUsers(query: String) {
        fetchSearchUsers.value = Unit
        temp.value = query
    }

    fun fetchRandomUsers() {
        randomUsers.value = Unit
    }

    val getUsers by lazy {
        LiveDataSource(temp, fetchSearchUsers).switchMap {
            repository.getSearch(it.first.toString())
        }
    }
}