package net.exotia.wallet.spigot.inventory

import xyz.xenondevs.invui.gui.Gui

interface OpenableInventory {
    fun createGui(): Gui
    fun getConfiguration(): InventoryConfiguration
}
