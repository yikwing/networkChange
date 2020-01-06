package com.rongyi.mylibrary

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class Network(val netType: NetType = NetType.AUTO)