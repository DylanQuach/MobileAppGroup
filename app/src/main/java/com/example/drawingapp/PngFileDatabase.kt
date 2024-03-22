package com.example.drawingapp

import android.content.Context
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.flow.Flow

@Database(entities = [PngFile::class], version = 1, exportSchema = false)
abstract class PngFileDatabase : RoomDatabase() {
    abstract fun pngFileDao(): PngFileDao

    companion object {
        @Volatile
        private var INSTANCE: PngFileDatabase? = null

        fun getDatabase(context: Context): PngFileDatabase {
            return INSTANCE ?: synchronized(this) {
                if (INSTANCE != null) return INSTANCE!!
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PngFileDatabase::class.java,
                    "png_file_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}

@Dao
interface PngFileDao {
    // Returns a flow, so the task "collecting" it will be marked as suspend
    // Insert a new PNG file into the database
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPngFile(pngFile: PngFile)

    // Query all PNG files
    @Query("SELECT * FROM png_files")
    fun getAllPngFiles(): Flow<List<PngFile>>

    // get file names
}
