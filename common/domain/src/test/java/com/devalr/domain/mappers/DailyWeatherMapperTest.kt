package com.devalr.domain.mappers

import com.devalr.data.dto.dailyweather.common.DataOriginsDto
import com.devalr.data.dto.dailyweather.daily.DailyDto
import com.devalr.data.dto.dailyweather.daily.DailyMaxMinValuesDto
import com.devalr.data.dto.dailyweather.daily.DailyPredictionDto
import com.devalr.data.dto.dailyweather.daily.DailySkyValueInTimeDto
import com.devalr.data.dto.dailyweather.daily.DailyValueInTimeIntDto
import com.devalr.data.dto.dailyweather.daily.DailyValueInTimeStrDto
import com.devalr.data.dto.dailyweather.daily.DailyWeatherDto
import com.devalr.data.dto.dailyweather.daily.DailyWindInTimeDto
import com.devalr.data.dto.dailyweather.daily.IntValueInTimeDto
import com.devalr.domain.mappers.daily.DailyWeatherMapper
import com.devalr.domain.model.enums.DayTime
import com.devalr.domain.model.enums.SkyState
import com.devalr.domain.model.enums.WindDirection
import com.devalr.domain.model.weather.daily.DailySkyState
import com.devalr.domain.model.weather.daily.DailyWeatherBo
import com.devalr.domain.model.weather.daily.DailyWeatherDataBo
import com.devalr.domain.model.weather.daily.DailyWindState
import com.devalr.domain.model.weather.daily.MaxMinValueBo
import com.devalr.domain.model.weather.daily.PrecipitationProbability
import com.devalr.domain.model.weather.daily.ValuesDayTimeBo
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.time.LocalDateTime

class DailyWeatherMapperTest {

    private lateinit var dailyWeatherMapper: DailyWeatherMapper
    private val dayMapper: Mapper<DailyDto, DailyWeatherBo> = mockk()

    @Before
    fun setUp() {
        dailyWeatherMapper = DailyWeatherMapper(dayMapper)
    }

    @Test
    fun `test transform with valid data`() {
        val dailyDto = DailyDto(
            rainProbability = listOf(DailyValueInTimeIntDto(10f, "6")),
            snowProbability = listOf(DailyValueInTimeStrDto("low", "6")),
            skyState = listOf(DailySkyValueInTimeDto("clear", "6", "clear")),
            wind = listOf(DailyWindInTimeDto("north", 10, "6")),
            windValue = listOf(DailyValueInTimeStrDto("strong", "6")),
            temperature = DailyMaxMinValuesDto(30, 15, listOf(IntValueInTimeDto(20, 6))),
            thermalSensation = DailyMaxMinValuesDto(32, 16, listOf(IntValueInTimeDto(22, 6))),
            relativeHumidity = DailyMaxMinValuesDto(80, 40, listOf(IntValueInTimeDto(60, 6))),
            date = "2023-10-10",
            uvMax = 5
        )

        val dailyWeatherBo = DailyWeatherBo(
            rainProbabilities = listOf(PrecipitationProbability(10f, DayTime.Morning)),
            snowProbabilities = listOf(PrecipitationProbability(10f, DayTime.Morning)),
            skyStates = listOf(DailySkyState(SkyState.DayClear, "", DayTime.Morning)),
            windState = listOf(DailyWindState(WindDirection.North, 10, DayTime.Morning)),
            temperatures = MaxMinValueBo(30, 15, listOf(ValuesDayTimeBo(20, DayTime.Morning))),
            thermalSensation = MaxMinValueBo(32, 16, listOf(ValuesDayTimeBo(22, DayTime.Morning))),
            humidity = MaxMinValueBo(80, 40, listOf(ValuesDayTimeBo(60, DayTime.Morning))),
            uvMax = 5,
            date = LocalDateTime.parse("2023-10-10T00:00:00")
        )

        val dailyWeatherDto = DailyWeatherDto(
            origen = DataOriginsDto(
                producer = "Weather Service",
                web = "https://weather.example.com",
                link = "https://weather.example.com/report",
                language = "en",
                copyright = "© 2023 Weather Service",
                legalDisclaimer = "All data provided as is without any warranty."
            ),
            made = "2023-10-10T00:00:00",
            name = "Weather Report",
            province = "Province Name",
            prediction = DailyPredictionDto(
                day = listOf(dailyDto)
            ),
            id = 1,
            version = 1.0f
        )

        every { dayMapper.transform(dailyDto) } returns dailyWeatherBo

        val expected = DailyWeatherDataBo(
            predictions = listOf(dailyWeatherBo)
        )

        val result = dailyWeatherMapper.transform(dailyWeatherDto)
        assertEquals(expected, result)
    }

    @Test
    fun `test transform with null data`() {
        val result = dailyWeatherMapper.transform(null)
        assertEquals(null, result)
    }
}