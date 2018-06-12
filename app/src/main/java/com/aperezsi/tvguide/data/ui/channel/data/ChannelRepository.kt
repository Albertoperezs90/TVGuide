package com.aperezsi.tvguide.data.ui.channel.data

import android.annotation.TargetApi
import android.os.Build
import android.util.Log
import com.aperezsi.tvguide.data.data.ChannelProgamming
import com.aperezsi.tvguide.data.data.ProgramResponse
import com.aperezsi.tvguide.data.service.interfaces.ProgramAPI
import com.aperezsi.tvguide.data.ui.channel.ChannelPresenter
import com.aperezsi.tvguide.data.utils.helpers.TimeHelper
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by alberto on 31/05/2018.
 */
class ChannelRepository (private val channelPresenter: ChannelPresenter) : IChannelRepository {

    private val programApi by lazy {
        ProgramAPI.create()
    }

    override fun getChannelProgramming(idChannel: String) {
        programApi.getChannelProgamming(idChannel)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    apiResponse ->
                    sortChannelProgramming(apiResponse.response.toMutableList())
                }, {
                    error ->
                    //TODO implement logger
                    Log.e("refreshPrograms ERROR", error.message)
                })
    }

    @TargetApi(Build.VERSION_CODES.N)
    override fun sortChannelProgramming(channelProgamming: MutableList<ProgramResponse>) {
        val channelSortedProgamming = ChannelProgamming()
        val todayEpoch = TimeHelper().getEpochDate()
        val tomorrowEpoch = TimeHelper().getEpochDate(1)
        val tomorrow1Epoch = TimeHelper().getEpochDate(2)
        val tomorrow2Epoch = TimeHelper().getEpochDate(3)
        val tomorrow3Epoch = TimeHelper().getEpochDate(4)

        channelSortedProgamming.today = channelProgamming.filter { it.EpochStart!!.toLong() > todayEpoch
                                                                   && it.EpochStart.toLong() < tomorrowEpoch }
        channelProgamming.removeAll { it.EpochStart!!.toLong() < tomorrowEpoch }

        channelSortedProgamming.tomorrow = channelProgamming.filter { it.EpochStart!!.toLong() < tomorrow1Epoch }
        channelProgamming.removeAll { it.EpochStart!!.toLong() < tomorrow1Epoch }

        channelSortedProgamming.tomorrow1 = channelProgamming.filter { it.EpochStart!!.toLong() < tomorrow2Epoch }
        channelProgamming.removeAll { it.EpochStart!!.toLong() < tomorrow2Epoch }

        channelSortedProgamming.tomorrow2 = channelProgamming.filter { it.EpochStart!!.toLong() < tomorrow3Epoch }
        channelProgamming.removeAll { it.EpochStart!!.toLong() < tomorrow3Epoch }

        channelSortedProgamming.tomorrow3 = channelProgamming

        channelPresenter.refreshDataList(channelSortedProgamming)
    }

}