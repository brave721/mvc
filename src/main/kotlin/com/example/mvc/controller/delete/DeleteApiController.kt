package com.example.mvc.controller.delete

import org.jetbrains.annotations.NotNull
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import javax.validation.constraints.Min

@RestController
@RequestMapping("/api")
@Validated
class DeleteApiController {
    //1. path variable
    //2. request param
    @DeleteMapping(path = ["/delete-mapping"])
    fun deleteMapping(
        @RequestParam name: String?,
        @NotNull("age is null")
        @Min(value = 20, message = "age more than 20")
        @RequestParam age: Int?
    ): String {
        println(name)
        println(age)
        return "$name $age"
    }

    @DeleteMapping(path = ["/delete-mapping/name/{name}/age/{age}"])
    fun deleteMappingPath(
        @PathVariable(value = "name")
        @NotNull("name is null")
        _name: String,

        @PathVariable(value = "age")
        @NotNull("age is null")
        @Min(value = 20, message = "age more than 20")
        _age: Int,
    ): String {
        return "$_name $_age path_var"
    }
}