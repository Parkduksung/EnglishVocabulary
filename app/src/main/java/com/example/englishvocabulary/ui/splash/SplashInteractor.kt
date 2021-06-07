package com.example.englishvocabulary.ui.splash

import com.example.englishvocabulary.data.repository.ExcelVocaRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class SplashInteractor @Inject constructor(private val excelVocaRepository: ExcelVocaRepository) {


    suspend fun checkExistExcelVoca(): Boolean = withContext(Dispatchers.IO){

        if(excelVocaRepository.checkExistExcelVoca()){
            return@withContext true
        }else{
            return@withContext verifyExcelData()
        }
    }

    private fun verifyExcelData(): Boolean {

        var checkVerify = false

        excelVocaRepository.verifyExcelData { isVerify: Boolean ->
            checkVerify = isVerify
        }

        return checkVerify
    }


}
