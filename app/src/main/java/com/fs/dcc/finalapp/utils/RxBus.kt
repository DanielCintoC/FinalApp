package com.fs.dcc.finalapp.utils

import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

/**
 * Created by danielcintoconde on 11/12/18.
 * Singleton - una sola instancia del objeto
 */
object RxBus {

    private val publisher = PublishSubject.create<Any>()

    /**
     * onNext - Cuando se publica todos los que esten suscritos o escuchando eventos va a recibir
     * este valor (event). Es el que propaga, se publica en el event bus y cuando ese event bus
     * le habla a los que estan escuchando se hace con el onNext.
     */
    fun publish(event: Any) {
        publisher.onNext(event)
    }

    fun <T> listen(eventType: Class<T>): Observable<T> = publisher.ofType(eventType)

}