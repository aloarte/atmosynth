package com.devalr.domain.mappers

import com.devalr.domain.mappers.daily.WindDirectionMapper
import com.devalr.domain.model.enums.WindDirection
import org.junit.Assert.assertEquals
import org.junit.Test

class WindDirectionMapperTest {

    private val mapper = WindDirectionMapper()

    @Test
    fun `test transform North`() {
        assertEquals(WindDirection.North, mapper.transform("N"))
    }

    @Test
    fun `test transform NorthEast`() {
        assertEquals(WindDirection.NorthEast, mapper.transform("NE"))
    }

    @Test
    fun `test transform East`() {
        assertEquals(WindDirection.East, mapper.transform("E"))
    }

    @Test
    fun `test transform SouthEast`() {
        assertEquals(WindDirection.SouthEast, mapper.transform("SE"))
    }

    @Test
    fun `test transform South`() {
        assertEquals(WindDirection.South, mapper.transform("S"))
    }

    @Test
    fun `test transform SouthWest`() {
        assertEquals(WindDirection.SouthWest, mapper.transform("SO"))
    }

    @Test
    fun `test transform West`() {
        assertEquals(WindDirection.West, mapper.transform("O"))
    }

    @Test
    fun `test transform NorthWest`() {
        assertEquals(WindDirection.NorthWest, mapper.transform("NO"))
    }

    @Test
    fun `test transform unknown direction`() {
        assertEquals(WindDirection.None, mapper.transform("unknown"))
    }
}