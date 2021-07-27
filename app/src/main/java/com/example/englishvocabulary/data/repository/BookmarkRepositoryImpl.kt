package com.example.englishvocabulary.data.repository

import com.example.englishvocabulary.data.source.local.bookmark.BookmarkLocalDataSource
import com.example.englishvocabulary.network.room.entity.BookmarkEntity
import org.koin.java.KoinJavaComponent.inject

class BookmarkRepositoryImpl : BookmarkRepository {

    private val bookmarkLocalDataSource by inject(BookmarkLocalDataSource::class.java)

    override fun getAllList(callback: (getList: List<BookmarkEntity>) -> Unit) {
        bookmarkLocalDataSource.getAllList(callback)
    }

    override fun addBookmark(
        bookmarkEntity: BookmarkEntity,
        callback: (isSuccess: Boolean) -> Unit
    ) {
        bookmarkLocalDataSource.addBookmark(bookmarkEntity, callback)
    }

    override fun deleteBookmark(
        bookmarkEntity: BookmarkEntity,
        callback: (isSuccess: Boolean) -> Unit
    ) {
        bookmarkLocalDataSource.deleteBookmark(bookmarkEntity, callback)
    }
}