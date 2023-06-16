package com.example.mvc.controller.get

import com.example.mvc.model.http.UserRequest
import org.springframework.web.bind.annotation.*

@RestController // REST API Controller 동작
@RequestMapping("/api") //http://localhost:8080/api
class GetApiController {
    @GetMapping(path = ["/hello", "/abcd"]) //GET http://localhost:8080/api/hello
    fun hello(): String {
        return "hello kotlin spring boot"
    }

    //옛날방식
    @RequestMapping(method = [RequestMethod.GET], path = ["/request-mapping"]) // 메소드 제한없이 쓸수 있는 함수
    fun requestMapping(): String {
        return "request-mapping"
    }

    @GetMapping("/get-mapping/path-variable/{name}/{age}")
    fun pathVariable(@PathVariable name: String, @PathVariable age: Int): String {
        println("$name, $age")
        return "$name, $age"
    }

    // http://localhost:8080/api/page?key=value&key2=value2
    @GetMapping("/get-mapping/query-param")
    fun queryParam(
        @RequestParam name: String,
        @RequestParam age: Int
    ): String {
        println("query param$name, $age")
        return "query param$name, $age"
    }

    //name age address email 한번에 객체로 받기
    //객체로 받을떄는 phone-number 같은것이 넘길수 없으니 고려해야한
    @GetMapping("/get-mapping/query-param/object")
    fun queryParamObject(userRequest: UserRequest): UserRequest {
        println(userRequest)
        return userRequest
    }

    //여기는 map으로 받아 phone-number를 받아 따로 처리할수 잇음
    @GetMapping("/get-mapping/query-param/map")
    fun queryParamMap(@RequestParam map: Map<String, Any>): Map<String, Any> {
        println(map)
        return map
    }
}