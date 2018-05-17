package com.aperezsi.tvguide.data.data

import java.io.Serializable

data class APIResponse (val response: List<ProgramResponse>) : Serializable
data class ProgramResponse (val genericType: String?,
                            val id: String?,
                            val idChannel: String?,
                            val type: String?,
                            val title: String?,
                            val category: String?,
                            val description: String?,
                            val score: String?,
                            val image: String?,
                            val epochStart: String?,
                            val epochEnd: String?) : Serializable