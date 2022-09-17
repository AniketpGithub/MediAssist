package com.example.MedicalAssistance.controller

import com.example.MedicalAssistance.model.BookAppointment

import com.example.MedicalAssistance.service.BookAppointmentService

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


@WebFluxTest(BookAppointmentController::class)
class BookAppointmentControllerTest{

    @Autowired
    lateinit var webTestClient : WebTestClient



    @Autowired
    lateinit var bookAppointmentService: BookAppointmentService

    @TestConfiguration
    class ControllerTestConfig{
        @Bean
        fun bookAppointmentService() = mockk<BookAppointmentService>()


    }

    @Test
    fun `should return list of all the Appointments `() {
        val bookApp = BookAppointment(patientId="1", patientName="Aniket", doctorName="Abcd", address="Mumbai",
            email="akp@gmail.com", mobileNumber="8329498688", dateofAppointment="10/09/2022",
            reason="Headache", )



        val expectedResult = mapOf("patientId" to "1",
            "patientName" to "Aniket",
            "doctorName" to "Abcd",
            "address" to "Mumbai",
            "email" to "akp@gmail.com",
            "mobileNumber" to "8329498688",
            "dateofAppointment" to "10/09/2022",
            "reason" to "Headache")

        every {
            bookAppointmentService.findAllAppointments()
        } returns Flux.just(bookApp)


        val response= webTestClient.get()
            .uri("/allAppointments")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()// invoking the end point
            .expectStatus().is2xxSuccessful
            .returnResult<Any>()
            .responseBody

        response.blockFirst() shouldBe expectedResult
        verify(exactly=1){
            bookAppointmentService.findAllAppointments()
        }
    }



    @Test

    fun `should create Appointment when create api is being called `(){

        val expectedResponse = mapOf("patientId" to "1",
            "patientName" to "Aniket",
            "doctorName" to "Abcd",
            "address" to "Mumbai",
            "email" to "akp@gmail.com",
            "mobileNumber" to "8329498688",
            "dateofAppointment" to "10/09/2022",
            "reason" to "Headache")

        val bookApp = BookAppointment(patientId="1", patientName="Aniket", doctorName="Abcd", address="Mumbai",
            email="akp@gmail.com", mobileNumber="8329498688", dateofAppointment="10/09/2022",
            reason="Headache", )


        every {
            bookAppointmentService.addAppointment(bookApp)
        }returns Mono.just(bookApp)


        val response= webTestClient.post()
            .uri("/bookAppointment")
            .bodyValue(bookApp)
            .exchange()// invoking the end point
            .expectStatus().is2xxSuccessful
            .returnResult<Any>()
            .responseBody

        response.blockFirst() shouldBe expectedResponse
        verify(exactly=1){
            bookAppointmentService.addAppointment(bookApp)
        }
    }


    @Test

    fun `should able to update appointment`(){

        val expectedResponse = mapOf("patientId" to "1",
            "patientName" to "Aniket",
            "doctorName" to "Abcd",
            "address" to "Mumbai",
            "email" to "akp@gmail.com",
            "mobileNumber" to "8329498688",
            "dateofAppointment" to "10/09/2022",
            "reason" to "Headache")

        val bookApp = BookAppointment(patientId="1", patientName="Aniket", doctorName="Abcd", address="Mumbai",
            email="akp@gmail.com", mobileNumber="8329498688", dateofAppointment="10/09/2022",
            reason="Headache", )


        every {
            bookAppointmentService.updateAppointment("1",bookApp)
        }returns Mono.just(bookApp)


        val response= webTestClient.put()
            .uri("/updateAppointment/1")
            .bodyValue(bookApp)
            .exchange()// invoking the end point
            .expectStatus().is2xxSuccessful

        verify(exactly = 1){
            bookAppointmentService.updateAppointment("1",bookApp)
        }
    }



    @Test

    fun `should able to delete appointment by id`(){

        val expectedResponse = mapOf("patientId" to "1",
            "patientName" to "Aniket",
            "doctorName" to "Abcd",
            "address" to "Mumbai",
            "email" to "akp@gmail.com",
            "mobileNumber" to "8329498688",
            "dateofAppointment" to "10/09/2022",
            "reason" to "Headache")

        val bookApp = BookAppointment(patientId="1", patientName="Aniket", doctorName="Abcd", address="Mumbai",
            email="akp@gmail.com", mobileNumber="8329498688", dateofAppointment="10/09/2022",
            reason="Headache", )

        every {
            bookAppointmentService.deleteById("1")
        }returns Mono.empty()


        val response= webTestClient.delete()
            .uri("/appointment/1")
            .exchange()// invoking the end point
            .expectStatus().is2xxSuccessful

        verify(exactly = 1){
            bookAppointmentService.deleteById("1")
        }
    }
}