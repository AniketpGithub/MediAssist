package com.example.MedicalAssistance.service


import com.example.MedicalAssistance.model.Patient
import com.example.MedicalAssistance.repository.PatientRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono


@Service
class PatientService(

    @Autowired
    val patientRepository: PatientRepository
    )

{
    fun findAllPatients() : Flux<Patient> {
        return patientRepository.findAll()
    }

    fun addPatient(patient: Patient): Mono<Patient> {
        return patientRepository.save(patient)

    }
    fun updatePatientById(id:String, patient: Patient):Mono<Patient>{
        return patientRepository.save(patient)
    }

    fun deleteById(id: String): Mono<Void>{
        return findById(id)
            .flatMap(patientRepository::delete)
    }



//    fun deleteAll(id: String): Mono<Void>{
//        return patientRepository.deleteAll()
//    }

    fun findById(id: String): Mono<Patient>{
        return patientRepository.findById(id)
    }



}