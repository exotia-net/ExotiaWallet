package net.exotia.wallet.proxy.listeners

import net.exotia.wallet.proxy.ExotiaWallet
import net.exotia.wallet.proxy.utils.MessageUtil
import net.md_5.bungee.api.ProxyServer
import net.md_5.bungee.api.connection.ProxiedPlayer
import net.md_5.bungee.api.event.PluginMessageEvent
import net.md_5.bungee.api.plugin.Listener
import net.md_5.bungee.event.EventHandler
import java.io.ByteArrayInputStream
import java.io.DataInputStream
import java.io.EOFException
import java.util.function.Consumer

class PluginMessagingListener() : Listener {
    @EventHandler
    fun onPluginMessageReceived(event: PluginMessageEvent) {
        if (event.tag != ExotiaWallet.NOTIFICATION_CHANNEL) return
        val byteArrayInputStream = ByteArrayInputStream(event.data)
        val dataInputStream = DataInputStream(byteArrayInputStream)
        try {
            if (dataInputStream.available() <= 0) return
            val message: String = dataInputStream.readUTF()
            ProxyServer.getInstance().players.forEach(Consumer { proxiedPlayer: ProxiedPlayer ->
                MessageUtil.sendMessage(proxiedPlayer, message)
            })
        } catch (exception: EOFException) {
            exception.printStackTrace()
        }
    }
}
