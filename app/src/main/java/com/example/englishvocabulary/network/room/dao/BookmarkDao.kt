package com.example.englishvocabulary.network.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.englishvocabulary.network.room.entity.BookmarkEntity

@Dao
interface BookmarkDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addBookmark(bookmarkEntity: BookmarkEntity): Long


    @Query("SELECT * FROM bookmark_table")
    fun getAll(): List<BookmarkEntity>


    @Query("DELETE FROM bookmark_table WHERE word = (:word)")
    fun deleteBookmark(word: String): Int

}