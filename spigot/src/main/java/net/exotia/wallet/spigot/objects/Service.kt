package net.exotia.wallet.spigot.objects

import eu.okaeri.configs.OkaeriConfig
import net.exotia.wallet.spigot.actions.ActionsService
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

class Service(
    private val _name: String,
    private val _cost: Double,
    private val _icon: ItemStack,
    private val _actions: List<String>
) : OkaeriConfig() {
    val name: String
        get() = _name
    val cost: Double
        get() = _cost
    val icon: ItemStack
        get() = _icon
    val actions: List<String>
        get() = _actions

    fun execute(actionsService: ActionsService, player: Player) {
        actionsService.parse(this._actions, player)
    }
}
