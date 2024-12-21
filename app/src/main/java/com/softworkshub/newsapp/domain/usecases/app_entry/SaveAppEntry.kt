package com.softworkshub.newsapp.domain.usecases.app_entry

import com.softworkshub.newsapp.domain.manager.LocalUserManager

class SaveAppEntry(
    private val localUserManager:LocalUserManager
) {
    suspend operator fun invoke(){
         localUserManager.saveAppEntry()
    }
}