package net.exotia.wallet.spigot.actions

import org.bukkit.entity.Player

interface Action {
    fun identifier(): String
    fun execute(player: Player, message: String)
}