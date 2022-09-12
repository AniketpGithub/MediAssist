package com.example.MedicalAssistance.controller

import com.example.MedicalAssistance.model.Patient
import com.example.MedicalAssistance.repository.PatientRepository
import com.example.MedicalAssistance.service.PatientService
import io.kotlintest.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.http.MediaType

import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.reactive.server.returnResult
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono


@WebFluxTest(PatientController::class)
class PatientControllerTest{
    @Autowired
    lateinit var webTestClient :WebTestClient

    @Autowired
    lateinit var patientRepository: PatientRepository

    @Autowired
    lateinit var patientService: PatientService

    @TestConfiguration
    class ControllerTestConfig{
      @Bean
      fun patientService() = mockk<PatientService>()

        @Bean
        fun patientRepository() = mockk<PatientRepository>()
    }

    @Test

    fun `should return list of all the patients `() {
        val patient1 = Patient(patientId="1", patientFirstName="Aniket", patientLastName="Pashte", userName="Akp",
            mobileNumber="8329498688", email="akp@gmail.com", gender="male", dob="08/06/2021",
            password="Akp@00", address="Mumbai")


        val expectedResult = mapOf("patientId" to "1",
            "patientFirstName" to "Aniket",
            "patientLastName" to "Pashte",
            "userName" to "Akp",
            "mobileNumber" to "8329498688",
            "email" to "akp@gmail.com",
            "gender" to "male",
            "dob" to "08/06/2021",
            "password" to "Akp@00",
            "address" to "Mumbai")

        every {
            patientService.findAllPatients()
        }returns Flux.just(patient1)


        val response= webTestClient.get()
            .uri("/AllPatients")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()// invoking the end point
            .expectStatus().is2xxSuccessful
            .returnResult<Any>()
            .responseBody

        response.blockFirst() shouldBe expectedResult
        verify(exactly=1){
            patientService.findAllPatients()
        }
    }


    @Test

    fun `should create patient when create api is being called `(){

        val expectedResponse = mapOf("patientId" to "2",
            "patientFirstName" to "John",
            "patientLastName" to "Cena",
            "userName" to "jcena",
            "mobileNumber" to "7866557788",
            "email" to "jc@gmail.com",
            "gender" to "male",
            "dob" to "12/09/2000",
            "password" to "jc@1234",
            "address" to "NYC")

        val patient = Patient(patientId="2", patientFirstName="John", patientLastName="Cena", userName="jcena",
            mobileNumber="7866557788", email="jc@gmail.com", gender="male", dob="12/09/2000",
            password="jc@1234", address="NYC")


        every {
            patientService.addPatient(patient)
        }returns Mono.just(patient)


        val response= webTestClient.post()
            .uri("/CreatePatients")
            .bodyValue(patient)
            .exchange()// invoking the end point
            .expectStatus().is2xxSuccessful
            .returnResult<Any>()
            .responseBody

        response.blockFirst() shouldBe expectedResponse
        verify(exactly=1){
            patientService.addPatient(patient)
        }
    }

    @Test

    fun `should able to update patient`(){

        val expectedResponse = mapOf("patientId" to "2",
            "patientFirstName" to "John",
            "patientLastName" to "Cena",
            "userName" to "jcena",
            "mobileNumber" to "7866557788",
            "email" to "jc@gmail.com",
            "gender" to "male",
            "dob" to "12/09/2000",
            "password" to "jc@1234",
            "address" to "NYC")

        val patient = Patient(patientId="2", patientFirstName="John", patientLastName="Cena", userName="jcena",
            mobileNumber="7866557788", email="jc@gmail.com", gender="male", dob="12/09/2000",
            password="jc@1234", address="NYC")


        every {
            patientService.updatePatientById("2",patient)
        }returns Mono.just(patient)


        val response= webTestClient.put()
            .uri("/update/2")
            .bodyValue(patient)
            .exchange()// invoking the end point
            .expectStatus().is2xxSuccessful

        verify(exactly = 1){
            patientService.updatePatientById("2",patient)
        }
    }

}