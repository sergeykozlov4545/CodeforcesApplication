package com.example.sergey.codeforcesapplication.model.pojo

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey

@Entity(tableName = "problem",
        primaryKeys = ["contest_id, index"])
data class Problem(
        @ForeignKey(entity = Contest::class, parentColumns = ["id"], childColumns = ["contest_id"])
        @ColumnInfo(name = "contest_id")
        val contestId: Long,

        val index: String,

        val name: String,

        val tags: List<String>
) {
    val tagsString = tags.joinToString()
}