package com.candybytes.taco.ui.vm.category

import com.candybytes.taco.domain.model.Category
import com.candybytes.taco.ui.vm.ViewEvent
import com.candybytes.taco.ui.vm.ViewSideEffect
import com.candybytes.taco.ui.vm.ViewState

sealed class CategoriesContract {
    sealed class Event : ViewEvent {
        data class CategoryClicked(val id: Int) : Event()
    }

    data class State(
        val categories: List<Category>
    ) : ViewState

    sealed class Effect : ViewSideEffect {
        data class ErrorMessage(val message: String) : Effect()

        sealed class Navigation : Effect() {
            data class CategoryScreen(val id: Int) : Navigation()
        }
    }
}
