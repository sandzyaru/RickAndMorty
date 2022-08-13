package kg.geektech.rickandmorty.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kg.geektech.rickandmorty.data.remote.dao.CharacterDao
import kg.geektech.rickandmorty.data.remote.dao.CharacterDataBase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class )
object RoomModule {
    @Singleton
    @Provides
    fun appDatabase(@ApplicationContext context: Context): CharacterDataBase {
        return CharacterDataBase.getDatabase(context)
    }

    @Singleton
    @Provides
    fun charactersDao(appDatabase: CharacterDataBase): CharacterDao {
        return appDatabase.characterDao()
    }
}