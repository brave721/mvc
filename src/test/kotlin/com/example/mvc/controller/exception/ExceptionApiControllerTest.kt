package com.example.mvc.controller.exception

import com.example.mvc.model.http.UserRequest
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.util.LinkedMultiValueMap

@WebMvcTest
@AutoConfigureMockMvc
class ExceptionApiControllerTest {
    @Autowired
    lateinit var mockMvc: MockMvc

    @Test
    fun helloTest() {
        mockMvc.perform(
            MockMvcRequestBuilders.get("/api/exception/hello")
        ).andExpect(
            MockMvcResultMatchers.status().isOk
        ).andExpect(
            MockMvcResultMatchers.content().string("hello")
        ).andDo(MockMvcResultHandlers.print())
    }

    @Test
    fun getTest() {
        val queryParams = LinkedMultiValueMap<String, String>()
        queryParams.add("name", "brave")
        queryParams.add("age", "36")

        mockMvc.perform(
            MockMvcRequestBuilders.get("/api/exception").queryParams(queryParams)
        ).andExpect(
            MockMvcResultMatchers.status().isOk
        ).andExpect(
            MockMvcResultMatchers.content().string("brave 36")
        ).andDo(MockMvcResultHandlers.print())
    }

    @Test
    fun getFailTest() {
        val queryParams = LinkedMultiValueMap<String, String>()
        queryParams.add("name", "brave")
        queryParams.add("age", "9")

        mockMvc.perform(
            MockMvcRequestBuilders.get("/api/exception").queryParams(queryParams)
        ).andExpect(
            MockMvcResultMatchers.status().isOk
        ).andExpect(
            MockMvcResultMatchers.content().contentType("application/json")
        ).andExpect(
            MockMvcResultMatchers.jsonPath("\$.result_code").value("fail")
        ).andDo(MockMvcResultHandlers.print())

//        {
//            "result_code": "fail",
//            "http_status": "400",
//            "httpMethod": "GET",
//            "message": "요청에 에러가 있습니다",
//            "path": "/api/exception",
//            "timestamp": "2022-09-03T18:26:11.905",
//            "errors":[
//            {
//                "field": "name",
//                "message": "크기가 2에서 6 사이여야 합니다",
//                "value": null
//            }
//            ]
//        }
    }

    @Test
    fun postTest() {
        val userRequest = UserRequest().apply {
            this.name = "brave"
            this.age = 36
            this.phoneNumber = "010-1234-1234"
            this.address = "수원시"
            this.email = "brave@kakao.com"
            this.createdAt = "2022-09-03 18:34:00"
        }

        val json = jacksonObjectMapper().writeValueAsString(userRequest)
        println(json)

        mockMvc.perform(
            MockMvcRequestBuilders.post("/api/exception")
                .content(json)
                .contentType("application/json")
                .accept("application/json")
        ).andExpect(
            MockMvcResultMatchers.status().isOk
        ).andExpect(
            MockMvcResultMatchers.jsonPath("\$.name").value("brave")
        ).andDo(MockMvcResultHandlers.print())
    }

    @Test
    fun postFailTest() {
        val userRequest = UserRequest().apply {
            this.name = "brave"
            this.age = 7
            this.phoneNumber = "010-1234-122234"
            this.address = "수원시"
            this.email = "brave@kakao.com"
            this.createdAt = "2022-09-03 18:34:00"
        }

        val json = jacksonObjectMapper().writeValueAsString(userRequest)
        println(json)

        mockMvc.perform(
            MockMvcRequestBuilders.post("/api/exception")
                .content(json)
                .contentType("application/json")
                .accept("application/json")
        ).andExpect(
            MockMvcResultMatchers.status().isOk
        ).andExpect(
            MockMvcResultMatchers.jsonPath("\$.result_code").value("fail2")
        ).andDo(MockMvcResultHandlers.print())
    }
}