package com.android.core.events


/**
 * Created by ThulasiRajan.P on 08/08/2024
 */
sealed interface NotifyEvents {
    data object Init : NotifyEvents

    data class Navigate(val route: String) : NotifyEvents

    data class ToggleLoading(val isLoading: Boolean) : NotifyEvents

    data class ShowModalSheet(val showSupportModalSheet: Boolean, val showDemoModalSheet: Boolean) : NotifyEvents

    data object RefreshScreen : NotifyEvents

    data class ShowInfoDialog(
       val showInfoDialog: Boolean
    ) : NotifyEvents

    data object OnBack : NotifyEvents

    data object NoInternet : NotifyEvents

    data class UnAuthorized(
        val title: String,
        val msg: String,
    ) : NotifyEvents

    data class ToggleLoadMore(val isLoadMoreLoading: Boolean) : NotifyEvents

    data class ShowError(
        val title: String? = null,
        val message: String? = null,
        val errorCode: String? = null,
        val errorStr: String? = null,
        val isInfoDialog: Boolean = false,
    ) : NotifyEvents
}
