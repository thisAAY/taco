package com.candybytes.taco.ui.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

interface ViewEvent
interface ViewState
interface ViewSideEffect

abstract class BaseViewModel<
        Event : ViewEvent,
        UiState : ViewState,
        Effect : ViewSideEffect,
        > : ViewModel() {
    private val initialState: UiState by lazy { setInitialState() }
    abstract fun setInitialState(): UiState

    private val _viewState: MutableStateFlow<UiState> by lazy { MutableStateFlow(initialState) }
    val viewState: StateFlow<UiState> by lazy { _viewState }

    private val _event: MutableSharedFlow<Event> = MutableSharedFlow()

    private val _effect: Channel<Effect> = Channel()
    val effect: Flow<Effect> = _effect.receiveAsFlow()

    init {
        subscribeToEvents()
    }

    fun setEvent(event: Event) {
        viewModelScope.launch { _event.emit(event) }
    }

    protected fun setState(reducer: UiState.() -> UiState) {
        val newState = viewState.value.reducer()
        _viewState.value = newState
    }

    private fun subscribeToEvents() {
        viewModelScope.launch {
            _event.collect {
                handleEvents(it)
            }
        }
    }

    protected abstract fun handleEvents(event: Event)

    protected fun setEffect(effect: Effect) {
        viewModelScope.launch { _effect.send(effect) }
    }
}
