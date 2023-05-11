package net.exotia.wallet.spigot.actions.providers

import net.exotia.wallet.spigot.actions.Action
import org.bukkit.Bukkit
import org.bukkit.entity.Player

class NotifyAction : Action {
    override fun identifier(): String {
        return "NOTIFY"
    }

    override fun execute(player: Player, message: String) {
        // Tutaj ma byc notify do bungee
        Bukkit.getOnlinePlayers().forEach { player -> player.sendMessage(message) }
    }
}
