package com.candybytes.taco.ui.vm.category

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.viewModelScope
import com.candybytes.taco.data.repository.CategoryRepository
import com.candybytes.taco.ui.vm.BaseViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class CategoriesViewModel @ViewModelInject constructor(
    private val categoryRepository: CategoryRepository
) : BaseViewModel<
        CategoriesContract.Event,
        CategoriesContract.State,
        CategoriesContract.Effect
        >() {

    init {
        viewModelScope.launch {
            categoryRepository.categories
                .collect {
                    it.getOrNull()?.let {
                        setState { copy(categories = it, isLoading = false) }
                    }

                    it.exceptionOrNull()?.let {
                        setState { copy(isLoading = false) }
                        setEffect(CategoriesContract.Effect.ErrorMessage(it.message ?: ""))
                    }
                }
        }
    }

    override fun setInitialState(): CategoriesContract.State {
        return CategoriesContract.State(
            categories = emptyList(),
            isLoading = true
        )
    }

    override fun handleEvents(event: CategoriesContract.Event) {
        when (event) {
            is CategoriesContract.Event.CategoryClicked -> {
                setEffect(CategoriesContract.Effect.NavigateTo.CategoryScreen(event.id))
            }
        }
    }

}
