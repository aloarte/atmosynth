package com.devalr.domain.mappers

import com.devalr.domain.mappers.daily.DayTimeStringStateMapper
import com.devalr.domain.model.enums.DayTime
import org.junit.Assert.assertEquals
import org.junit.Test

class DayTimeStringStateMapperTest {

    private val mapper = DayTimeStringStateMapper()

    @Test
    fun `test transform EarlyMorning`() {
        assertEquals(DayTime.EarlyMorning, mapper.transform("00-06"))
    }

    @Test
    fun `test transform Morning`() {
        assertEquals(DayTime.Morning, mapper.transform("06-12"))
    }

    @Test
    fun `test transform Afternoon`() {
        assertEquals(DayTime.Afternoon, mapper.transform("12-18"))
    }

    @Test
    fun `test transform Night`() {
        assertEquals(DayTime.Night, mapper.transform("18-24"))
    }

    @Test
    fun `test transform unknown time`() {
        assertEquals(DayTime.Unknown, mapper.transform("unknown"))
    }
}