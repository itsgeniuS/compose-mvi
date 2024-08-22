package com.android.core.app.env

import com.android.data.remote.common.ApiType

/**
 * Created by ThulasiRajan.P on 9/8/2024
 */
data class EnvironmentData(
    val apiType: ApiType,
    val deploymentType: DeploymentType
) {

    var baseUrl = ""

    init {
        setBaseUrl(apiType, deploymentType)
    }

    fun setBaseUrl(
        apiType: ApiType,
        deploymentType: DeploymentType
    ) {
        val prefix = when (deploymentType) {
            DeploymentType.DEV -> "alpha-"
            DeploymentType.SIT -> "beta-"
            DeploymentType.PROD -> ""
        }
        baseUrl = "https://$prefix${apiType.url}"
    }

    override fun toString(): String {
        return baseUrl
    }

}