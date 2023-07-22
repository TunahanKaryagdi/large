package com.tunahankaryagdi.b_log.di

import com.tunahankaryagdi.b_log.data.repository.AuthRepositoryImpl
import com.tunahankaryagdi.b_log.data.source.remote.AuthService
import com.tunahankaryagdi.b_log.domain.repository.AuthRepository
import com.tunahankaryagdi.b_log.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton




@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit{

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideAuthService(retrofit: Retrofit) : AuthService{
        return retrofit.create()
    }

    @Provides
    @Singleton
    fun provideAuthRepository(authService: AuthService) : AuthRepository {
        return AuthRepositoryImpl(authService)
    }

}