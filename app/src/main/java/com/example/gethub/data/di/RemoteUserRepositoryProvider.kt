package com.example.gethub.data.di

import com.example.gethub.domain.repository.ImplementUserRepository
import com.example.gethub.domain.repository.UserRepository

object RemoteUserRepositoryProvider {

    fun remoteUserRepositoryProvider(): UserRepository {
        val remoteSource =
            com.example.gethub.data.di.RemoteDataSourceProvider.remoteDataSourceProvider()
        return ImplementUserRepository.getInstance(remoteSource)
    }
}