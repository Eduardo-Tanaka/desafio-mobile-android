package br.com.eduardotanaka.nexaas.di.module

import br.com.eduardotanaka.nexaas.data.repository.RetrofitTesteRepository
import br.com.eduardotanaka.nexaas.data.repository.RetrofitTesteRepositoryImpl
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
    abstract fun bindRetrofitTesteRepository(retrofitTesteRepository: RetrofitTesteRepositoryImpl): RetrofitTesteRepository

}