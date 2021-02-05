package com.sevenpeakssoftware.amirnaghavi.base.domain


sealed class RepositoryStrategy {
    object OfflineFirst : RepositoryStrategy()
    object Remote : RepositoryStrategy()
}