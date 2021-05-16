package com.example.englishvocabulary.data.repository

import com.example.englishvocabulary.network.room.entity.BookmarkEntity

interface BookmarkRepository {
    fun getAllList(
        callback: (getList: List<BookmarkEntity>) -> Unit
    )

    fun addBookmark(
        bookmarkEntity: BookmarkEntity,
        callback: (isSuccess: Boolean) -> Unit
    )

    fun deleteBookmark(
        bookmarkEntity: BookmarkEntity,
        callback: (isSuccess: Boolean) -> Unit
    )
}