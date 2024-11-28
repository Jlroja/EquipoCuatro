package com.example.miniproyecto1.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

// Asegúrate de usar el paquete correcto

@Module
@InstallIn(SingletonComponent::class) // Especifica que el módulo debe ser instalado en el componente Singleton
class AppModule {
    @Provides
    @Singleton // Esto indica que Context será proporcionado como un Singleton
    fun provideContext(application: Application): Context {
        return application.applicationContext // Retorna el ApplicationContext
    }
}