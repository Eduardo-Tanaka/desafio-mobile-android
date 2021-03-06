package br.com.eduardotanaka.nexaas.constants

enum class CacheKey {

    CACHE_NAME;

    override fun toString(): String {
        return name
    }
}