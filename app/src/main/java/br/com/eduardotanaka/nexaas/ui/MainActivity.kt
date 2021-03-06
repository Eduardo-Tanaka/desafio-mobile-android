package br.com.eduardotanaka.nexaas.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.app.ActivityOptionsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.eduardotanaka.nexaas.constants.ExtraKey
import br.com.eduardotanaka.nexaas.data.model.entity.Product
import br.com.eduardotanaka.nexaas.databinding.ActivityMainBinding
import br.com.eduardotanaka.nexaas.ui.base.BaseActivity
import br.com.eduardotanaka.nexaas.ui.base.StatefulResource
import br.com.eduardotanaka.nexaas.ui.detalhe.DetailActivity
import timber.log.Timber

class MainActivity : BaseActivity() {

    private val viewModel by viewModels<MainActivityViewModelImpl> { factory }
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        /*window.requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS)

        // Set up shared element transition and disable overlay so views don't show above system bars
        setExitSharedElementCallback(MaterialContainerTransformSharedElementCallback())
        window.sharedElementsUseOverlay = false*/

        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        viewModel.getAll()
        viewModel.productList.observe(this, {
            if (it.state == StatefulResource.State.SUCCESS && it.hasData()) {
                Timber.d(it.resource?.data?.toString())

                val list = it.resource?.data!!
                val adapter = ProductAdapter(list, this)

                binding.qtdItens.text = "${list.size} itens in your cart"
                binding.rvProducts.layoutManager = LinearLayoutManager(this)
                binding.rvProducts.adapter = adapter

                binding.total.text = String.format(
                    "%.2f",
                    (list.sumOf { it.price }.div(100) + list.sumOf { it.shipping }
                        .div(100) + list.sumOf { it.tax }.div(100))
                )
                binding.subtotal.text = String.format("%.2f", list.sumOf { it.price }.div(100))
                binding.shipping.text = String.format("%.2f", list.sumOf { it.shipping }.div(100))
                binding.tax.text = String.format("%.2f", list.sumOf { it.tax }.div(100))

                adapter.onItemSelectedListener = object :
                    ProductAdapter.OnItemSelectedListener {
                    override fun onProductClicked(
                        product: Product,
                        options: ActivityOptionsCompat
                    ) {
                        val intent = Intent(this@MainActivity, DetailActivity::class.java)
                        intent.putExtra(ExtraKey.PRODUCT.toString(), product)

                        startActivity(intent, options.toBundle())
                    }
                }
            }
        })
    }
}