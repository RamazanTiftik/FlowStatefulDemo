package com.ramazantiftik.flowstateful

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn

class FirstScreenVM : ViewModel(){

    private var count = 0

    val counter= flow {
        while (true) {
            emit(count++)
            kotlinx.coroutines.delay(1000)
            println("running flow")

        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000),0)
    // lazily -> gözlemlenmeye başladığı zaman çalışmaya başlar ve fragment kill edilse dahi çalışır
    //whilesubscried -> gözlemlendiği süree çalışır, fragment kapanınca çalışmaz yani fragment kill edilirse çalışmaz
    //-> whilesubscried a parametre olarak long time verebiliriz, mesela 5 saniye kill edildikten sonra müddet verilebilir
    //Eagerly -> gözlemlemeye başlamadan çalışır ve fragment kill edilse dahi çalışır

}