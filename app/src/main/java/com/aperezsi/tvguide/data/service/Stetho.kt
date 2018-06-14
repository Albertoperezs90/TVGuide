package com.aperezsi.tvguide.data.service

import android.app.Application
import com.facebook.stetho.Stetho

class Stetho : Application() {
    override fun onCreate() {
        super.onCreate()
        // depura almacenamiento local BD, share, etc
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                        .build())
    }
}