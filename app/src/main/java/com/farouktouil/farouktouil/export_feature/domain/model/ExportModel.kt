package com.farouktouil.farouktouil.export_feature.domain.model

import kotlinx.coroutines.flow.Flow

data class ExportModel(
    val sensorData:Float,
    val time:Long
)