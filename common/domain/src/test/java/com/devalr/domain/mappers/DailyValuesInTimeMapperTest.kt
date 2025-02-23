package com.devalr.domain.mappers

import com.devalr.data.dto.dailyweather.daily.IntValueInTimeDto
import com.devalr.domain.mappers.Mapper
import com.devalr.domain.mappers.daily.DailyValuesInTimeMapper
import com.devalr.domain.model.enums.DayTime
import com.devalr.domain.model.weather.daily.ValuesDayTimeBo
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class DailyValuesInTimeMapperTest {

    private lateinit var dailyValuesInTimeMapper: DailyValuesInTimeMapper
    private val intDayTimeMapper: Mapper<Int, DayTime> = mockk()

    @Before
    fun setUp() {
        dailyValuesInTimeMapper = DailyValuesInTimeMapper(intDayTimeMapper)
    }

    @Test
    fun `test transform with valid data`() {
        val intValueInTimeDto = IntValueInTimeDto(value = 20, time = 6)
        val dayTime = DayTime.Morning
        val expected = ValuesDayTimeBo(value = 20, time = dayTime)

        every { intDayTimeMapper.transform(6) } returns dayTime

        val result = dailyValuesInTimeMapper.transform(intValueInTimeDto)
        assertEquals(expected, result)
    }

}