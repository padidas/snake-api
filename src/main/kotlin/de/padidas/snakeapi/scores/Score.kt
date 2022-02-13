package de.padidas.snakeapi.scores

import org.springframework.data.annotation.Id
import java.time.LocalDateTime

data class Score(
    @Id
    val id: String,
    val username: String,
    val score: Int,
    val snakeLength: Int,
    val modifiedDate: LocalDateTime = LocalDateTime.now(),
    val privateMode: Boolean = false,
)
