package marconvcm.highlander.server.entity

import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.constraints.Email
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class Person(
    @Id
    val id: String,
    @Email
    @Column(unique = true)
    val email: String
)