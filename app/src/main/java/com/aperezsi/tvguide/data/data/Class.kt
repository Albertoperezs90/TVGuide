package com.aperezsi.tvguide.data.data

import java.io.Serializable

data class APIResponse (val response: List<ProgramResponse>) : Serializable
data class ProgramResponse (val GenericType: String?,
                            val Id: String?,
                            val IdChannel: String?,
                            val Type: String?,
                            val Title: String?,
                            val Category: String?,
                            val Description: String?,
                            val Score: String?,
                            val Image: String?,
                            val EpochStart: String?,
                            val EpochEnd: String?) : Serializable