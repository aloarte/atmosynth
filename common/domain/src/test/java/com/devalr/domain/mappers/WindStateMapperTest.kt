package com.devalr.domain.mappers

import com.devalr.data.dto.dailyweather.daily.DailyWindInTimeDto
import com.devalr.domain.mappers.daily.WindStateMapper
import com.devalr.domain.model.enums.DayTime
import com.devalr.domain.model.enums.WindDirection
import com.devalr.domain.model.weather.daily.DailyWindState
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class WindStateMapperTest {

    private val windDirectionMapper: Mapper<String, WindDirection> = mockk()
    private val stringDayTimeMapper: Mapper<String, DayTime> = mockk()
    private lateinit var windStateMapper: WindStateMapper

    @Before
    fun setUp() {
        windStateMapper = WindStateMapper(windDirectionMapper, stringDayTimeMapper)
    }

    @Test
    fun `test transform with valid data`() {
        val dailyWindInTimeDto = DailyWindInTimeDto(
            direction = "N",
            speed = 10,
            time = "06-12"
        )

        every { windDirectionMapper.transform("N") } returns WindDirection.North
        every { stringDayTimeMapper.transform("06-12") } returns DayTime.Morning

        val expected = DailyWindState(
            direction = WindDirection.North,
            speed = 10,
            time = DayTime.Morning
        )

        val result = windStateMapper.transform(dailyWindInTimeDto)
        assertEquals(expected, result)
    }

    @Test
    fun `test transform with null direction and time`() {
        val dailyWindInTimeDto = DailyWindInTimeDto(
            direction = null,
            speed = 5,
            time = null
        )

        every { windDirectionMapper.transform(any()) } returns WindDirection.None
        every { stringDayTimeMapper.transform(any()) } returns DayTime.Unknown

        val expected = DailyWindState(
            direction = WindDirection.None,
            speed = 5,
            time = DayTime.Unknown
        )

        val result = windStateMapper.transform(dailyWindInTimeDto)
        assertEquals(expected, result)
    }
}