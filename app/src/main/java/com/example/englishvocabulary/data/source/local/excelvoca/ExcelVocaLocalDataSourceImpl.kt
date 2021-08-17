package com.example.englishvocabulary.data.source.local.excelvoca

import com.example.englishvocabulary.App
import com.example.englishvocabulary.data.model.ExcelData
import com.example.englishvocabulary.network.room.database.ExcelVocaDatabase
import com.example.englishvocabulary.network.room.entity.ExcelVocaEntity
import com.example.englishvocabulary.util.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.apache.poi.hssf.usermodel.HSSFWorkbook
import org.apache.poi.poifs.filesystem.POIFSFileSystem
import org.koin.java.KoinJavaComponent.inject

class ExcelVocaLocalDataSourceImpl : ExcelVocaLocalDataSource {

    private val excelVocaDatabase by inject(ExcelVocaDatabase::class.java)

    override suspend fun checkExistExcelVoca(): Boolean = withContext(Dispatchers.IO) {
        return@withContext excelVocaDatabase.excelVocaDao().getAll().isNotEmpty()
    }

    override suspend fun registerExcelVocaData(): Boolean = withContext(Dispatchers.IO) {
        registerAllReadExcelFileData()
        return@withContext excelVocaDatabase.excelVocaDao().getAll().isNotEmpty()
    }

    override suspend fun getWantDayExcelVocaData(wantDay: String): Result<List<ExcelVocaEntity>> =
        withContext(Dispatchers.IO) {
            val getWantDayExcelVocaEntityList =
                excelVocaDatabase.excelVocaDao().getDayExcelVocaEntity(wantDay)

            return@withContext if (!getWantDayExcelVocaEntityList.isNullOrEmpty()) {
                Result.success(getWantDayExcelVocaEntityList)
            } else {
                Result.failure(Throwable("Specific Excel Voca is Empty Or Null"))
            }
        }

    override suspend fun getAllBookmarkList(): Result<List<ExcelVocaEntity>> =
        withContext(Dispatchers.IO) {
            return@withContext try {
                val getAllBookmarkList =
                    excelVocaDatabase.excelVocaDao().getBookmarkExcelVocaEntity(true)
                Result.success(getAllBookmarkList)
            } catch (e: Exception) {
                Result.failure(Throwable("bookmarkList is Null!"))
            }
        }

    override suspend fun toggleBookmarkExcelData(
        item: ExcelData
    ): Result<ExcelVocaEntity> = withContext(Dispatchers.IO) {

        val updateExcelData = excelVocaDatabase.excelVocaDao().updateBookmarkExcelData(
            day = item.day,
            word = item.word,
            mean = item.mean,
            like = !item.like
        )

        return@withContext if (updateExcelData == 1) {
            val updateVocaEntity = item.toExcelVocaEntity().copy(like = !item.like)
            Result.success(updateVocaEntity)
        } else {
            Result.failure(Throwable())
        }
    }

    override suspend fun getAllExcelData(): Result<List<ExcelVocaEntity>> =
        withContext(Dispatchers.IO) {
            return@withContext try {
                Result.success(excelVocaDatabase.excelVocaDao().getAll())
            } catch (e: Exception) {
                Result.failure(Throwable("Error GetAllExcelEntity"))
            }
        }

    private fun registerAllReadExcelFileData() {
        getAllReadExcelFileData().forEach { excelData ->
            excelVocaDatabase.excelVocaDao().registerExcelVocaEntity(excelData.toExcelVocaEntity())
        }
    }

    private fun getAllExcelVocaEntity(): List<ExcelVocaEntity> {
        return excelVocaDatabase.excelVocaDao().getAll()
    }

    private fun getAllReadExcelFileData(): List<ExcelData> {
        return mutableListOf<ExcelData>().apply {
            vocaList.forEach {
                addAll(readExcelFile(it))
            }
        }
    }


    private fun readExcelFile(file: String): List<ExcelData> {

        val excelListDataList = mutableListOf<ExcelData>()

        val assertManager =
            App.instance.context().assets
        val inputStream =
            assertManager.open(file)

        val workFileSystem = POIFSFileSystem(inputStream)
        val workBookExcel = HSSFWorkbook(workFileSystem)

        val sheetExcel = workBookExcel.getSheetAt(0)
        val sheetRows = sheetExcel.iterator()
        while (sheetRows.hasNext()) {
            val currentRow = sheetRows.next()
            if (currentRow.rowNum != 0) {
                val cellsInRow = currentRow.iterator()
                var excelData = ExcelData()
                while (cellsInRow.hasNext()) {
                    val currentCell = cellsInRow.next()
                    if (currentCell.columnIndex != 0) {
                        when (currentCell.columnIndex) {
                            1 -> excelData.word = currentCell.stringCellValue
                            2 -> excelData.mean = currentCell.stringCellValue
                            3 -> excelData.day = currentCell.stringCellValue
                        }
                    }
                }
                excelListDataList.add(excelData)
            }
        }
        return excelListDataList
    }

    companion object {
        private val vocaList = listOf(
            "voca1.xls", "voca2.xls", "voca3.xls", "voca4.xls", "voca5.xls", "voca6.xls"
        )
    }
}