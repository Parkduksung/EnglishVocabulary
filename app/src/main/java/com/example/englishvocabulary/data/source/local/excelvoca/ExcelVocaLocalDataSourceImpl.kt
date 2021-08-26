package com.example.englishvocabulary.data.source.local.excelvoca

import com.example.englishvocabulary.data.model.ExcelData
import com.example.englishvocabulary.network.api.SheetApi
import com.example.englishvocabulary.network.room.database.ExcelVocaDatabase
import com.example.englishvocabulary.network.room.entity.ExcelVocaEntity
import com.example.englishvocabulary.util.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.java.KoinJavaComponent.inject

class ExcelVocaLocalDataSourceImpl : ExcelVocaLocalDataSource {

    private val excelVocaDatabase by inject(ExcelVocaDatabase::class.java)

    private val sheetApi by inject(SheetApi::class.java)

    override suspend fun checkExistExcelVoca(): Boolean = withContext(Dispatchers.IO) {
        return@withContext excelVocaDatabase.excelVocaDao().getAll().isNotEmpty()
    }

    override suspend fun registerExcelVocaData(): Boolean = withContext(Dispatchers.IO) {
        return@withContext try {
            sheetApi.getSheetExcelData().execute().body()!!.forEachIndexed { index, excelData ->
                excelVocaDatabase.excelVocaDao().registerExcelVocaEntity(
                    ExcelVocaEntity(index, excelData.day, excelData.word, excelData.mean, false)
                )
            }
            checkExistExcelVoca()
        } catch (e: Exception) {
            false
        }
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
            Result.failure(Throwable("Error ToggleBookmark"))
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

}