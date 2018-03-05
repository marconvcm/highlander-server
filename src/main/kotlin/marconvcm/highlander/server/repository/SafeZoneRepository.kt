package marconvcm.highlander.server.repository

import marconvcm.highlander.server.entity.SafeZone
import org.springframework.data.repository.CrudRepository

interface SafeZoneRepository: CrudRepository<SafeZone, String>