package com.example.mvc.advice

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler

//@RestControllerAdvice
class GlobalControllerAdvice {
    //REST API Errors 글로벌하게 모아준다
    //basePackage를 지정하여 특정 패키지나 클래스만 태울수 있다
    @ExceptionHandler(value = [RuntimeException::class])
    fun exception(e: Exception): String {
        return "Global Common Server Error"
    }

    @ExceptionHandler(value = [IndexOutOfBoundsException::class])
    fun indexOutOfBoundsException(e: IndexOutOfBoundsException): ResponseEntity<String> { //return이 string이면 200이 내려감
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Index Error, 원래는 알려주면 안되는 정보")
    }
}