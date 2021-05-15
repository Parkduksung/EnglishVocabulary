package com.example.englishvocabulary.ui.splash

import android.os.Bundle
import android.os.Handler
import android.util.Log
import com.example.englishvocabulary.App
import com.example.englishvocabulary.R
import com.example.englishvocabulary.base.BaseActivity
import com.example.englishvocabulary.databinding.ActivitySplashBinding
import com.example.englishvocabulary.ui.home.ExcelData
import com.example.englishvocabulary.ui.home.HomeActivity
import org.apache.poi.hssf.usermodel.HSSFWorkbook
import org.apache.poi.poifs.filesystem.POIFSFileSystem

class SplashActivity : BaseActivity<ActivitySplashBinding>(R.layout.activity_splash) {

    private val excelListDataList = mutableListOf<ExcelData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Handler().postDelayed({

            vocaList.forEach {
                readExcelFile(it)
            }

            excelListDataList.toList().forEach {
                Log.d("결과", it.mean)
            }
            startActivity(HomeActivity.getIntent(this))
            finish()
        }, SPLASH_DELAY_MILLIS)
    }


    private fun readExcelFile(file: String) {

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
    }

    companion object {
        private const val SPLASH_DELAY_MILLIS = 1500L

        private val vocaList = listOf(
            "voca1.xls", "voca2.xls", "voca3.xls", "voca4.xls", "voca5.xls", "voca6.xls"
        )

    }
}