package de.padidas.snakeapi.scores

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "timestamp-formatter")
class ScoreConfig {
    var iso: String? = null
}