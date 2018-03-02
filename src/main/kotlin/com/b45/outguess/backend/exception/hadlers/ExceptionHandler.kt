package com.b45.outguess.backend.exception.hadlers

import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody

@ControllerAdvice
class CustomExceptionHander {

    @ExceptionHandler(NotFoundException::class)
    fun handleNotFoundException(e: Exception): ResponseEntity<ErrorResponse> = ResponseEntity(
            ErrorResponse(e.message ?: "Not Found"), HttpHeaders(), HttpStatus.NOT_FOUND)

    @ExceptionHandler(RuntimeException::class)
    fun handleOtherException(e: Exception) = ResponseEntity(
    ErrorResponse(e.message ?:"Internal Server Error"), HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR)


    data class ErrorResponse(val message: String)

}