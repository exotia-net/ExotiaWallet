package net.exotia.wallet.spigot.factory

import eu.okaeri.configs.ConfigManager
import eu.okaeri.configs.OkaeriConfig
import eu.okaeri.configs.yaml.bukkit.YamlBukkitConfigurer
import eu.okaeri.configs.yaml.bukkit.serdes.SerdesBukkit
import java.io.File

class ConfigurationFactory(private val dataDirectory: File) {
    fun <T : OkaeriConfig?> produce(type: Class<T>, fileName: String): T {
        return this.produce(type, File(dataDirectory, fileName))
    }

    fun <T : OkaeriConfig?> produce(type: Class<T>, file: File): T {
        return ConfigManager.create(type) { okaeriConfig: OkaeriConfig ->
            okaeriConfig
                .withConfigurer(YamlBukkitConfigurer())
                .withBindFile(file)
                .withSerdesPack(SerdesBukkit())
                .saveDefaults()
                .load(true)
        }
    }
}
