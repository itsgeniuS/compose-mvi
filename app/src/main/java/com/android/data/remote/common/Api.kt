package com.android.data.remote.common

/**
 * Created by ThulasiRajan.P on 9/8/2024
 */
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class Api(val apiType: ApiType)
