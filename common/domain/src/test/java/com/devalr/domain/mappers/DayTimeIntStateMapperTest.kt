package com.devalr.domain.mappers

import com.devalr.domain.mappers.daily.DayTimeIntStateMapper
import com.devalr.domain.model.enums.DayTime
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class DayTimeIntStateMapperTest {

    private lateinit var dayTimeIntStateMapper: DayTimeIntStateMapper

    @Before
    fun setUp() {
        dayTimeIntStateMapper = DayTimeIntStateMapper()
    }

    @Test
    fun `test transform with valid data`() {
        assertEquals(DayTime.EarlyMorning, dayTimeIntStateMapper.transform(6))
        assertEquals(DayTime.Morning, dayTimeIntStateMapper.transform(12))
        assertEquals(DayTime.Afternoon, dayTimeIntStateMapper.transform(18))
        assertEquals(DayTime.Night, dayTimeIntStateMapper.transform(24))
    }

    @Test
    fun `test transform with unknown time`() {
        assertEquals(DayTime.Unknown, dayTimeIntStateMapper.transform(0))
        assertEquals(DayTime.Unknown, dayTimeIntStateMapper.transform(5))
        assertEquals(DayTime.Unknown, dayTimeIntStateMapper.transform(25))
    }
}