package com.example.gethub.presentation.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.example.gethub.domain.repository.UserRepository

class RepositoryViewModel(private val repository: UserRepository): ViewModel(){
    private val temp = MutableLiveData<String>()

    fun setUsername(username: String){
        temp.value = username
    }

    val getRepository by lazy {
        temp.switchMap { username ->
            repository.getRepository(username)
        }
    }
}