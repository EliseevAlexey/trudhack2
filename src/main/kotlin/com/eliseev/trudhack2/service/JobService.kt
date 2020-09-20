package com.eliseev.trudhack2.service

import com.eliseev.trudhack2.dao.JobDao
import com.eliseev.trudhack2.model.JobModel
import org.springframework.stereotype.Service

interface JobService {
    fun getByTestModelResponse(response: Int): JobModel?
    fun getByName(name: String): JobModel
}

@Service
class JobServiceImpl(private val jobDao: JobDao) : JobService {

    override fun getByTestModelResponse(response: Int): JobModel? =
        when (response) {
            1 -> getByName("Кладовщик")
            2 -> getByName("Водитель погрузчика")
            3 -> getByName("Официант")
            else -> null
        }

    override fun getByName(name: String): JobModel = jobDao.findByName(name)
}
