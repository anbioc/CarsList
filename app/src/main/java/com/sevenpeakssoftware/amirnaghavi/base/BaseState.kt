package com.sevenpeakssoftware.amirnaghavi.base



data class BaseState(
        var error: ErrorEntity = ErrorEntity.NoError,
        var loading: Boolean = true,
) {

    fun noError() = this.copy(
            error = ErrorEntity.NoError,
    )

    fun noErrorNoLoading() = this.copy(
            error = ErrorEntity.NoError,
            loading = false,
    )

    fun onErrorNoLoading(failure: ErrorEntity) = this.copy(
            error  = ErrorEntity.NoError,
            loading = false,
    )

    fun loading() = this.copy(
            error = ErrorEntity.NoError,
            loading = true,
    )

}
