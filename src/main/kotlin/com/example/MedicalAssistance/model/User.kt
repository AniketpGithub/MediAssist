package com.example.MedicalAssistance.model

import org.springframework.data.annotation.Id

class User {

    @Id
    var id: Long? = null
    var username: String? = null
    var password: String? = null

    constructor() {}
    constructor(username: String?, password: String?) : super() {
        this.username = username
        this.password = password
    }
}