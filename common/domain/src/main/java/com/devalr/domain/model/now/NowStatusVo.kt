package com.devalr.domain.model.now

import com.devalr.domain.model.enums.NowStatusType

data class NowStatusVo(val city:String, val temperature:String, val thermalSensation:String, val nowStatus: NowStatusType)