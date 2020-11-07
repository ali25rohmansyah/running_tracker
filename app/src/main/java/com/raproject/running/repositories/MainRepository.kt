package com.raproject.running.repositories

import com.raproject.running.db.Run
import com.raproject.running.db.RunDAO
import javax.inject.Inject

class MainRepository @Inject constructor(
    val runDao: RunDAO
){
    suspend fun insertRun(run: Run) = runDao.insert(run)

    suspend fun deleteRun(run: Run) = runDao.delete(run)

    fun getAllRunSorted(run: String) = runDao.getAllRunsSorted(run)

    fun getTotalAvgSpeed() = runDao.getTotalAvgSpeed()

    fun getTotalDistanceInMeters() = runDao.getTotalDistanceInMeters()

    fun getTotalCaloriesBurned() = runDao.getTotalCaloriesBurned()

    fun getTotalTimeInMillis() = runDao.getTotalTimeInMillis()
}