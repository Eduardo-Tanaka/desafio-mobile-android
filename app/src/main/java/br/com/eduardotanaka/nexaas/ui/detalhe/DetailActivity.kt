package br.com.eduardotanaka.nexaas.ui.detalhe

import android.os.Bundle
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import br.com.eduardotanaka.nexaas.R
import br.com.eduardotanaka.nexaas.constants.ExtraKey
import br.com.eduardotanaka.nexaas.data.model.entity.Product
import br.com.eduardotanaka.nexaas.databinding.ActivityDetailBinding
import br.com.eduardotanaka.nexaas.ui.base.BaseActivity
import com.bumptech.glide.Glide

class DetailActivity : BaseActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val homeIcon = resources.getDrawable(R.drawable.ic_close_24px)
        homeIcon.setTint(resources.getColor(R.color.white))
        supportActionBar?.setHomeAsUpIndicator(homeIcon)

        val product = intent.getParcelableExtra<Product>(ExtraKey.PRODUCT.toString())

        binding.title.text = product?.name
        binding.subtitle.text = if (product?.stock!! > 0) "in stock" else "sold out"
        binding.price.text = "$ ${String.format("%.2f", product?.price.div(100))}"
        binding.description.text = product?.description

        // create a ProgressDrawable object which we will show as placeholder
        val drawable = CircularProgressDrawable(this)
        drawable.setColorSchemeColors(
            R.color.purple_500,
            R.color.purple_700,
            R.color.purple_200
        )
        drawable.centerRadius = 60f
        drawable.strokeWidth = 5f
        // set all other properties as you would see fit and start it
        drawable.start()

        Glide.with(this).load(product.imageUrl)
            .placeholder(drawable)
            .fitCenter() // this cropping technique scales the image so that it fills the requested bounds and then crops the extra.
            .into(binding.image)
    }
}