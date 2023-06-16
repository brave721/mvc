package com.example.mvc.model.http

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDateTime

data class ErrorResponse(
    @field:JsonProperty("result_code")
    var resultCode: String? = null,
    @field:JsonProperty("http_status")
    var httpStatus: String? = null,
    var httpMethod: String? = null,
    var message: String? = null,
    var path: String? = null,
    var timestamp: LocalDateTime? = null,
    var errors: MutableList<Error>? = mutableListOf()
) {

}

data class Error(
    var field: String? = null,
    var message: String? = null,
    var value: Any? = null
) {

}

//{
//    "result_code" : "OK",
//    "http_status" : "400",
//    "message" : "요청 잘못됨",
//    "path" : "/api/exception/hello",
//    "timestamp" : "2020-10-02T13:00:00",
//    "errors" : [
//    {
//        "field" : "_name",
//        "message" : "5글자 이상이어야합니다"
//    }
//    ]
//}