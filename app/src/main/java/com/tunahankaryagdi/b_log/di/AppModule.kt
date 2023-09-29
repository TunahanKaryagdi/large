package com.tunahankaryagdi.b_log.di

import android.content.Context
import com.tunahankaryagdi.b_log.data.repository.AuthRepositoryImpl
import com.tunahankaryagdi.b_log.data.repository.BlogRepositoryImpl
import com.tunahankaryagdi.b_log.data.repository.CommentRepositoryImpl
import com.tunahankaryagdi.b_log.data.repository.ImageRepositoryImpl
import com.tunahankaryagdi.b_log.data.repository.LikeRepositoryImpl
import com.tunahankaryagdi.b_log.data.repository.UserRepositoryImpl
import com.tunahankaryagdi.b_log.data.source.local.AuthDataStore
import com.tunahankaryagdi.b_log.data.source.remote.AuthService
import com.tunahankaryagdi.b_log.data.source.remote.BlogService
import com.tunahankaryagdi.b_log.data.source.remote.CommentService
import com.tunahankaryagdi.b_log.data.source.remote.ImageService
import com.tunahankaryagdi.b_log.data.source.remote.LikeService
import com.tunahankaryagdi.b_log.data.source.remote.UserService
import com.tunahankaryagdi.b_log.domain.repository.AuthRepository
import com.tunahankaryagdi.b_log.domain.repository.BlogRepository
import com.tunahankaryagdi.b_log.domain.repository.CommentRepository
import com.tunahankaryagdi.b_log.domain.repository.ImageRepository
import com.tunahankaryagdi.b_log.domain.repository.LikeRepository
import com.tunahankaryagdi.b_log.domain.repository.UserRepository
import com.tunahankaryagdi.b_log.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
    fun provideCommentService(retrofit: Retrofit): CommentService{
        return retrofit.create(CommentService::class.java)
    }


    @Provides
    @Singleton
    fun provideCommentRepository(commentService: CommentService) : CommentRepository{
        return CommentRepositoryImpl(commentService)
    }

    @Provides
    @Singleton
    fun provideImageService(retrofit: Retrofit): ImageService{
        return retrofit.create(ImageService::class.java)
    }

    @Provides
    @Singleton
    fun provideImageRepository(imageService: ImageService) : ImageRepository{
        return ImageRepositoryImpl(imageService)
    }

    @Provides
    @Singleton
    fun provideLikeService(retrofit: Retrofit): LikeService{
        return retrofit.create(LikeService::class.java)
    }

    @Provides
    @Singleton
    fun provideLikeRepository(likeService: LikeService) : LikeRepository{
        return LikeRepositoryImpl(likeService)
    }

    @Provides
    @Singleton
    fun provideDataStore(@ApplicationContext context: Context) : AuthDataStore{
        return AuthDataStore(context)
    }

    @Provides
    @Singleton
    fun provideApplication() : MyApplication{
        return MyApplication()
    }

}