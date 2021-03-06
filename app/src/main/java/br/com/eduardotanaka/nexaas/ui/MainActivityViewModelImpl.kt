package br.com.eduardotanaka.nexaas.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.eduardotanaka.nexaas.R
import br.com.eduardotanaka.nexaas.data.model.entity.Product
import br.com.eduardotanaka.nexaas.data.repository.ProductRepository
import br.com.eduardotanaka.nexaas.ui.base.BaseViewModel
import br.com.eduardotanaka.nexaas.ui.base.StatefulResource
import javax.inject.Inject

class MainActivityViewModelImpl @Inject constructor(
    private val productRepository: ProductRepository,
) : BaseViewModel(), MainActivityViewModel {

    private val mutableProductList: MutableLiveData<StatefulResource<List<Product>>> =
        MutableLiveData()
    override val productList: LiveData<StatefulResource<List<Product>>> =
        mutableProductList

    override fun getAll() {
        launch {
            mutableProductList.value = StatefulResource.with(StatefulResource.State.LOADING)
            val resource = productRepository.getAll()
            when {
                resource.hasData() -> {
                    //return the value
                    mutableProductList.value = StatefulResource.success(resource)
                }
                resource.isNetworkIssue() -> {
                    mutableProductList.value = StatefulResource<List<Product>>()
                        .apply {
                            setMessage(R.string.no_network_connection)
                            setState(StatefulResource.State.ERROR_NETWORK)
                        }
                }
                resource.isApiIssue() -> //TODO 4xx isn't necessarily a service error, expand this to sniff http code before saying service error
                    if (resource.response?.code() == 404) {
                        mutableProductList.value = StatefulResource<List<Product>>()
                            .apply {
                                setState(StatefulResource.State.ERROR_API)
                                setMessage(R.string.not_found)
                            }
                    } else {
                        mutableProductList.value = StatefulResource<List<Product>>()
                            .apply {
                                setState(StatefulResource.State.ERROR_API)
                                setMessage(R.string.service_error)
                            }
                    }
                else -> mutableProductList.value = StatefulResource<List<Product>>()
                    .apply {
                        setState(StatefulResource.State.SUCCESS)
                        setMessage(R.string.not_found)
                    }
            }
        }
    }

}