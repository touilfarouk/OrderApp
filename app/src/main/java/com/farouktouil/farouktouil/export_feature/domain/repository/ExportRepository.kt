package com.farouktouil.farouktouil.export_feature.domain.repository

import com.farouktouil.farouktouil.core.util.Resource
import com.farouktouil.farouktouil.export_feature.domain.model.ExportModel
import com.farouktouil.farouktouil.export_feature.domain.model.PathInfo
import kotlinx.coroutines.flow.Flow

interface ExportRepository {

    fun startExportData(
        exportList: List<ExportModel>
    ): Flow<Resource<PathInfo>>

}