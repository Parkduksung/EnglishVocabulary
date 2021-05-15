package com.example.englishvocabulary.data.source.local

import com.example.englishvocabulary.App
import com.example.englishvocabulary.data.model.ExcelData
import org.apache.poi.hssf.usermodel.HSSFWorkbook
import org.apache.poi.poifs.filesystem.POIFSFileSystem
import javax.inject.Inject

class SplashLocalDataSourceImpl @Inject constructor() : SplashLocalDataSource {

    override fun getExcelData(callback: (excelList: List<ExcelData>) -> Unit) {

        val excelDataList = mutableListOf<ExcelData>().apply {
            vocaList.forEach {
                addAll(readExcelFile(it))
            }
        }
        callback(excelDataList)
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