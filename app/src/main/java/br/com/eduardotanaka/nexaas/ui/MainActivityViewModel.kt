package br.com.eduardotanaka.nexaas.ui

import androidx.lifecycle.LiveData
import br.com.eduardotanaka.nexaas.data.model.entity.Product
import br.com.eduardotanaka.nexaas.ui.base.StatefulResource

interface MainActivityViewModel {

    val productList: LiveData<StatefulResource<List<Product>>>
    fun getAll()
}