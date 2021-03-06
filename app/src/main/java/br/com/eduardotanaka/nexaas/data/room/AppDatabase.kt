package br.com.eduardotanaka.nexaas.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import br.com.eduardotanaka.nexaas.data.model.entity.Product
import br.com.eduardotanaka.nexaas.data.room.converter.Converters
import br.com.eduardotanaka.nexaas.data.room.dao.ProductDao

@Database(
    version = 1,
    entities = [
        Product::class
    ],
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun productDao(): ProductDao
}