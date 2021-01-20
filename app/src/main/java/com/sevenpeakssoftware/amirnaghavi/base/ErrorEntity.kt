package com.sevenpeakssoftware.amirnaghavi.base



sealed class ErrorEntity {
    object Network : ErrorEntity() {
        override fun toString(): String {
            return this.javaClass.canonicalName ?: ""
        }
    }

    object NotFound : ErrorEntity() {
        override fun toString(): String {
            return this.javaClass.canonicalName ?: ""
        }
    }

    object AccessDenied : ErrorEntity() {
        override fun toString(): String {
            return this.javaClass.canonicalName ?: ""
        }
    }

    object ServiceUnavailable : ErrorEntity() {
        override fun toString(): String {
            return this.javaClass.canonicalName ?: ""
        }
    }

    data class Unknown(val message: String = "") : ErrorEntity() {
        override fun toString(): String {
            return this.javaClass.canonicalName ?: ""
        }
    }

    object NoError : ErrorEntity() {
        override fun toString(): String {
            return this.javaClass.canonicalName ?: ""
        }
    }

    fun isError() = isNotError().not()
    fun isNotError() = this == NoError
}