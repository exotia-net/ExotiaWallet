package net.exotia.wallet.spigot.objects

import eu.okaeri.configs.OkaeriConfig
import org.bukkit.inventory.ItemStack

class Category(
    private var _slot: Int,
    private var _itemStack: ItemStack,
    private var _services: List<Service>
) : OkaeriConfig() {
    var slot: Int
        get() = _slot
        set(value) { _slot = value }
    var itemStack: ItemStack
        get() = _itemStack
        set(value) { _itemStack = value }
    var services: List<Service>
        get() = _services
        set(value) { _services = value }
}
