package com.sevenpeakssoftware.amirnaghavi.base.presentation

import com.sevenpeakssoftware.amirnaghavi.base.domain.ErrorEntity


/**
 * Base UI state.
 */
data class CoreState(
        var error: ErrorEntity = ErrorEntity.NoError,
        var loading: Boolean = true,
) {

    fun noErrorNoLoading() = this.copy(
            error = ErrorEntity.NoError,
            loading = false,
    )

    fun onErrorNoLoading(failure: ErrorEntity) = this.copy(
            error  = ErrorEntity.Unknown(failure.message),
            loading = false,
    )

    fun loading() = this.copy(
            error = ErrorEntity.NoError,
            loading = true,
    )

}
