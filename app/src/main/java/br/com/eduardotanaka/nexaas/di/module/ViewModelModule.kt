package br.com.eduardotanaka.nexaas.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.eduardotanaka.nexaas.di.ViewModelFactory
import br.com.eduardotanaka.nexaas.di.ViewModelKey
import br.com.eduardotanaka.nexaas.ui.MainActivityViewModelImpl
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Singleton

/**
 * Usado para injetar dependencias nos ViewModels
 */
@Module
abstract class ViewModelModule {

    @Binds
    @Singleton
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainActivityViewModelImpl::class)
    abstract fun bindMainActivityViewModel(mainActivityViewModelImpl: MainActivityViewModelImpl): ViewModel

}