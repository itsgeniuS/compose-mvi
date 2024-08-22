package com.android.core.app

import com.android.core.app.env.DeploymentType
import com.android.core.app.env.EnvironmentData
import com.android.data.remote.common.ApiType

/**
 * Created by ThulasiRajan.P on 9/8/2024
 */
object EnvironmentManager {
    val environments = mutableListOf(
        EnvironmentData(
            apiType = ApiType.GAMES_URL,
            deploymentType = DeploymentType.DEV
        ),
    )

    fun getBaseUrl(apiType: ApiType): String {
        return environments.find { it.apiType == apiType }!!.baseUrl
    }
}
