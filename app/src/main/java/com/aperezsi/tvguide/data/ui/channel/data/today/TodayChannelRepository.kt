package com.aperezsi.tvguide.data.ui.channel.data.today

import android.annotation.TargetApi
import android.os.Build
import android.util.Log
import com.aperezsi.tvguide.data.data.ChannelProgamming
import com.aperezsi.tvguide.data.data.ProgramResponse
import com.aperezsi.tvguide.data.service.ProgramAPI
import com.aperezsi.tvguide.data.ui.channel.ChannelPresenter
import com.aperezsi.tvguide.data.utils.helpers.TimeHelper
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by alberto on 31/05/2018.
 */
class TodayChannelRepository (private val channelPresenter: ChannelPresenter) : ITodayChannelRepository {

}