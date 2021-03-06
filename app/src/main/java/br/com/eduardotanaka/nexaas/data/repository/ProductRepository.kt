package br.com.eduardotanaka.nexaas.data.repository

import br.com.eduardotanaka.nexaas.data.model.entity.Product
import br.com.eduardotanaka.nexaas.data.repository.base.Resource

interface ProductRepository {

    suspend fun getAll(): Resource<List<Product>>
}