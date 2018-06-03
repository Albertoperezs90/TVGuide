package com.aperezsi.tvguide.data.ui.channel.data

import android.util.Log
import com.aperezsi.tvguide.data.service.ProgramAPI
import com.aperezsi.tvguide.data.ui.channel.ChannelPresenter
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by alberto on 31/05/2018.
 */
class ChannelRepository (private val channelPresenter: ChannelPresenter) : IChannelRepository {

    private val programApi by lazy {
        ProgramAPI.create()
    }

    override fun getChannelProgamming(idChannel: String) {
        programApi.getChannelProgamming(idChannel)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    apiResponse ->
                    channelPresenter.refreshDataList(apiResponse.response)
                }, {
                    error ->
                    //TODO implement logger
                    Log.e("refreshPrograms ERROR", error.message)
                })
    }
}