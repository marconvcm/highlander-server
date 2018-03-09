package marconvcm.highlander.server.task

import marconvcm.highlander.server.service.SafeZoneService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class CleanupTask(
    @Autowired private val safeZoneService: SafeZoneService) {

    @Scheduled(fixedRate = 1000)
    fun run() {
        safeZoneService.cleanup(5000)
    }
}