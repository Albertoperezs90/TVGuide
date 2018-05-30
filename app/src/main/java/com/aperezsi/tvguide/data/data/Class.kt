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
                            val EpochEnd: String?) : Serializable, SearchSuggestion {


    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 1
    }

    override fun getBody(): String {
        return Title!!
    }


    companion object CREATOR : Parcelable.Creator<ProgramResponse> {
        override fun createFromParcel(parcel: Parcel): ProgramResponse {
            return ProgramResponse(parcel)
        }

        override fun newArray(size: Int): Array<ProgramResponse?> {
            return arrayOfNulls(size)
        }
    }
}