package br.com.eduardotanaka.nexaas.data.model.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import br.com.eduardotanaka.nexaas.data.model.api.ProductResponse
import br.com.eduardotanaka.nexaas.data.model.entity.base.ReflectsApiResponse
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class Product(
    @PrimaryKey
    var name: String = "",
    var quantity: Int = 0,
    var stock: Int = 0,
    var imageUrl: String = "",
    var price: Double = 0.0,
    var tax: Double = 0.0,
    var shipping: Double = 0.0,
    var description: String = ""
) : ReflectsApiResponse<List<Product>, ProductResponse>, Parcelable {
    override fun reflectFrom(apiResponse: ProductResponse): List<Product> {
        val products = ArrayList<Product>()
        apiResponse.products.let { result ->
            result.map {
                products.add(
                    Product(
                        it.name,
                        it.quantity,
                        it.stock,
                        it.imageUrl,
                        it.price,
                        it.tax,
                        it.shipping,
                        it.description
                    )
                )
            }
        }

        return products
    }
}