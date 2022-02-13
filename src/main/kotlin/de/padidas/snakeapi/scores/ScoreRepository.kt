package de.padidas.snakeapi.scores

import org.springframework.data.domain.Sort
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import java.time.LocalDateTime


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

//    fun findTenBestPlayers(): List<Score> {
//        val aggregation = newAggregation(
//            sort(Sort.Direction.DESC, "score"),
//            group("_id", "username")
//                .first("username").`as`("username")
//                .first("score").`as`("score")
//                .first("snakeLength").`as`("snakeLength")
//                .first("modifiedDate").`as`("modifiedDate"),
//            sort(Sort.Direction.DESC, "score"),
//            limit(10)
//        )
//
//        val groupResult = mongoTemplate.aggregate(aggregation, Score::class.java, Score::class.java)
//        return groupResult.mappedResults
//    }
//
//
//    /*
//    db.score.aggregate([
//        { "$sort": { "score": -1 } },
//        { "$group": {
//            "_id": "$username",
//            "username": { "$first": "$username" },
//            "score": { "$first": "$score" },
//            "snakeLength": { "$first": "$snakeLength" }
//        }}
//    ])
//    .sort({"score": -1})
//    .limit(10)
//     */


    fun findActiveScores(): List<Score> {
        val nowMinus10Seconds = LocalDateTime.now().minusSeconds(10)
        val query = Query()
            .addCriteria(
                Criteria().andOperator(
                    Criteria.where("modifiedDate").gt(nowMinus10Seconds),
                    Criteria.where("privateMode").`is`(false)
                )
            )
            .limit(8)
            .with(Sort.by("modifiedDate").descending())
        return mongoTemplate.find(query, Score::class.java)
    }
}