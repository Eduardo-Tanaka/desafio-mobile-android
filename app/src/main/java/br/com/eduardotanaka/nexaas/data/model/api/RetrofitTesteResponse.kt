package br.com.eduardotanaka.nexaas.data.model.api

import br.com.eduardotanaka.nexaas.data.model.api.base.ApiResponseObject

data class RetrofitTesteResponse(
    val retrofitTesteModelList: List<RetrofitTesteModel>
) : ApiResponseObject
