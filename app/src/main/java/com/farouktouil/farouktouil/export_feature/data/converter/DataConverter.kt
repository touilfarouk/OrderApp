package com.farouktouil.farouktouil.export_feature.data.converter

import com.farouktouil.farouktouil.core.util.Resource
import com.farouktouil.farouktouil.export_feature.domain.model.ExportModel
import kotlinx.coroutines.flow.Flow

interface DataConverter {

    fun convertSensorData(
        exportDataList:List<ExportModel>
    ): Flow<Resource<GenerateInfo>>

}