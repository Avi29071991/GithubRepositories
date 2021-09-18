package com.avinash.test.githubtrendingrepos.api.model

import com.google.gson.annotations.SerializedName

data class Item(
    val id : Int = 0,
    @SerializedName("node_id") val nodeId : String? = null,
    val name : String? = null,
    @SerializedName("full_name") val fullName : String? = null,
    val private : Boolean = false,
    @SerializedName("html_url") val htmlUrl : String? = null,
    val description : String? = null,
    val url : String? = null,
    @SerializedName("created_at") val createdAt : String? = null,
    @SerializedName("updated_at") val updatedAt : String? = null,
    @SerializedName("pushed_at") val pushedAt : String? = null,
    @SerializedName("git_url") val gitUrl : String? = null,
    @SerializedName("ssh_url") val sshUrl : String? = null,
    @SerializedName("clone_url") val cloneUrl : String? = null,
    val homepage : String? = null,
    val size : Int = 0,
    val language : String? = null,
    val archived : Boolean = false,
    val disabled : Boolean = false,
    val score : Int = 0
)