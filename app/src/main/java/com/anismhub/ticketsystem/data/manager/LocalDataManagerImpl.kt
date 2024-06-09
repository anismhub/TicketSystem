package com.anismhub.ticketsystem.data.manager

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.anismhub.ticketsystem.domain.manager.LocalDataManager
import com.anismhub.ticketsystem.domain.model.LoginData
import com.anismhub.ticketsystem.domain.model.ProfileData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.datastore: DataStore<Preferences> by preferencesDataStore(name = "TicketSystem")

class LocalDataManagerImpl(context: Context) : LocalDataManager {

    private val dataStore = context.datastore

    override suspend fun saveLoginData(loginData: LoginData) {
        dataStore.edit { pref ->
            pref[userIdKey] = loginData.userId
            pref[userNameKey] = loginData.userName
            pref[userFullNameKey] = loginData.userFullName
            pref[userRoleKey] = loginData.userRole
            pref[accessTokenKey] = loginData.accessToken
            pref[loginStateKey] = true
        }
    }

    override fun getLoginData(): Flow<LoginData> {
        return dataStore.data.map { pref ->
            LoginData(
                userId = pref[userIdKey] ?: 0,
                userName = pref[userNameKey] ?: "",
                userFullName = pref[userFullNameKey] ?: "",
                userRole = pref[userRoleKey] ?: "",
                accessToken = pref[accessTokenKey] ?: ""
            )
        }
    }

    override fun getLoginState(): Flow<Boolean> {
        return dataStore.data.map { pref ->
            pref[loginStateKey] ?: false
        }
    }

    override fun getAccessToken(): Flow<String> {
        return dataStore.data.map { pref ->
            pref[accessTokenKey] ?: ""
        }
    }

    override suspend fun clearLoginData() {
        dataStore.edit { pref ->
            pref.remove(userIdKey)
            pref.remove(userNameKey)
            pref.remove(userFullNameKey)
            pref.remove(userRoleKey)
            pref.remove(accessTokenKey)
            pref.remove(loginStateKey)
            pref.remove(departmentIdKey)
            pref.remove(departmentNameKey)
            pref.remove(userPhoneKey)
        }
    }

    override fun getProfileData(): Flow<ProfileData> {
        return dataStore.data.map { pref ->
            ProfileData(
                userId = pref[userIdKey] ?: 0,
                userName = pref[userNameKey] ?: "",
                userFullName = pref[userFullNameKey] ?: "",
                userRole = pref[userRoleKey] ?: "",
                departmentId = pref[departmentIdKey] ?: 0,
                departmentName = pref[departmentNameKey] ?: "",
                userPhone = pref[userPhoneKey] ?: "08xx"
            )
        }
    }

    override suspend fun saveProfileData(profileData: ProfileData) {
        dataStore.edit { pref ->
            pref[userIdKey] = profileData.userId
            pref[userNameKey] = profileData.userName
            pref[userFullNameKey] = profileData.userFullName
            pref[userRoleKey] = profileData.userRole
            pref[departmentIdKey] = profileData.departmentId
            pref[departmentNameKey] = profileData.departmentName
            pref[userPhoneKey] = profileData.userPhone
        }
    }

    private companion object {
        val userIdKey = intPreferencesKey("user_id_key")
        val userNameKey = stringPreferencesKey("user_name_key")
        val userFullNameKey = stringPreferencesKey("user_fullname_key")
        val userRoleKey = stringPreferencesKey("user_role_key")
        val accessTokenKey = stringPreferencesKey("access_token_key")
        val loginStateKey = booleanPreferencesKey("login_state_key")
        val departmentIdKey = intPreferencesKey("department_id_key")
        val departmentNameKey = stringPreferencesKey("department_name_key")
        val userPhoneKey = stringPreferencesKey("user_phone_key")
    }
}