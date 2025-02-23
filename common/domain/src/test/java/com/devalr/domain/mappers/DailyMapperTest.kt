package com.devalr.domain.mappers

import com.devalr.data.dto.dailyweather.daily.*
import com.devalr.domain.mappers.Mapper
import com.devalr.domain.mappers.daily.DailyMapper
import com.devalr.domain.model.enums.DayTime
import com.devalr.domain.model.enums.SkyState
import com.devalr.domain.model.enums.WindDirection
import com.devalr.domain.model.weather.daily.*
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.time.LocalDateTime

class DailyMapperTest {

    private lateinit var dailyMapper: DailyMapper
    private val precipitationRainMapper: Mapper<DailyValueInTimeIntDto, PrecipitationProbability> = mockk()
    private val precipitationSnowMapper: Mapper<DailyValueInTimeStrDto, PrecipitationProbability> = mockk()
    private val windStateMapper: Mapper<DailyWindInTimeDto, DailyWindState> = mockk()
    private val skyStateMapper: Mapper<DailySkyValueInTimeDto, DailySkyState> = mockk()
    private val minMaxValueMapper: Mapper<DailyMaxMinValuesDto, MaxMinValueBo> = mockk()

    @Before
    fun setUp() {
        dailyMapper = DailyMapper(
            precipitationRainMapper,
            precipitationSnowMapper,
            windStateMapper,
            skyStateMapper,
            minMaxValueMapper
        )
    }

    @Test
    fun `test transform with valid data`() {
        val dailyDto = DailyDto(
            skyState = listOf(DailySkyValueInTimeDto("clear", "clear sky", "6")),
            rainProbability = listOf(DailyValueInTimeIntDto(20f, "6")),
            snowProbability = listOf(DailyValueInTimeStrDto("low", "6")),
            wind = listOf(DailyWindInTimeDto("N", 10, "6")),
            relativeHumidity = DailyMaxMinValuesDto(80, 40, listOf(IntValueInTimeDto(60, 6))),
            temperature = DailyMaxMinValuesDto(30, 15, listOf(IntValueInTimeDto(20, 6))),
            thermalSensation = DailyMaxMinValuesDto(32, 16, listOf(IntValueInTimeDto(22, 6))),
            date = "2023-10-10T00:00:00",
            windValue = listOf(DailyValueInTimeStrDto(value = "30", time = "6")),
            uvMax = 5
        )

        val expected = DailyWeatherBo(
            skyStates = listOf(DailySkyState(SkyState.DayClear, "clear sky", DayTime.Morning)),
            rainProbabilities = listOf(PrecipitationProbability(20f, DayTime.Morning)),
            snowProbabilities = listOf(PrecipitationProbability(10f, DayTime.Morning)),
            windState = listOf(DailyWindState(WindDirection.North, 10, DayTime.Morning)),
            humidity = MaxMinValueBo(80, 40, listOf(ValuesDayTimeBo(60, DayTime.Morning))),
            temperatures = MaxMinValueBo(30, 15, listOf(ValuesDayTimeBo(20, DayTime.Morning))),
            thermalSensation = MaxMinValueBo(30, 15, listOf(ValuesDayTimeBo(20, DayTime.Morning))),
            date = LocalDateTime.parse("2023-10-10T00:00:00"),
            uvMax = 5
        )

        every { skyStateMapper.transform(any()) } returns DailySkyState(SkyState.DayClear, "clear sky", DayTime.Morning)
        every { precipitationRainMapper.transform(any()) } returns PrecipitationProbability(20f, DayTime.Morning)
        every { precipitationSnowMapper.transform(any()) } returns PrecipitationProbability(10f, DayTime.Morning)
        every { windStateMapper.transform(any()) } returns DailyWindState(WindDirection.North, 10, DayTime.Morning)
        every { minMaxValueMapper.transform(any()) } answers {
            val arg = it.invocation.args[0] as DailyMaxMinValuesDto
            when (arg) {
                dailyDto.temperature -> MaxMinValueBo(30, 15, listOf(ValuesDayTimeBo(20, DayTime.Morning)))
                dailyDto.relativeHumidity -> MaxMinValueBo(80, 40, listOf(ValuesDayTimeBo(60, DayTime.Morning)))
                dailyDto.thermalSensation -> MaxMinValueBo(30, 15, listOf(ValuesDayTimeBo(20, DayTime.Morning)))
                else -> throw IllegalArgumentException("Unexpected argument")
            }
        }
        val result = dailyMapper.transform(dailyDto)
        assertEquals(expected, result)
    }
}