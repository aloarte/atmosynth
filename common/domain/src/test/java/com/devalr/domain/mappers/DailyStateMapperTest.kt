package com.devalr.domain.mappers

import com.devalr.data.dto.dailyweather.daily.DailySkyValueInTimeDto
import com.devalr.domain.mappers.Mapper
import com.devalr.domain.mappers.daily.DailyStateMapper
import com.devalr.domain.model.enums.DayTime
import com.devalr.domain.model.enums.SkyState
import com.devalr.domain.model.weather.daily.DailySkyState
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class DailyStateMapperTest {

    private lateinit var dailyStateMapper: DailyStateMapper
    private val dayTimeMapper: Mapper<String, DayTime> = mockk()
    private val dailySkyStateMapper: Mapper<String, SkyState> = mockk()

    @Before
    fun setUp() {
        dailyStateMapper = DailyStateMapper(dayTimeMapper, dailySkyStateMapper)
    }

    @Test
    fun `test transform with valid data`() {
        val dailySkyValueInTimeDto = DailySkyValueInTimeDto(value = "clear", description = "clear sky", time = "6")
        val dayTime = DayTime.Morning
        val skyState = SkyState.DayClear
        val expected = DailySkyState(state = skyState, description = "clear sky", time = dayTime)

        every { dayTimeMapper.transform("6") } returns dayTime
        every { dailySkyStateMapper.transform("clear") } returns skyState

        val result = dailyStateMapper.transform(dailySkyValueInTimeDto)
        assertEquals(expected, result)
    }

    @Test
    fun `test transform with null time`() {
        val dailySkyValueInTimeDto = DailySkyValueInTimeDto(value = "clear", description = "clear sky", time = null)
        val skyState = SkyState.DayClear
        val expected = DailySkyState(state = skyState, description = "clear sky", time = DayTime.Unknown)

        every { dailySkyStateMapper.transform("clear") } returns skyState

        val result = dailyStateMapper.transform(dailySkyValueInTimeDto)
        assertEquals(expected, result)
    }
}