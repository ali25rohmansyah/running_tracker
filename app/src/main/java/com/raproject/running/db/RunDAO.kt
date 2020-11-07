package com.raproject.running.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface RunDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(run: Run)

    @Delete
    suspend fun delete(run: Run)

    @Query("""
        SELECT * FROM running_table 
        ORDER BY 
        CASE WHEN :column = 'timestamp' THEN timestamp END DESC,
        CASE WHEN :column = 'timeInMillis' THEN timeInMillis END DESC,
        CASE WHEN :column = 'caloriesBurned' THEN caloriesBurned END DESC,
        CASE WHEN :column = 'avgSpeed' THEN avgSpeedInKMH END DESC,
        CASE WHEN :column = 'distanceInMeters' THEN distanceInMeters END DESC
    """)
    fun getAllRunsSorted(column: String): LiveData<List<Run>>

    @Query("SELECT SUM(timeInMillis) FROM running_table")
    fun getTotalTimeInMillis(): LiveData<Long>

    @Query("SELECT SUM(caloriesBurned) FROM running_table")
    fun getTotalCaloriesBurned(): LiveData<Int>

    @Query("SELECT SUM(distanceInMeters) FROM running_table")
    fun getTotalDistanceInMeters(): LiveData<Int>

    @Query("SELECT AVG(avgSpeedInKMH) FROM running_table")
    fun getTotalAvgSpeed(): LiveData<Float>
}