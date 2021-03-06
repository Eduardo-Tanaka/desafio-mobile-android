package br.com.eduardotanaka.nexaas.data.repository

import br.com.eduardotanaka.nexaas.data.repository.base.Resource

interface RetrofitTesteRepository {

    suspend fun getAll(): Resource<List<RetrofitTeste>>
}