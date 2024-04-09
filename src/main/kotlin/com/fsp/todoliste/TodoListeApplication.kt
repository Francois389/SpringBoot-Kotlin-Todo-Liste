package com.fsp.todoliste

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TodoListeApplication

fun main(args: Array<String>) {
    runApplication<TodoListeApplication>(*args)
}
