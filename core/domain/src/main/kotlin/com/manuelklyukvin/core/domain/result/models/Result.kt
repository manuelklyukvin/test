package com.manuelklyukvin.core.domain.result.models

sealed interface Result<D, E> {

    data class Success<D, E>(val data: D): Result<D, E>
    data class Error<D, E>(val error: E): Result<D, E>
}