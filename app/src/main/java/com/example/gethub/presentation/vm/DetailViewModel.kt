package com.example.gethub.presentation.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.example.gethub.domain.repository.UserRepository

class DetailViewModel(private val repository: UserRepository): ViewModel(){
    private val temp = MutableLiveData<String>()

    fun setUsername(username: String) {
        temp.value = username
    }

    val getdetailUser by lazy {
        temp.switchMap { username ->
            repository.getDetailUser(username)}
    }
}