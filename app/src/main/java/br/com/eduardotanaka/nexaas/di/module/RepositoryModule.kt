package br.com.eduardotanaka.nexaas.di.module

import br.com.eduardotanaka.nexaas.data.repository.ProductRepository
import br.com.eduardotanaka.nexaas.data.repository.ProductRepositoryImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

/**
 * Classes repository colocar aqui
 */
@Module
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindProductRepository(productRepository: ProductRepositoryImpl): ProductRepository

}