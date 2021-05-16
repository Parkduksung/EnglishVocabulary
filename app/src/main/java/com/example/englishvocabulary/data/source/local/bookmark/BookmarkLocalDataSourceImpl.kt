package com.example.englishvocabulary.data.source.local.bookmark

import com.example.englishvocabulary.network.room.dao.BookmarkDao
import com.example.englishvocabulary.network.room.entity.BookmarkEntity
import com.example.englishvocabulary.util.AppExecutors
import javax.inject.Inject

class BookmarkLocalDataSourceImpl @Inject constructor(private val bookmarkDao: BookmarkDao) :
    BookmarkLocalDataSource {
    override fun getAllList(callback: (getList: List<BookmarkEntity>) -> Unit) {

        val appExecutors = AppExecutors()

        appExecutors.diskIO.execute {
            val bookmarkList = bookmarkDao.getAll()

            appExecutors.mainThread.execute {
                callback(bookmarkList)
            }
        }
    }

    override fun addBookmark(
        bookmarkEntity: BookmarkEntity,
        callback: (isSuccess: Boolean) -> Unit
    ) {

        val appExecutors = AppExecutors()

        appExecutors.diskIO.execute {

            val addBookmark = bookmarkDao.addBookmark(bookmarkEntity)

            appExecutors.mainThread.execute {
                if (addBookmark >= 1) {
                    callback(true)
                } else {
                    callback(false)
                }
            }
        }
    }

    override fun deleteBookmark(
        bookmarkEntity: BookmarkEntity,
        callback: (isSuccess: Boolean) -> Unit
    ) {

        val appExecutors = AppExecutors()

        appExecutors.diskIO.execute {

            val deleteBookmark = bookmarkDao.deleteBookmark(bookmarkEntity.word)

            appExecutors.mainThread.execute {
                if (deleteBookmark >= 1) {
                    callback(true)
                } else {
                    callback(false)
                }
            }
        }
    }
}