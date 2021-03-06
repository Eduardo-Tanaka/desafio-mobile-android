package br.com.eduardotanaka.nexaas.network

import retrofit2.Response
import retrofit2.http.GET

// https://5e25087e43dea60014404938.mockapi.io/api/retrofit-teste
interface RetrofitTesteService {

    @GET("/api/retrofit-teste")
    suspend fun getAll(): Response<List<RetrofitTesteModel>>
}