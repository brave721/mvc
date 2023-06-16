package com.example.mvc.controller.put

import com.example.mvc.model.http.UserRequest
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.validation.FieldError
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api")
class PutApiController {
    @PutMapping("/put-mapping")
    fun putMapping(): String {
        return "put-mapping"
    }

    @RequestMapping(method = [RequestMethod.PUT], path = ["/request-mapping"])
    fun requestMapping(): String {
        return "request-mapping - put method"
    }

    @PutMapping(path = ["/put-mapping/object"])
    fun putMappingObject(
        @Valid @RequestBody userRequest: UserRequest,
        bindingResult: BindingResult
    ): ResponseEntity<String> {
        println("putMappingObject")
        if (bindingResult.hasErrors()) {
            //500 Error
            val msg = StringBuilder()
            bindingResult.allErrors.forEach {
                val field = it as FieldError
                val message = it.defaultMessage
                msg.append("$field : $msg\n")
            }
            return ResponseEntity.badRequest().body(msg.toString())
        }
        return ResponseEntity.ok("")
        //0. response
//        return UserResponse().apply {
//            //1. result
//            this.result = com.example.mvc.model.http.Result().apply {
//                this.resultCode = "OK"
//                this.resultMessage = "success"
//            }
//        }.apply {
//            //2. description
//            this.description = "이것은 성공에 대한 설명이다"
//        }.apply {
//            //3. user mutable list
//            var userList = mutableListOf<UserRequest>()
//            userList.add(userRequest)
//            userList.add(UserRequest().apply {
//                this.name = "aaa"
//                this.age = 10
//                this.email = "b@gmail.com"
//                this.address = "seoul"
//                this.phoneNumber = "010-1234-4321"
//
//            })
//            this.userRequest = userList
//        }
    }
}