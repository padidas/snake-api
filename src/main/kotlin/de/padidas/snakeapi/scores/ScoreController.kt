package de.padidas.snakeapi.scores

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime

@RestController
@CrossOrigin(origins = ["*"])
@RequestMapping("/scores")
class ScoreController(private val scoreRepository: ScoreRepository, private val scoreMongoRepo: ScoreMongoRepo) {

    @GetMapping("/topScores")
    fun getTopScores(): ResponseEntity<List<Score>> {
        println("GET /scores/topScores *********************************")
        val scores = scoreRepository.findTenHighestScores()
        return ResponseEntity.ok(scores)
    }

    @GetMapping("/topPlayers")
    fun getTopPlayers(): ResponseEntity<List<Score>> {
        println("GET /scores/topPlayers *********************************")
        val scores = scoreRepository.findTenBestPlayers()
        return ResponseEntity.ok(scores)
    }

    @GetMapping("/activeScores")
    fun getActiveScores(): ResponseEntity<List<Score>> {
        println("GET /scores/activeScores *********************************")
        val scores = scoreRepository.findActiveScores()
        return ResponseEntity.ok(scores)
    }

    @PostMapping
    fun createOrUpdateScore(@RequestBody scoreReq: ScoreReq): ResponseEntity<Score> {
        println("POST /scores ********************************")
        val score = Score(
            id = scoreReq.scoreId,
            score = scoreReq.score,
            username = if (scoreReq.username.trim().isEmpty()) "anonymous" else scoreReq.username.trim().lowercase(),
            snakeLength = scoreReq.snakeLength,
            modifiedDate = LocalDateTime.now(),
            privateMode = scoreReq.privateMode,
        )
        return ResponseEntity.ok(scoreMongoRepo.save(score))
    }
}

data class ScoreReq(
    val scoreId: String,
    val username: String,
    val score: Int,
    val snakeLength: Int,
    val privateMode: Boolean,
)