package com.example.mvc.controller.exception

import com.example.mvc.model.http.ErrorResponse
import com.example.mvc.model.http.UserRequest
import org.springframework.http.HttpStatus
import org.springframework.validation.FieldError
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime
import javax.servlet.http.HttpServletRequest
import javax.validation.ConstraintViolationException
import javax.validation.Valid
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@Validated
@RestController
@RequestMapping("/api/exception")
class ExceptionApiController {
    //연습용이다
    @GetMapping("/hello")
    fun hello(): String {
//        throw RuntimeException("강제 exception 발생")
        val list = mutableListOf<String>()
//        val temp = list[0]
        return "hello"
    }

    @GetMapping("")
    fun get(
        @RequestParam
        @NotBlank
        @Size(min = 2, max = 6)
        name: String,
        @RequestParam
        @Min(10)
        age: Int
    ): String {
        println(name)
        println(age)
        return "$name $age"
    }

    @PostMapping("")
    fun post(@Valid @RequestBody userRequest: UserRequest): UserRequest {
        println(userRequest)
        return userRequest
    }

    @ExceptionHandler(value = [MethodArgumentNotValidException::class])
    fun methodArgumentNotValidException(
        e: MethodArgumentNotValidException,
        request: HttpServletRequest
    ): ErrorResponse {
        val errors = mutableListOf<com.example.mvc.model.http.Error>()
        e.bindingResult.allErrors.forEach { errorObject ->
            val error = com.example.mvc.model.http.Error().apply {
                this.field = (errorObject as FieldError).field
                this.message = errorObject.defaultMessage
                this.value = errorObject.rejectedValue
            }
        }

        val errorResponse = ErrorResponse().apply {
            this.resultCode = "fail2"
            this.httpStatus = HttpStatus.BAD_REQUEST.value().toString()
            this.httpMethod = request.method
            this.message = "요청에 에러가 있습니다"
            this.path = request.requestURI.toString()
            this.timestamp = LocalDateTime.now()
            this.errors = errors
        }
        return errorResponse
    }

    @ExceptionHandler(value = [ConstraintViolationException::class])
    fun constraintViolation(
        e: ConstraintViolationException,
        request: HttpServletRequest
    ): ErrorResponse {
        //1.에러분석
        var errors = mutableListOf<com.example.mvc.model.http.Error>()
        e.constraintViolations.forEach {
            val field = it.propertyPath.last().name
            val message = it.message
            val error = com.example.mvc.model.http.Error().apply {
                this.field = field
                this.message = message
            }
            errors.add(error)
        }
        //2. Error Response
        val errorResponse = ErrorResponse().apply {
            this.resultCode = "fail"
            this.httpStatus = HttpStatus.BAD_REQUEST.value().toString()
            this.httpMethod = request.method
            this.message = "요청에 에러가 있습니다"
            this.path = request.requestURI.toString()
            this.timestamp = LocalDateTime.now()
            this.errors = errors
        }
        return errorResponse
    }

}