package marconvcm.highlander.server.service

import marconvcm.highlander.server.entity.SafeZone
import marconvcm.highlander.server.exception.ForbbidenException
import marconvcm.highlander.server.repository.SafeZoneRepository
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit
import java.util.*

@Service
class SafeZoneService(
    @Autowired private val safeZoneRepository: SafeZoneRepository) {

    val logger = LoggerFactory.getLogger(SafeZoneService::class.java)

    fun open(personId: String): SafeZone = safeZoneRepository.count().let { many ->
        when(many) {
            0L -> safeZoneRepository.save(
                SafeZone(
                    id = UUID.randomUUID().toString(),
                    personId = personId,
                    lastUpdate = LocalDateTime.now()
                )
            )
            else -> throw ForbbidenException()
        }
    }

    fun close(token: String): String = safeZoneRepository.findOne(token).let {
        safeZoneRepository.delete(it)
        "done"
    }

    fun keepAlive(token: String): String = safeZoneRepository.findOne(token).let {
        safeZoneRepository.save(it.copy(lastUpdate = LocalDateTime.now()))
        "updated"
    }

    fun cleanup(expireIn: Long = 1000 * 60) {
        logger.info("CleanUp started")
        safeZoneRepository.findAll().forEach { safeZone ->
            logger.info("Safezone $safeZone")
            when(safeZone) {
                is SafeZone -> {
                    val now = LocalDateTime.now()
                    val lastUpdate = safeZone.lastUpdate
                    ChronoUnit.MILLIS.between(lastUpdate, now).let { delta ->
                        if (delta > expireIn) {
                            safeZoneRepository.delete(safeZone.id)
                            logger.info("Removed $safeZone")
                        }
                    }

                }
                else -> 0
            }
        }
        logger.info("CleanUp finished")
    }
}