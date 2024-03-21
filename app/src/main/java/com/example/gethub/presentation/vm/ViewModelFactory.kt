package com.example.gethub.presentation.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.gethub.data.di.RemoteUserRepositoryProvider
import com.example.gethub.domain.repository.UserRepository

class ViewModelFactory private constructor(private val repository: UserRepository): ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T: ViewModel> create(modelClass: Class<T>) =
        with(modelClass) {
            when {
                modelClass.isAssignableFrom(HomeViewModel::class.java) ->{
                    HomeViewModel(repository)as T
                }
                modelClass.isAssignableFrom(DetailViewModel::class.java) ->{
                    DetailViewModel(repository)as T
                }
                modelClass.isAssignableFrom(RepositoryViewModel::class.java) ->{
                    RepositoryViewModel(repository)as T
                }
                modelClass.isAssignableFrom(FollowViewModel::class.java) ->{
                    FollowViewModel(repository)as T
                }
                else ->
                    throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
            }
        } as T

    companion object {
        @Volatile
        private var INSTANCE: ViewModelFactory? = null

        fun getInstance() =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: ViewModelFactory(repository = RemoteUserRepositoryProvider.remoteUserRepositoryProvider())
            }. also {
                INSTANCE = it
            }
    }
}