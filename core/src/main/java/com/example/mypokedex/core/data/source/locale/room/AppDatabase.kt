package com.example.mypokedex.core.data.source.locale.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mypokedex.core.data.source.locale.entity.PokemonDetailEntity
import com.example.mypokedex.core.data.source.locale.entity.PokemonEntity

@Database(entities = [PokemonEntity::class, PokemonDetailEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun appDao(): AppDao
}