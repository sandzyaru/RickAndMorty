package kg.geektech.rickandmorty.data.remote


import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kg.geektech.rickandmorty.data.apiservice.CharacterApi
import kg.geektech.rickandmorty.data.ext.createAnApi
import kg.geektech.rickandmorty.data.repository.RepositoryImpl
import kg.geektech.rickandmorty.domain.repository.Repository
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [NetworkBuilder::class])
@InstallIn(SingletonComponent::class)
class MainModule  {
    @Singleton
    @Provides
    fun provideCharacterApiService(retrofit: Retrofit): CharacterApi {
        return retrofit.createAnApi()
    }
    @Singleton
    @Provides
    fun provideCharacterRepository(api: CharacterApi):Repository{
        return RepositoryImpl(api)
    }

}