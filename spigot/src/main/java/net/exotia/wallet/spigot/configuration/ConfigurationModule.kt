package net.exotia.wallet.spigot.configuration

import eu.okaeri.injector.Injector
import eu.okaeri.injector.annotation.Inject
import eu.okaeri.injector.annotation.PostConstruct
import net.exotia.wallet.spigot.configuration.files.MessagesConfiguration
import net.exotia.wallet.spigot.configuration.files.PluginConfiguration
import net.exotia.wallet.spigot.factory.ConfigurationFactory
import org.bukkit.plugin.Plugin

class ConfigurationModule {
    @Inject private val plugin: Plugin? = null;
    @Inject private val injector: Injector? = null;

    @PostConstruct
    fun onConstruct() {
        val configurationFactory = ConfigurationFactory(this.plugin!!.dataFolder);
        this.injector!!.registerInjectable(configurationFactory.produce(PluginConfiguration::class.java, "configuration.yml"))
        this.injector!!.registerInjectable(configurationFactory.produce(MessagesConfiguration::class.java, "messages.yml"))

        this.injector!!.registerInjectable(configurationFactory)
    }
}
