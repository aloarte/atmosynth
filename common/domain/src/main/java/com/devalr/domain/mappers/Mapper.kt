package com.devalr.domain.mappers

abstract class Mapper<IN, OUT> {
    abstract fun transform(data: IN): OUT
}
