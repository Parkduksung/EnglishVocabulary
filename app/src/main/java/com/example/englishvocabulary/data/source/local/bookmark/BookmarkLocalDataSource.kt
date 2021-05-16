package com.example.englishvocabulary.data.source.local.bookmark

import com.example.englishvocabulary.network.room.entity.BookmarkEntity

interface BookmarkLocalDataSource {
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