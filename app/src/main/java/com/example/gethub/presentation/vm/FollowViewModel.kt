package com.example.gethub.presentation.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.example.gethub.domain.repository.UserRepository

class FollowViewModel(private val repository: UserRepository): ViewModel(){
    private val temp = MutableLiveData<String>()

    fun setUsername(username: String){
        temp.value = username
    }

    val getFollowers by lazy {
        temp.switchMap { username ->
            repository.getFollowers(username)
        }
    }

    val getFollowing by lazy {
        temp.switchMap { username ->
            repository.getFollowing(username)
        }
    }
}

