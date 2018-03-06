package marconvcm.highlander.server.web

import marconvcm.highlander.server.repository.PersonRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HealthController(
    @Value("\${spring.jpa.database-platform}") private val databasePlatorm: String,
    @Autowired private val personRepository: PersonRepository) {

    @GetMapping("/healthcheck")
    fun check(): Map<String, Any> = mapOf(
        "online" to true,
        "databasePlatorm" to databasePlatorm,
        "personCount" to personRepository.count()
    )
}