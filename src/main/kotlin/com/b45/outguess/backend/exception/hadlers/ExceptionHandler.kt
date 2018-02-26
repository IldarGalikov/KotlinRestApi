package com.b45.outguess.backend.exception.hadlers

import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody

@ControllerAdvice
class CustomExceptionHander {

    @ExceptionHandler(NotFoundException::class)
    @ResponseBody
    fun handleNotFoundException(e: NotFoundException): ErrorResponse {
        return ErrorResponse(e.message ?: "not found")
    }

    @ResponseBody
    @ExceptionHandler(RuntimeException::class)
    fun handleOtherException(e: NotFoundException): ErrorResponse {
        return ErrorResponse(e.message ?: "Internsal Server Error ....")
    }


    data class ErrorResponse(val message: String)

}