package com.eliseev.trudhack2.dao

import com.eliseev.trudhack2.model.JobModel
import org.springframework.data.jpa.repository.JpaRepository

interface JobDao : JpaRepository<JobModel, Long> {
    fun findByName(name: String): JobModel
}
