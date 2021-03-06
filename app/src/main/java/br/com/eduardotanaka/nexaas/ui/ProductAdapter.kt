package br.com.eduardotanaka.nexaas.ui

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import br.com.eduardotanaka.nexaas.R
import br.com.eduardotanaka.nexaas.data.model.entity.Product
import br.com.eduardotanaka.nexaas.databinding.LayoutProductBinding
import com.bumptech.glide.Glide

class ProductAdapter(
    var products: List<Product>,
    val context: Context
) : RecyclerView.Adapter<ProductAdapter.MainViewHolder>() {

    var onItemSelectedListener: OnItemSelectedListener? = null

    interface OnItemSelectedListener {
        fun onProductClicked(filme: Product, options: ActivityOptionsCompat)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductAdapter.MainViewHolder {
        val itemBinding =
            LayoutProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ProductAdapter.MainViewHolder, position: Int) {
        holder.bind(products[position])
    }

    override fun getItemCount(): Int {
        return products.count()
    }

    inner class MainViewHolder(private val rowView: LayoutProductBinding) :
        RecyclerView.ViewHolder(rowView.root) {
        private lateinit var product: Product

        fun bind(product: Product) {
            this.product = product

            // create a ProgressDrawable object which we will show as placeholder
            val drawable = CircularProgressDrawable(context)
            drawable.setColorSchemeColors(
                R.color.purple_500,
                R.color.purple_700,
                R.color.purple_200
            )
            drawable.centerRadius = 30f
            drawable.strokeWidth = 5f
            // set all other properties as you would see fit and start it
            drawable.start()

            Glide.with(context).load(this.product.imageUrl)
                .placeholder(drawable)
                .fitCenter() // this cropping technique scales the image so that it fills the requested bounds and then crops the extra.
                .into(rowView.image)

            rowView.title.text = this.product.name
            rowView.subtitle.text = if (this.product.stock > 0) "in stock" else "sold out"
            rowView.price.text = "$ ${String.format("%.2f", this.product.price.div(100))}"

            rowView.rowProduct.setOnClickListener {
                val pair1 = Pair.create<View, String>(rowView.image, "image")
                val pair2 = Pair.create<View, String>(rowView.title, "title")
                val pair3 = Pair.create<View, String>(rowView.subtitle, "subtitle")
                val pair4 = Pair.create<View, String>(rowView.price, "price")

                val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                    context as Activity,
                    pair1, pair2, pair3, pair4
                )

                val options1 = ActivityOptionsCompat.makeClipRevealAnimation(
                    rowView.rowProduct,
                    rowView.rowProduct.measuredWidth,
                    rowView.rowProduct.measuredHeight,
                    rowView.rowProduct.measuredWidth,
                    rowView.rowProduct.measuredHeight
                )
                val options2 = ActivityOptionsCompat.makeScaleUpAnimation(
                    rowView.rowProduct,
                    rowView.rowProduct.measuredWidth,
                    rowView.rowProduct.measuredHeight,
                    rowView.rowProduct.measuredWidth,
                    rowView.rowProduct.measuredHeight
                )
                val options3 = ActivityOptionsCompat.makeBasic()

                onItemSelectedListener?.onProductClicked(product, options)
            }
        }
    }

    fun updateItems(newList: List<Product>) {
        products = newList
        notifyDataSetChanged()
    }

}