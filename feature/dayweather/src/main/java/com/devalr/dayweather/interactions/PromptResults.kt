package com.devalr.dayweather.interactions

import com.devalr.dayweather.model.PromptStateVo

data class PromptResults(
    val promptWeatherSummary: PromptStateVo = PromptStateVo(),
    val promptPrecipitations: PromptStateVo = PromptStateVo(),
    val promptWind: PromptStateVo = PromptStateVo(),
    val promptHumidity: PromptStateVo = PromptStateVo(),
    val promptUv: PromptStateVo = PromptStateVo()
)
