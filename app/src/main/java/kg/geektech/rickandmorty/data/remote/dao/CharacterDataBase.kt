package kg.geektech.rickandmorty.data.remote.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import kg.geektech.rickandmorty.domain.model.ResultDomain


@Database(entities = [ResultDomain::class], version = 1)
@TypeConverters(Converters::class)
abstract class CharacterDataBase:RoomDatabase() {
    abstract fun characterDao():CharacterDao

    companion object {
        @Volatile
        private var INSTANCE: CharacterDataBase? = null

        fun getDatabase(context: Context): CharacterDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CharacterDataBase::class.java,
                    "characters_db"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }

    }
}