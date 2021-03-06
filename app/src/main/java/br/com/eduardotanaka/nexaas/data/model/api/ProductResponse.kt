package br.com.eduardotanaka.nexaas.data.model.api

import br.com.eduardotanaka.nexaas.data.model.api.base.ApiResponseObject

/*
[
  {
    "name": "Pencil",
    "quantity": 1,
    "stock": 5,
    "image_url": "https://github.com/charleston10/test-android-nexaas/blob/master/assets/pencil.png?raw=true",
    "price": 150,
    "tax": 162,
    "shipping": 50,
    "description": "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam nunc magna, gravida ut orci non, egestas venenatis libero. Sed luctus, turpis at porta commodo, ipsum orci volutpat sapien, ut scelerisque diam massa lobortis odioc."
  },
]
 */
data class ProductResponse(
    val products: List<ProductModel>,
) : ApiResponseObject