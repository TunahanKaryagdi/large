package com.tunahankaryagdi.b_log.di

import android.content.Context
import com.tunahankaryagdi.b_log.data.repository.AuthRepositoryImpl
import com.tunahankaryagdi.b_log.data.repository.BlogRepositoryImpl
import com.tunahankaryagdi.b_log.data.repository.UserRepositoryImpl
import com.tunahankaryagdi.b_log.data.source.local.AuthDataStore
import com.tunahankaryagdi.b_log.data.source.remote.AuthService
import com.tunahankaryagdi.b_log.data.source.remote.BlogService
import com.tunahankaryagdi.b_log.data.source.remote.UserService
import com.tunahankaryagdi.b_log.domain.repository.AuthRepository
import com.tunahankaryagdi.b_log.domain.repository.BlogRepository
import com.tunahankaryagdi.b_log.domain.repository.UserRepository
import com.tunahankaryagdi.b_log.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
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
        return retrofit.create(AuthService::class.java)
    }

    @Provides
    @Singleton
    fun provideAuthRepository(authService: AuthService) : AuthRepository {
        return AuthRepositoryImpl(authService)
    }


    @Provides
    @Singleton
    fun provideBlogService(retrofit: Retrofit) : BlogService {
        return retrofit.create(BlogService::class.java)
    }

    @Provides
    @Singleton
    fun provideBlogRepository(blogService: BlogService) : BlogRepository {
        return BlogRepositoryImpl(blogService)
    }


    @Provides
    @Singleton
    fun provideUserService(retrofit: Retrofit) :UserService{
        return retrofit.create(UserService::class.java)
    }

    @Provides
    @Singleton
    fun provideUserRepository(userService: UserService) : UserRepository{
        return UserRepositoryImpl(userService)
    }

    @Provides
    @Singleton
    fun provideDataStore(@ApplicationContext context: Context) : AuthDataStore{
        return AuthDataStore(context)
    }

}