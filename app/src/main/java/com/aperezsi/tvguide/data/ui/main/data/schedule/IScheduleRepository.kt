package com.aperezsi.tvguide.data.ui.main.data.schedule

import com.aperezsi.tvguide.data.data.ProgramResponse
import com.aperezsi.tvguide.data.data.ScheduleProgramming

interface IScheduleRepository {

    fun mapProgramResponseToScheduleProgamming(programs: List<ProgramResponse>)
}