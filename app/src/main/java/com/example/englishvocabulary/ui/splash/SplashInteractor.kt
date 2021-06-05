package com.example.englishvocabulary.ui.splash

import com.example.englishvocabulary.data.repository.ExcelVocaRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class SplashInteractor @Inject constructor(private val excelVocaRepository: ExcelVocaRepository) {


    suspend fun checkExistExcelVoca(): Boolean = withContext(Dispatchers.IO){

//        var isExist = false
//
//
//
//        excelVocaRepository.getExcelData {
//            isExist = if (it.isNotEmpty()) {
//                verifyExcelData()
//            } else {
//                true
//            }
//        }
        return@withContext excelVocaRepository.checkExistExcelVoca()
    }

    private fun verifyExcelData(): Boolean {

        var checkVerify = false

        excelVocaRepository.verifyExcelData { isVerify: Boolean ->
            checkVerify = isVerify
        }

        return checkVerify
    }



}
