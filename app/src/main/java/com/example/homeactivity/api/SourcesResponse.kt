package com.example.homeactivity.api

import com.google.gson.annotations.SerializedName

data class SourcesResponse(

	@field:SerializedName("sources") //this line uses the name of field in thr api field and enable me if i want to change the the variable in the next line
	val sources: List<SourcesItem?>? = null,

	@field:SerializedName("status")
	val status: String? = null,

	@field:SerializedName("message") //this will return when occur failure in api request
	val message: String? = null,

	@field:SerializedName("code") //this will return when occur failure in api request
	val code: String? = null,


)

data class SourcesItem(

	@field:SerializedName("country")
	val country: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("language")
	val language: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("category")
	val category: String? = null,

	@field:SerializedName("url")
	val url: String? = null
)
