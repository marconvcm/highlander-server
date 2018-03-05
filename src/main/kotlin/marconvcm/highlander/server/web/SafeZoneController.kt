package marconvcm.highlander.server.web

import marconvcm.highlander.server.entity.SafeZone
import marconvcm.highlander.server.service.SafeZoneService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class SafeZoneController(
    @Autowired val safeZoneService: SafeZoneService) {

    @PutMapping("/open/:personId")
    fun open(@PathVariable("personId") personId: String): SafeZone = safeZoneService.open(personId)

    @DeleteMapping("/close/:token")
    fun close(@PathVariable("token") token: String): String = safeZoneService.close(token)

    @GetMapping("/keepalive/:token")
    fun keepAlive(@PathVariable("token") token: String): String = safeZoneService.keepAlive(token)
}