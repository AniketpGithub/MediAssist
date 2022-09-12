package com.example.MedicalAssistance.model

import org.springframework.data.annotation.Id

data class Patient (

   @Id
   val patientId: String?,
   val patientFirstName: String?,
   val patientLastName: String?,
   val userName: String?,
   val mobileNumber: String?,
   val email: String?,
   val gender: String?,
   val dob: String?,
   val password: String?,
   val address: String?
        )