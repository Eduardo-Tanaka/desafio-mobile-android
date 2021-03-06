package br.com.eduardotanaka.nexaas.data.room.dao

import androidx.room.Dao
import androidx.room.Query
import br.com.eduardotanaka.nexaas.data.model.entity.Product
import br.com.eduardotanaka.nexaas.data.room.dao.base.BaseDao

@Dao
abstract class ProductDao : BaseDao<Product>() {

    @Query("SELECT * FROM Product")
    abstract fun getAll(): List<Product>
}