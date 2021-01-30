package com.sevenpeakssoftware.amirnaghavi.base


sealed class RepositoryStrategy {
    object OfflineFirst : RepositoryStrategy()
    object Remote : RepositoryStrategy()
}