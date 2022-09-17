//package com.example.MedicalAssistance.controller
//
//
//import com.example.MedicalAssistance.model.Patient
//import com.example.MedicalAssistance.model.PatientRequest
//import com.example.MedicalAssistance.model.User
//import com.example.MedicalAssistance.repository.PatientRepository
//import com.example.MedicalAssistance.service.PatientLoginService
//import com.example.MedicalAssistance.service.PatientService
//import io.kotlintest.shouldBe
//import io.mockk.every
//import io.mockk.mockk
//import io.mockk.verify
//import org.junit.jupiter.api.Test
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
//import org.springframework.boot.test.context.TestConfiguration
//import org.springframework.context.annotation.Bean
//import org.springframework.http.MediaType
//import org.springframework.test.web.reactive.server.WebTestClient
//import org.springframework.test.web.reactive.server.returnResult
//import reactor.core.publisher.Flux
//import reactor.core.publisher.Mono
//
//@WebFluxTest(PatientLoginController::class)
//class PatientLoginControllerTest{
//
//    @Autowired
//    lateinit var webTestClient : WebTestClient
//
//
//    @Autowired
//    lateinit var patientLoginService: PatientLoginService
//    @Autowired
//    lateinit var patientService: PatientService
//
//    @TestConfiguration
//    class ControllerTestConfig{
//        @Bean
//        fun patientLoginService() = mockk<PatientLoginService>()
//        @Bean
//        fun patientService() = mockk<PatientService>()
//    }
//
//    @Test
//
//    fun `should return signed in user `() {
//        val patient1 = PatientRequest()
//
//
//        val expectedResult = mapOf("username" to "akp",
//            "password" to "Akp@00")
//
//        every {
//            patientLoginService.patientLogin(patient1)
//        } returns Mono.just(patient1)
//
//
//        val response= webTestClient.get()
//            .uri("/AllPatients")
//            .accept(MediaType.APPLICATION_JSON)
//            .exchange()// invoking the end point
//            .expectStatus().is2xxSuccessful
//            .returnResult<Any>()
//            .responseBody
//
//        response.blockFirst() shouldBe expectedResult
//        verify(exactly=1){
//            patientLoginService.patientLogin()
//        }
//    }
//}