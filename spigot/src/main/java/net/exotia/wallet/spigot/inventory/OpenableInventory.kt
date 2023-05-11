package net.exotia.wallet.spigot.inventory

import xyz.xenondevs.invui.gui.Gui

interface OpenableInventory {
    fun createGui(inventoryOpener: InventoryOpener? = null, vararg params: String?, ): Gui
    fun getConfiguration(): InventoryConfiguration
}
