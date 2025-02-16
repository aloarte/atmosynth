package com.devalr.domain.mappers.daily

import com.devalr.domain.mappers.Mapper
import com.devalr.domain.model.enums.WindDirection

class WindDirectionMapper : Mapper<String, WindDirection>() {
    override fun transform(data: String): WindDirection = when (data) {
        "N" -> WindDirection.North
        "NE" -> WindDirection.NorthEast
        "E" -> WindDirection.East
        "SE" -> WindDirection.SouthEast
        "S" -> WindDirection.South
        "SO" -> WindDirection.SouthWest
        "O" -> WindDirection.West
        "NO" -> WindDirection.NorthWest
        else -> WindDirection.None
    }
}
