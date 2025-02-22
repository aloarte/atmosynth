package com.devalr.dayweather.mappers

import com.devalr.dayweather.model.enums.WindDirectionText
import com.devalr.dayweather.model.now.WindState
import com.devalr.domain.mappers.Mapper
import com.devalr.domain.model.enums.WindDirection
import com.devalr.domain.model.weather.daily.DailyWindState

class WindEnumMapper : Mapper<DailyWindState?, WindState>() {

    override fun transform(data: DailyWindState?): WindState = data?.let {
        when (data.direction) {
            WindDirection.North -> WindState(WindDirectionText.N, data.speed)
            WindDirection.NorthEast -> WindState(WindDirectionText.NE, data.speed)
            WindDirection.East -> WindState(WindDirectionText.E, data.speed)
            WindDirection.SouthEast -> WindState(WindDirectionText.SE, data.speed)
            WindDirection.South -> WindState(WindDirectionText.S, data.speed)
            WindDirection.SouthWest -> WindState(WindDirectionText.SW, data.speed)
            WindDirection.West -> WindState(WindDirectionText.W, data.speed)
            WindDirection.NorthWest -> WindState(WindDirectionText.NW, data.speed)
            WindDirection.None -> WindState(WindDirectionText.None, data.speed)
        }
    } ?: WindState(WindDirectionText.None, 0)
}
