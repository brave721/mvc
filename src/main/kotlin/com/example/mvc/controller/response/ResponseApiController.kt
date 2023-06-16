package com.example.mvc.controller.response

import com.example.mvc.model.http.UserRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/response")
class ResponseApiController {
    // 1. get 4xx
    // GET http://localhost:8080/api/response
    @GetMapping("")
    fun getMapping(@RequestParam age: Int?): ResponseEntity<String> {
//        if (age == null) {
//            return ResponseEntity.badRequest().body("age 값이 비었어요")
//        }
//        if (age < 20) {
//            return ResponseEntity.badRequest().body("나이는 20 이상이어야합니다")
//        }
        return age?.let {
            if (it < 20) {
                return ResponseEntity.badRequest().body("나이는 20 이상이어야합니다")
            }
            ResponseEntity.ok("OK")
        } ?: kotlin.run {
            return ResponseEntity.badRequest().body("age 값이 비었어요")
        }
    }

    @PostMapping("")
    fun postMapping(@RequestBody userRequest: UserRequest?): ResponseEntity<UserRequest> {
        return ResponseEntity.status(200).body(userRequest)
    }

    @PutMapping("")
    fun putMapping(@RequestBody userRequest: UserRequest?): ResponseEntity<UserRequest> {
        //1. 기존데이터가 없어서 생성함
        return ResponseEntity.status(HttpStatus.CREATED).body(userRequest)
    }

    @DeleteMapping("/{id}")
    fun deleteMapping(@PathVariable id: Int): ResponseEntity<Nothing> {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null)
    }
}