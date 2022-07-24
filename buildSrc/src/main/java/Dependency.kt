import org.gradle.api.internal.tasks.testing.JULRedirector
import org.gradle.api.publish.internal.versionmapping.VersionMappingStrategyInternal

/**
 * @author Ha Jin Seok
 * @email seok270@gmail.com
 * @created 2022-05-03
 * @desc
 */



object AndroidX {
    const val APP_COMPAT = "androidx.appcompat:appcompat:${Versions.APP_COMPAT}"
    const val MATERIAL = "com.google.android.material:material:${Versions.MATERIAL}"
    const val CONSTRAINT_LAYOUT = "androidx.constraintlayout:constraintlayout:${Versions.CONSTRAINT_LAYOUT}"
    const val ACTIVITY_KTX = "androidx.activity:activity-ktx:${Versions.ACTIVITY_KTX}"
    const val DATASTORE = "androidx.datastore:datastore-preferences:${Versions.DATASTORE}"
    const val LIFECYCLE_VIEW_MODEL = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.LIFECYCLE_VIEW_MODEL}"
    const val LIFECYCLE_LIVEDATA = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.LIFECYCLE_LIVEDATA}"
    const val LEGACY = "androidx.legacy:legacy-support-v4:${Versions.LEGACY}"
}

object KTX {
    const val KTX_CORE = "androidx.core:core-ktx:${Versions.KTX_CORE}"
}

object TEST {
    const val JUNIT = "androidx.test.ext:junit:${Versions.JUNIT}"
    const val JUNIT_VERSION ="junit:junit:${Versions.JUNIT_VERSION}"
    const val ESPRESSO_CORE = "androidx.test.espresso:espresso-core:${Versions.ESPRESSO_CORE}"
}

object DaggerHilt {
    const val DAGGER_HILT = "com.google.dagger:hilt-android:${Versions.HILT_ANDROID}"
    const val DAGGER_HILT_COMPILER = "com.google.dagger:hilt-android-compiler:${Versions.HILT_ANDROID_COMPILER}"
    const val DAGGER_HILT_VIEW_MODEL = "androidx.hilt:hilt-lifecycle-viewmodel:${Versions.HILT_VIEWMODEL}"
    const val DAGGER_HILT_ANDROIDX_COMPILER = "androidx.hilt:hilt-compiler:${Versions.HILT_COMPILER}"
}

object ROOM {
    const val ROOM_RUNTIME = "androidx.room:room-runtime:${Versions.ROOM_DB}"
    const val ROOM_COMPILER = "androidx.room:room-compiler:${Versions.ROOM_DB}"
    const val ROOM_KTX = "androidx.room:room-ktx:${Versions.ROOM_DB}"
}

object Retrofit {
    const val RETROFIT = "com.squareup.retrofit2:retrofit:2.9.0"
    const val CONVERTER_GSON = "com.squareup.retrofit2:converter-gson:2.9.0"
    const val CONVERTER_JAXB = "com.squareup.retrofit2:converter-jaxb:2.9.0"
}

object Custom {
    const val colorPicker = "com.github.dhaval2404:colorpicker:2.0"
    const val circleImage = "de.hdodenhof:circleimageview:3.1.0"
}

object Firebase {
    const val firebase_Bom = "com.google.firebase:firebase-bom:30.0.1"
    const val firebase_Analytics = "com.google.firebase:firebase-analytics-ktx"
    const val firebase_Crashlytics = "com.google.firebase:firebase-crashlytics-ktx"
    const val firebase_Database = "com.google.firebase:firebase-database-ktx:20.0.3"
    const val firebase_FireStore = "com.google.firebase:firebase-firestore-ktx:24.0.1"
}

object Adb {
    const val ads = "com.google.android.gms:play-services-ads:20.6.0"
}