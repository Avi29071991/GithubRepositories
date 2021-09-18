package com.avinash.test.githubtrendingrepos.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "repository")
data class RepositoryItem(
    @PrimaryKey
    var repositoryId: Int? = null,
    var name : String? = null,
    var fullName : String? = null,
    var private : Boolean = false,
    var description : String? = null,
    var url : String? = null,
    var createdAt : String? = null,
    var updatedAt : String? = null,
    var gitUrl : String? = null,
    var sshUrl : String? = null,
    var cloneUrl : String? = null,
    var homepage : String? = null,
    var size : Int = 0,
    var language : String? = null,
    var archived : Boolean = false,
    var disabled : Boolean = false,
    var score : Int = 0
)
