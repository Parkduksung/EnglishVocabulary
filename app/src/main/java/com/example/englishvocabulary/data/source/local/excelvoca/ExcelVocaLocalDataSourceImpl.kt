package com.example.englishvocabulary.data.source.local.excelvoca

import com.example.englishvocabulary.App
import com.example.englishvocabulary.data.model.ExcelData
import com.example.englishvocabulary.network.room.dao.ExcelVocaDao
import com.example.englishvocabulary.network.room.entity.ExcelVocaEntity
import com.example.englishvocabulary.util.AppExecutors
import org.apache.poi.hssf.usermodel.HSSFWorkbook
import org.apache.poi.poifs.filesystem.POIFSFileSystem
import javax.inject.Inject

class ExcelVocaLocalDataSourceImpl @Inject constructor(private val excelVocaDao: ExcelVocaDao) :
    ExcelVocaLocalDataSource {

    override fun toggleBookmarkExcelData(
        toggleBookmark: Boolean,
        item: ExcelData,
        callback: (isSuccess: Boolean) -> Unit
    ) {
        val appExecutors = AppExecutors()

        appExecutors.diskIO.execute {
            val updateExcelData = excelVocaDao.updateBookmarkExcelData(
                day = item.day,
                word = item.word,
                mean = item.mean,
                toggleBookmark
            )

            appExecutors.mainThread.execute {
                if (updateExcelData == 1) {
                    callback(true)
                } else {
                    callback(false)
                }
            }
        }
    }

    override fun getAllBookmarkExcelData(callback: (excelList: List<ExcelVocaEntity>) -> Unit) {
        val appExecutors = AppExecutors()

        appExecutors.diskIO.execute {

            val getAllBookmarkExcelData = excelVocaDao.getBookmarkExcelVocaEntity(true)

            appExecutors.mainThread.execute {
                callback(getAllBookmarkExcelData)
            }
        }

    }

    override fun getExcelData(callback: (excelList: List<ExcelVocaEntity>) -> Unit) {

        val appExecutors = AppExecutors()

        appExecutors.diskIO.execute {

            val getAllExcelVocaEntity = getAllExcelVocaEntity()

            appExecutors.mainThread.execute {
                callback(getAllExcelVocaEntity)
            }
        }
    }

    override fun verifyExcelData(callback: (isVerify: Boolean) -> Unit) {

        val appExecutors = AppExecutors()

        appExecutors.diskIO.execute {

            if (getAllExcelVocaEntity().isEmpty()) {
                registerAllReadExcelFileData()
            }
            appExecutors.mainThread.execute {
                callback(true)
            }
        }
    }

    override fun getWantDayExcelData(
        day: String,
        callback: (excelList: List<ExcelVocaEntity>) -> Unit
    ) {

        val appExecutors = AppExecutors()

        appExecutors.diskIO.execute {
            val getWantDayExcelVocaEntity = excelVocaDao.getDayExcelVocaEntity(wantDay = day)

            appExecutors.mainThread.execute {
                callback(getWantDayExcelVocaEntity)
            }
        }

    }

    private fun registerAllReadExcelFileData() {
        getAllReadExcelFileData().forEach { excelData ->
            excelVocaDao.registerExcelVocaEntity(excelData.toExcelVocaEntity())
        }
    }

    private fun getAllExcelVocaEntity(): List<ExcelVocaEntity> {
        return excelVocaDao.getAll()
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