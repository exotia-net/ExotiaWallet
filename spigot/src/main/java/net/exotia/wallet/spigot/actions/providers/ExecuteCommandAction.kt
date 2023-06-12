package net.exotia.wallet.spigot.actions.providers

import net.exotia.wallet.spigot.actions.Action
import org.bukkit.Bukkit
import org.bukkit.entity.Player

class ExecuteCommandAction : Action {
    override fun identifier(): String {
        return "EXECUTE_COMMAND"
    }

    override fun execute(player: Player, message: String) {
        Bukkit.getServer().dispatchCommand(Bukkit.getServer().consoleSender, message.replace("{player}", player.name))
    }
}
