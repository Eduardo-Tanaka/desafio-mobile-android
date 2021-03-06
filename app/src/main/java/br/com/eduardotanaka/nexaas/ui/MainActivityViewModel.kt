package br.com.eduardotanaka.nexaas.ui

import androidx.lifecycle.LiveData
import br.com.eduardotanaka.nexaas.data.model.entity.RetrofitTeste
import br.com.eduardotanaka.nexaas.ui.base.StatefulResource

interface MainActivityViewModel {

    val retrofitTesteList: LiveData<StatefulResource<List<RetrofitTeste>>>
    fun getAll()
}