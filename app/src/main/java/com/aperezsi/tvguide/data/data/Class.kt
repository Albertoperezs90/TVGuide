package com.aperezsi.tvguide.data.data

import android.os.Parcel
import android.os.Parcelable
import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion
import java.io.Serializable

/* REGION PROGRAMS */
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

data class ScheduleProgramming(var scheduleProgramming: MutableList<ProgramResponse>)
/* ENDREGION PROGRAMS */

/* REGION USERS */

data class User(var id: String = "", var nickname: String = "", var email: String = "", var password: String = "", var avatar: String = "") : Serializable

/* ENDREGION USERS */


/* REGION CHAT */

data class Messages(var keys: MutableList<String> = mutableListOf(), var messages: MutableList<Message> = mutableListOf())
data class Chat (var id: String = "", var idProgram: String = "", var epochEnd: String = "", var messages: HashMap<String, Message> = hashMapOf()) : Serializable
data class Message(var id: String = "", var user: User = User(), var message: String = "") : Serializable

/* ENDREGION CHAT */