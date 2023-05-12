package net.exotia.wallet.proxy.listeners

import net.exotia.wallet.proxy.ExotiaWallet
import net.exotia.wallet.proxy.utils.MessageUtil
import net.md_5.bungee.api.chat.TextComponent
import net.md_5.bungee.api.connection.ProxiedPlayer
import net.md_5.bungee.api.event.PluginMessageEvent
import net.md_5.bungee.api.plugin.Listener
import net.md_5.bungee.api.plugin.Plugin
import net.md_5.bungee.event.EventHandler
import java.io.ByteArrayInputStream
import java.io.DataInputStream
import java.io.IOException
import java.util.function.Consumer

class PluginMessagingListener(private val plugin: Plugin?) : Listener {
    @EventHandler
    fun onPluginMessageReceived(event: PluginMessageEvent?) {
        if (event?.tag != ExotiaWallet.NOTIFICATION_CHANNEL) return
        val byteArrayInputStream = ByteArrayInputStream(event?.data)
        val dataInputStream = DataInputStream(byteArrayInputStream)
        try {
            plugin?.proxy?.players?.forEach(Consumer {
                player: ProxiedPlayer -> MessageUtil.sendMessage(player, dataInputStream.readUTF()
                    .replace("{player}", player.name)
                )
            })
        } catch (exception: IOException) {
            exception.printStackTrace()
        }
    }
}
