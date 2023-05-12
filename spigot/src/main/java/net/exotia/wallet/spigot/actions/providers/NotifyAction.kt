package net.exotia.wallet.spigot.actions.providers

import net.exotia.wallet.spigot.ExotiaWallet
import net.exotia.wallet.spigot.actions.Action
import org.bukkit.Bukkit
import org.bukkit.Server
import org.bukkit.entity.Player
import org.bukkit.plugin.Plugin
import java.io.ByteArrayOutputStream
import java.io.DataOutputStream
import java.io.IOException

class NotifyAction(private val plugin: Plugin) : Action {
    override fun identifier(): String {
        return "NOTIFY"
    }

    override fun execute(player: Player, message: String) {
        // Tutaj ma byc notify do bungee
//        Bukkit.getOnlinePlayers().forEach { player -> player.sendMessage(message) }
        val byteArrayOutputStream = ByteArrayOutputStream()
        val dataOutputStream = DataOutputStream(byteArrayOutputStream)

        try {
            dataOutputStream.writeUTF(message)
            this.plugin.server.sendPluginMessage(this.plugin, ExotiaWallet.NOTIFICATION_CHANNEL, byteArrayOutputStream.toByteArray())
        } catch (exception: IOException) {
            exception.printStackTrace()
        }
    }
}
