package com.b45.outguess.backend.exception.hadlers

class BadDataException(message: String) : RuntimeException(message)
class NotFoundException(message: String) : RuntimeException(message)
class IllegalStateException(message: String) : RuntimeException(message)