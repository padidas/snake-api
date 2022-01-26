package de.padidas.snakeapi.scores

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime

@RestController
@CrossOrigin(origins = ["*"])
@RequestMapping("/scores")
class ScoreController(private val scoreRepository: ScoreRepository, private val scoreMongoRepo: ScoreMongoRepo) {

    @GetMapping
    fun getTenHighestScores(): ResponseEntity<List<Score>> {
        println("GET REQ *********************************")
        val scores = scoreRepository.findTenHighestScores()
        return ResponseEntity.ok(scores)
    }

    @PostMapping
    fun createOrUpdateScore(@RequestBody scoreReq: ScoreReq): ResponseEntity<Score> {
        println("POST REQ ********************************")
        val score = Score(
            id = scoreReq.scoreId,
            score = scoreReq.score,
            username = if (scoreReq.username.trim().isEmpty()) "Anonymous" else scoreReq.username.trim(),
            snakeLength = scoreReq.snakeLength,
            modifiedDate = LocalDateTime.now(),
        )
        return ResponseEntity.ok(scoreMongoRepo.save(score))
    }
}

data class ScoreReq(
    val scoreId: String,
    val username: String,
    val score: Int,
    val snakeLength: Int,
)