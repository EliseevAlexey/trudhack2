package com.eliseev.trudhack2.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.OneToMany

@Entity
class JobModel(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val name: String,
    @OneToMany
    @JoinColumn(name = "job_id", referencedColumnName = "id")
    var questions: List<Question> = emptyList()
)

data class JobModelDto(
    val id: Long,
    val name: String,
    val udpipeName: String
)

fun JobModel.toDto(udpipeName: String) =
    JobModelDto(
        id = id!!,
        name = JobEnumDto.findByName(name).name,
        udpipeName = udpipeName
    )
