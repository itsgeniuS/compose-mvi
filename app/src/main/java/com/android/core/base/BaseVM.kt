package com.android.core.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.core.events.NotifyEvents
import com.android.data.remote.common.ApiResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

/**
 * Created by ThulasiRajan.P on 8/8/2024
 */
abstract class BaseVM<STATE : BaseViewState<*>, ACTION>(
    private val mainDispatcher: CoroutineDispatcher = Dispatchers.Main,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
) : ViewModel() {

    private val notifyEvents = Channel<NotifyEvents>(
        capacity = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST,
    )
    val notifyEventsChannel = notifyEvents.receiveAsFlow()

    private val _uiState = MutableStateFlow<BaseViewState<*>>(BaseViewState.Empty)
    val uiState: StateFlow<BaseViewState<*>> = _uiState.asStateFlow()

    abstract fun onTriggerEvent(action: ACTION)

    protected fun setState(state: STATE) = viewModelScope.launch {
        _uiState.emit(state)
    }

    fun sendEvent(event: NotifyEvents) {
        when (event) {
            is NotifyEvents.ToggleLoading -> {
                viewModelScope.launch(mainDispatcher) {
                    notifyEvents.trySend(event)
                }
            }

            else -> {
                viewModelScope.launch {
                    notifyEvents.trySend(event)
                }
            }
        }
    }

    fun toggleLoading(canShowLoading: Boolean) {
        viewModelScope.launch(mainDispatcher) {
            notifyEvents.trySend(NotifyEvents.ToggleLoading(canShowLoading))
        }
    }

    fun <T> callApi(
        api: Flow<ApiResult<T>>,
        onSuccess: (ApiResult.Success<T>) -> Unit = {},
        onError: (ApiResult.Error<T>) -> Unit = {},
    ) {
        api.onStart {
            toggleLoading(true)
        }.onEach { result ->
            toggleLoading(false)

            if (result is ApiResult.Success) {
                onSuccess.invoke(result)
            } else if (result is ApiResult.Error) {
                onError.invoke(result)
            }
        }.onCompletion {
            toggleLoading(false)
        }.launchIn(viewModelScope)
    }
}
