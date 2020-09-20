package com.eliseev.trudhack2.config

import com.eliseev.trudhack2.dao.JobDao
import com.eliseev.trudhack2.dao.QuestionDao
import com.eliseev.trudhack2.model.JobModel
import com.eliseev.trudhack2.model.Question
import com.eliseev.trudhack2.model.QuestionDataType.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class InitDB {

    @Autowired
    private lateinit var modelDao: JobDao

    @Autowired
    private lateinit var questionDao: QuestionDao

    @Bean
    fun init() = CommandLineRunner {

        modelDao.save(JobModel(name = "Кладовщик")).also {
            it.questions = questionDao.saveAll(createQuestions1(it))
            modelDao.save(it)
        }
        modelDao.save(JobModel(name = "Водитель погрузчика")).also {
            it.questions = questionDao.saveAll(createQuestions2(it))
            modelDao.save(it)
        }
        modelDao.save(JobModel(name = "Официант")).also {
            it.questions = questionDao.saveAll(createQuestions3(it))
            modelDao.save(it)
        }
    }


    private fun createQuestions1(jobModel: JobModel): List<Question> {
        return listOf(
            Question(null, jobModel, "q1", "Желаемая должность", STRING),
            Question(null, jobModel, "q2", "У вас есть опыт работы кладовщиком?", BOOLEAN),
            Question(null, jobModel, "q2d", "У вас есть опыт работы кладовщиком?", STRING),
            Question(null, jobModel, "q3", "Укажите Ваш стаж работы кладовщиком", INTEGER),
            Question(null, jobModel, "q4", "Отрасль", STRING_ARRAY),
            Question(null, jobModel, "q5", "Названия компаний", STRING_ARRAY),
            Question(null, jobModel, "q6", "С какой системой хранения Вы имели опыт работы?", STRING_ARRAY),
            Question(null, jobModel, "q7", "Опыт работы с программами", STRING_ARRAY),
            Question(null, jobModel, "q8", "Опыт с инструментарием", STRING_ARRAY),
            Question(null, jobModel, "q9", "Типы работ", STRING_ARRAY),
            Question(null, jobModel, "q10", "Сколько времени вы потратили на поиск работы кладовщика?", INTEGER),
            Question(null, jobModel, "q11", "Уровень ЗП (желаемый)", INTEGER)
        )
    }

    private fun createQuestions2(jobModel: JobModel): List<Question> {
        return listOf(
            Question(null, jobModel, "q1", "Желаемая должность", STRING),
            Question(null, jobModel, "q2", "У вас есть водительские права?", BOOLEAN),
            Question(null, jobModel, "q3", "Водительское удостоверение", STRING),
            Question(null, jobModel, "q4", "Удостоверение тракториста-машиниста нового образца", STRING),
            Question(null, jobModel, "q5", "Удостоверение тракториста-машиниста старого образца", STRING),
            Question(null, jobModel, "q6", "У вас есть опыт работы водителем погрузчиком?", BOOLEAN),
            Question(null, jobModel, "q7", "Ваш стаж работы водителем погрузчика", INTEGER),
            Question(null, jobModel, "q8", "Сфера деятельности", STRING_ARRAY),
            Question(null, jobModel, "q8", "Сфера деятельности", STRING_ARRAY),
            Question(null, jobModel, "q9", "Названия компаний", STRING_ARRAY),
            Question(null, jobModel, "q10", "Какой техникой вы управляете?", STRING_ARRAY),
            Question(null, jobModel, "q11", "Максимальная высота поднятия груза", INTEGER),
            Question(null, jobModel, "q12", "С какой системой хранения Вы имели опыт работы?", STRING_ARRAY),
            Question(null, jobModel, "q13", "удостоверение стропальщика?", BOOLEAN),
            Question(null, jobModel, "q14", "Типы работ", STRING_ARRAY),
            Question(null, jobModel, "q15", "Возможность провести тест-драйв на собеседовании", BOOLEAN),
            Question(null, jobModel, "q16", "Ключевые качества", STRING_ARRAY),
            Question(null, jobModel, "q17", "Знание языков", STRING_ARRAY),
            Question(null, jobModel, "q19", "Хобби", STRING),
            Question(null, jobModel, "q20", "Уровень ЗП", INTEGER),
            Question(
                null,
                jobModel, "q21",
                "График работы (1 - полный, 2 - неполный день, 3 - сменный, 4 - удалённая работа, 5 - вахтовый метод",
                INTEGER
            ),
            Question(null, jobModel, "q22", "Ночная смена", BOOLEAN),
            Question(null, jobModel, "q23", "Командировки", BOOLEAN),
            Question(null, jobModel, "q24", "Станции метро", STRING_ARRAY)
        )
    }

    private fun createQuestions3(jobModel: JobModel): List<Question> {
        return listOf(
            Question(null, jobModel, "q1", "Желаемая должность", STRING),
            Question(
                null,
                jobModel,
                "q2",
                "Есть ли у Вас профессиональное образование в сфере гостеприимства?",
                BOOLEAN
            ),
            Question(
                null,
                jobModel, "q3",
                "профессиональное образовательное учреждение, где вы учились, и специальность",
                STRING
            ),
            Question(null, jobModel, "q4", "Год окончания обучения", INTEGER),
            Question(null, jobModel, "q5", "Стаж работы официантом", BOOLEAN),
            Question(null, jobModel, "q6", "Рестораны, где работал", STRING_ARRAY),
            Question(null, jobModel, "q7", "дополнительные профессиональные знания и навыки", STRING_ARRAY),
            Question(
                null,
                jobModel, "q8",
                "Знание языков.Уровень владения (5 - native speaker, 4 - fluent, 3 - advanced,  2- intermediate, 1- pre-intermediate)",
                STRING_ARRAY
            ),
            Question(null, jobModel, "q9", "C какой посудой работали", STRING_ARRAY),
            Question(
                null,
                jobModel, "q10",
                "График работы (1 - полный, 2 - неполный день, 3 - сменный, 6 - ночная смена)",
                INTEGER_ARRAY
            )
        )
    }

}
