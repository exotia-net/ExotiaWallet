package net.exotia.wallet.proxy

import net.exotia.wallet.proxy.listeners.PluginMessagingListener
import net.md_5.bungee.api.plugin.Plugin

class ExotiaWallet : Plugin() {
    override fun onEnable() {
        proxy.registerChannel(NOTIFICATION_CHANNEL)
        proxy.pluginManager.registerListener(this, PluginMessagingListener())
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }

    companion object {
        var NOTIFICATION_CHANNEL: String? = "wallet:notification"
    }
}
