package com.devalr.domain.mappers

import android.text.TextUtils
import com.devalr.data.dto.dailyweather.daily.DailyValueInTimeStrDto
import com.devalr.domain.mappers.daily.SnowPrecipitationMapper
import com.devalr.domain.model.enums.DayTime
import com.devalr.domain.model.weather.daily.PrecipitationProbability
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class SnowPrecipitationMapperTest {

    private lateinit var snowPrecipitationMapper: SnowPrecipitationMapper
    private val stringDayTimeMapper: Mapper<String, DayTime> = mockk()

    @Before
    fun setUp() {
        mockkStatic(TextUtils::class)
        every { TextUtils.isDigitsOnly(any()) } returns true

        snowPrecipitationMapper = SnowPrecipitationMapper(stringDayTimeMapper)
    }

    @Test
    fun `test transform with valid data`() {
        val dailyValueInTimeStrDto = DailyValueInTimeStrDto(
            value = "50",
            time = "06-12"
        )

        every { stringDayTimeMapper.transform("06-12") } returns DayTime.Morning

        val expected = PrecipitationProbability(
            probability = 50f,
            time = DayTime.Morning
        )

        val result = snowPrecipitationMapper.transform(dailyValueInTimeStrDto)
        assertEquals(expected, result)
    }

    @Test
    fun `test transform with unknown time`() {
        val dailyValueInTimeStrDto = DailyValueInTimeStrDto(
            value = "30",
            time = "unknown"
        )

        every { stringDayTimeMapper.transform("unknown") } returns DayTime.Unknown

        val expected = PrecipitationProbability(
            probability = 30f,
            time = DayTime.Unknown
        )

        val result = snowPrecipitationMapper.transform(dailyValueInTimeStrDto)
        assertEquals(expected, result)
    }
}