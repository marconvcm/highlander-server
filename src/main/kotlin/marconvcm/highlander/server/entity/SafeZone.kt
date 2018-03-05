package marconvcm.highlander.server.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class SafeZone(
    @Id
    @JsonProperty("token")
    val id: String,
    @get:JsonIgnore
    val personId: String,
    @get:JsonIgnore
    val lastUpdate: LocalDateTime
)