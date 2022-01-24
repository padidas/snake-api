package de.padidas.snakeapi.scores

import org.bson.types.ObjectId
import org.springframework.data.domain.Sort
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.Update
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

interface ScoreMongoRepo : MongoRepository<Score, String> {
}

@Repository
class ScoreRepository(val mongoTemplate: MongoTemplate) {

    fun findTenHighestScores(): List<Score> {
        val query = Query()
            .limit(10)
            .with(Sort.by("score").descending())
        return mongoTemplate.find(query, Score::class.java)
    }
}