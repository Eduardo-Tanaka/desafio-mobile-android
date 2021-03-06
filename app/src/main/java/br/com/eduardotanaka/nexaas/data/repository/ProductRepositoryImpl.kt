package br.com.eduardotanaka.nexaas.data.repository

import android.content.SharedPreferences
import br.com.eduardotanaka.nexaas.constants.CacheKey
import br.com.eduardotanaka.nexaas.data.model.api.ProductModel
import br.com.eduardotanaka.nexaas.data.model.api.ProductResponse
import br.com.eduardotanaka.nexaas.data.model.entity.Product
import br.com.eduardotanaka.nexaas.data.repository.base.BaseRepository
import br.com.eduardotanaka.nexaas.data.repository.base.Resource
import br.com.eduardotanaka.nexaas.data.repository.helpers.DataFetchHelper
import br.com.eduardotanaka.nexaas.data.room.AppDatabase
import br.com.eduardotanaka.nexaas.network.ProductService
import retrofit2.Response
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val productService: ProductService,
    private val appDatabase: AppDatabase,
    private val sharedPreferences: SharedPreferences
) : BaseRepository(), ProductRepository {
    override suspend fun getAll(): Resource<List<Product>> {
        val dataFetchHelper = object : DataFetchHelper.LocalFirstUntilStale<List<Product>>(
            ProductRepositoryImpl::class.simpleName.toString(),
            sharedPreferences,
            CacheKey.PRODUTOS.toString(),
            "",
            TimeUnit.HOURS.toSeconds(24 * 30)
        ) {
            override suspend fun getDataFromLocal(): List<Product>? {
                return appDatabase.productDao().getAll()
            }

            override suspend fun getDataFromNetwork(): Response<out Any?> {
                return productService.getAll()
            }

            override suspend fun convertApiResponseToData(response: Response<out Any?>): List<Product> {
                val response =
                    ProductResponse(response.body() as List<ProductModel>)
                return Product().reflectFrom(response)
            }

            override suspend fun storeFreshDataToLocal(data: List<Product>): Boolean {
                data.let {
                    appDatabase.productDao().insert(data)
                    return true
                }
            }
        }

        return dataFetchHelper.fetchDataIOAsync().await()
    }
}