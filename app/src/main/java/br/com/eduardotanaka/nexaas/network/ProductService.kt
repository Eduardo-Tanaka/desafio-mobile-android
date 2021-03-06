package br.com.eduardotanaka.nexaas.network

import br.com.eduardotanaka.nexaas.data.model.api.ProductModel
import retrofit2.Response
import retrofit2.http.GET

// https://raw.githubusercontent.com/myfreecomm/desafio-mobile-android/master/api/data.json
interface ProductService {

    @GET("/myfreecomm/desafio-mobile-android/master/api/data.json")
    suspend fun getAll(): Response<List<ProductModel>>
}