package com.orion.zenite.model

import androidx.lifecycle.MutableLiveData

data class Token(
    val message: String, // "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJmZXJuYW5kYS5lc3RldmVzQHplbml0ZS5jb20uYnIiLCJleHAiOjM3ODkxMzIwOTExfQ.xVdfEOI0Lids6FmdKQppsK9eQgnc4_RaTJt2icLAoWY"
    val value: Boolean
)
