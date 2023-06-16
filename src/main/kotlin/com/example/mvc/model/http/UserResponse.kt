package com.example.mvc.model.http

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming

data class UserResponse(
    var result: com.example.mvc.model.http.Result? = null,
    var description: String? = null,

    @JsonProperty("user")
    var userRequest: List<UserRequest>? = null
)

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class Result(
    var resultCode: String? = null,
    var resultMessage: String? = null
)

