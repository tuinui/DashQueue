package com.telecorp.dashqueue.utils.pref

import com.marcinmoskala.kotlinpreferences.PreferenceHolder

/**
 * Created by Saran on 1/11/2560.
 */
object MyPreferencesHolder : PreferenceHolder() {
    var savedPhoneNumber: String by bindToPreferenceField("")
    var rememberMe: Boolean by bindToPreferenceField(true)
}