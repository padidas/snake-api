package de.padidas.snakeapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SnakeApiApplication

fun main(args: Array<String>) {
	runApplication<SnakeApiApplication>(*args)
}
