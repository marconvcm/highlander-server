package marconvcm.highlander.server.service

import marconvcm.highlander.server.entity.Person
import marconvcm.highlander.server.payload.RegisterPayload
import marconvcm.highlander.server.repository.PersonRepository
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class PersonService(
    @Autowired private val personRepository: PersonRepository) {

    val logger = LoggerFactory.getLogger(PersonService::class.java)

    fun register(payload: RegisterPayload): Person {

        logger.info("Payload: $payload")

        return personRepository.save(
            Person (
                id = UUID.randomUUID().toString(),
                email = payload.email
            )
        ).let {
            logger.info("Entity: $it")
            it
        }
    }
}