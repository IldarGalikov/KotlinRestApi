package com.b45.outguess.backend.exception.hadlers

import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus

@Controller
class CustomExceptionHander {

    @ExceptionHandler(NotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleNotFoundException(e: NotFoundException): ErrorResponse {
        return ErrorResponse(e.message ?: "not found")
    }

    @ExceptionHandler(RuntimeException::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun handleOtherException(e: NotFoundException): ErrorResponse {
        return ErrorResponse(e.message ?: "Internsal Server Error ....")
    }


    data class ErrorResponse(val message: String)

}