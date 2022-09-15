package com.example.MedicalAssistance.service

import com.example.MedicalAssistance.model.BookAppointment
import com.example.MedicalAssistance.repository.BookAppointmentRepository
import io.kotlintest.shouldBe
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono


class BookAppointmentServiceTest{


    val apoointment1 = BookAppointment(patientId="1", patientName="Aniket", doctorName="Abcd", address="Mumbai",
        email="akp@gmail.com", mobileNumber="8329498688", dateofAppointment="10/09/2022",
        reason="Headache", )
    val apoointment2 = BookAppointment(patientId="2", patientName="Rohit", doctorName="XYZ", address="Pune",
        email="ro@gmail.com", mobileNumber="8776598769", dateofAppointment="1/12/2022",
        reason="fever", )

    private val bookAppointmentRepository = mockk<BookAppointmentRepository>(){
        every{
            findAll()
        } returns Flux.just(apoointment1,apoointment2)

        every{
            findById("2")
        } returns Mono.just(apoointment2)
    }

    private  val bookAppointmentService = BookAppointmentService(bookAppointmentRepository)

    @Test
    fun `should return all appointment`()
    {
        val firstAppointment = bookAppointmentService.findAllAppointments().blockFirst()
        val secondAppointment = bookAppointmentService.findAllAppointments().blockLast()

        firstAppointment shouldBe apoointment1
        secondAppointment shouldBe apoointment2
    }
}
