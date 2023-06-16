package com.example.mvc.controller.page

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class PageController {
    @GetMapping("/main")
    fun main(): String {
        println("init main")
        //RestController가 안붙으면 html을 찾아서 내려준다
        return "main.html"
    }
}