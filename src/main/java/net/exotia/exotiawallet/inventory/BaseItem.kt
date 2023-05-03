package net.exotia.exotiawallet.inventory

import eu.okaeri.configs.OkaeriConfig
import org.bukkit.inventory.ItemStack

class BaseItem(private val _itemStack: ItemStack, private val _actions: List<String>) : OkaeriConfig() {
    var itemStack: ItemStack
        get() = _itemStack
        set(value) {}
    var actions: List<String>
        get() = _actions
        set(value) {}
}
