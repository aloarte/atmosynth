package com.devalr.domain.mappers

import com.devalr.data.dto.dailyweather.daily.DailyMaxMinValuesDto
import com.devalr.data.dto.dailyweather.daily.IntValueInTimeDto
import com.devalr.domain.mappers.daily.MinMaxValueMapper
import com.devalr.domain.model.enums.DayTime
import com.devalr.domain.model.weather.daily.MaxMinValueBo
import com.devalr.domain.model.weather.daily.ValuesDayTimeBo
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class MinMaxValueMapperTest {

    private lateinit var minMaxValueMapper: MinMaxValueMapper
    private val dailyValuesInTimeMapper: Mapper<IntValueInTimeDto, ValuesDayTimeBo> = mockk()

    @Before
    fun setUp() {
        minMaxValueMapper = MinMaxValueMapper(dailyValuesInTimeMapper)
    }

    @Test
    fun `test transform with valid data`() {
        val intValueInTimeDto = IntValueInTimeDto(value = 10, time = 6)
        val valuesDayTimeBo = ValuesDayTimeBo(value = 10, time = DayTime.Morning)

        val dailyMaxMinValuesDto = DailyMaxMinValuesDto(
            max = 20,
            min = 5,
            values = listOf(intValueInTimeDto)
        )

        every { dailyValuesInTimeMapper.transform(intValueInTimeDto) } returns valuesDayTimeBo

        val expected = MaxMinValueBo(
            max = 20,
            min = 5,
            valuesPerDayTime = listOf(valuesDayTimeBo)
        )

        val result = minMaxValueMapper.transform(dailyMaxMinValuesDto)
        assertEquals(expected, result)
    }
}