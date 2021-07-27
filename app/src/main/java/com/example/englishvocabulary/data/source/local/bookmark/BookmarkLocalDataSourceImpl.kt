package com.example.englishvocabulary.data.source.local.bookmark

import com.example.englishvocabulary.network.room.database.BookmarkDatabase
import com.example.englishvocabulary.network.room.entity.BookmarkEntity
import com.example.englishvocabulary.util.AppExecutors
import org.koin.java.KoinJavaComponent.inject

class BookmarkLocalDataSourceImpl : BookmarkLocalDataSource {

    private val bookmarkDatabase by inject(BookmarkDatabase::class.java)

    override fun getAllList(callback: (getList: List<BookmarkEntity>) -> Unit) {

        val appExecutors = AppExecutors()

        appExecutors.diskIO.execute {
            val bookmarkList = bookmarkDatabase.bookmarkDao().getAll()

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

            val addBookmark = bookmarkDatabase.bookmarkDao().addBookmark(bookmarkEntity)

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

            val deleteBookmark = bookmarkDatabase.bookmarkDao().deleteBookmark(bookmarkEntity.word)

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