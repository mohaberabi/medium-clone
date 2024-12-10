package com.mohaberabi.tatbeeq.core.data.network

import com.mohaberabi.tatbeeq.platform_module.AppSecrets
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.serializer.KotlinXSerializer
import io.github.jan.supabase.storage.Storage
import kotlinx.serialization.json.Json

object SupaBaseFactory {


    private const val SUBA_URL = "https://pmrvuafzdkujkxeyxhre.supabase.co"
    private val SUBA_KEY = AppSecrets.supabaseSecret

    fun create(): SupabaseClient {
        return createSupabaseClient(
            supabaseUrl = SUBA_URL,
            supabaseKey = SUBA_KEY
        ) {
            install(Postgrest)
            install(Storage)
            defaultSerializer = KotlinXSerializer(
                Json {
                    ignoreUnknownKeys = true
                },
            )
        }
    }
}