package br.com.eduardotanaka.nexaas.util

import android.os.Looper

fun onMainThread() = Looper.myLooper() == Looper.getMainLooper()
