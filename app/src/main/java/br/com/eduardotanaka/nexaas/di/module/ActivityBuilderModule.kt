package br.com.eduardotanaka.nexaas.di.module

import br.com.eduardotanaka.nexaas.ui.MainActivity
import br.com.eduardotanaka.nexaas.ui.detalhe.DetailActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Colocar as activities aqui e caso necessário importar os modules
 */
@Module
abstract class ActivityBuilderModule {

    @ContributesAndroidInjector
    abstract fun contributesMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun contributesDetailActivity(): DetailActivity
}