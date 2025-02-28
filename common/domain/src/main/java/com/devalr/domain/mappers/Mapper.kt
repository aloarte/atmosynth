package com.devalr.domain.mappers

abstract class Mapper<IN, OUT> {
    abstract fun transform(data: IN): OUT
    open fun transformReverse(data: OUT): IN {
        throw UnsupportedOperationException("This transformation is not implemented")
    }
}
