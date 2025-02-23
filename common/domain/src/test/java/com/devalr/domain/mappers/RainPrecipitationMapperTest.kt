package com.devalr.domain.mappers

import com.devalr.data.dto.dailyweather.daily.DailyValueInTimeIntDto
import com.devalr.domain.mappers.daily.RainPrecipitationMapper
import com.devalr.domain.model.enums.DayTime
import com.devalr.domain.model.weather.daily.PrecipitationProbability
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class RainPrecipitationMapperTest {

    private lateinit var rainPrecipitationMapper: RainPrecipitationMapper
    private val dayTimeMapper: Mapper<String, DayTime> = mockk()

    @Before
    fun setUp() {
        rainPrecipitationMapper = RainPrecipitationMapper(dayTimeMapper)
    }

    @Test
    fun `test transform with valid data`() {
        val dailyValueInTimeIntDto = DailyValueInTimeIntDto(
            value = 70f,
            time = "12-18"
        )

        every { dayTimeMapper.transform("12-18") } returns DayTime.Afternoon

        val expected = PrecipitationProbability(
            probability = 70f,
            time = DayTime.Afternoon
        )

        val result = rainPrecipitationMapper.transform(dailyValueInTimeIntDto)
        assertEquals(expected, result)
    }

    @Test
    fun `test transform with unknown time`() {
        val dailyValueInTimeIntDto = DailyValueInTimeIntDto(
            value = 40f,
            time = "unknown"
        )

        every { dayTimeMapper.transform("unknown") } returns DayTime.Unknown

        val expected = PrecipitationProbability(
            probability = 40f,
            time = DayTime.Unknown
        )

        val result = rainPrecipitationMapper.transform(dailyValueInTimeIntDto)
        assertEquals(expected, result)
    }
}