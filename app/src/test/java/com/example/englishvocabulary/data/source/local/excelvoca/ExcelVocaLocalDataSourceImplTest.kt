package com.example.englishvocabulary.data.source.local.excelvoca

import base.BaseTest
import com.example.englishvocabulary.network.room.database.ExcelVocaDatabase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.core.module.Module
import org.koin.dsl.module
import org.mockito.Mock

class ExcelVocaLocalDataSourceImplTest : BaseTest() {

    @Mock
    lateinit var excelVocaDatabase: ExcelVocaDatabase

    private lateinit var excelVocaLocalDataSourceImpl: ExcelVocaLocalDataSource

    override fun createModules(): List<Module> {
        return listOf(
            module {
                single { excelVocaDatabase }
            }
        )
    }

    @ExperimentalCoroutinesApi
    override fun setup() {
        super.setup()
        excelVocaLocalDataSourceImpl = ExcelVocaLocalDataSourceImpl()
    }

}
