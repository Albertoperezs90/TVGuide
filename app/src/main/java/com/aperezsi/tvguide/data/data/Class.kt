package com.aperezsi.tvguide.data.data

import android.os.Parcel
import android.os.Parcelable
import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion
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


data class ChannelProgamming(var today: List<ProgramResponse>? = null,
                             var tomorrow: List<ProgramResponse>? = null,
                             var tomorrow1: List<ProgramResponse>? = null,
                             var tomorrow2: List<ProgramResponse>? = null,
                             var tomorrow3: List<ProgramResponse>? = null)