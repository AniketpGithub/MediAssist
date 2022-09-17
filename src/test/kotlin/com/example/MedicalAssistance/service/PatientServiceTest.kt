package com.example.MedicalAssistance.service

import com.example.MedicalAssistance.model.Patient
import com.example.MedicalAssistance.repository.PatientRepository
import io.kotlintest.shouldBe
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono


class PatientServiceTest{

    val patient1 = Patient(patientId="1", patientFirstName="Aniket", patientLastName="Pashte", userName="Akp",
        mobileNumber="8329498688", email="akp@gmail.com", gender="male", dob="08/06/2021",
        password="Akp@00", address="Mumbai")
    val patient2 = Patient(patientId="2", patientFirstName="John", patientLastName="Cena", userName="jcena",
        mobileNumber="7866557788", email="jc@gmail.com", gender="male", dob="12/09/2000",
        password="jc@1234", address="NYC")

     private val patientRepository = mockk<PatientRepository>(){
         every{
             findAll()
         }returns Flux.just(patient1,patient2)

         every{
             findById("2")
         }returns Mono.just(patient2)




     }

    private  val patientService = PatientService(patientRepository)

    @Test
    fun `should return all patients`()
    {
        val firstPatient = patientService.findAllPatients().blockFirst()
        val secondPatient = patientService.findAllPatients().blockLast()

        firstPatient shouldBe patient1
        secondPatient shouldBe patient2
    }

    @Test
    fun `should create Appointment `(){

        val patient = Patient(patientId="1", patientFirstName="Aniket", patientLastName="Pashte", userName="Akp",
            mobileNumber="8329498688", email="akp@gmail.com", gender="male", dob="08/06/2021",
            password="Akp@00", address="Mumbai")
        every{
            patientRepository.save(patient)
        }returns Mono.just(patient)
        val addPatient = patientService.addPatient(patient).block()

        addPatient shouldBe patient

    }
}