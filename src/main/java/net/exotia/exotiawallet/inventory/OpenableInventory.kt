package net.exotia.exotiawallet.inventory

import xyz.xenondevs.invui.gui.Gui

interface OpenableInventory {
    fun createGui(): Gui
    fun getConfiguration(): InventoryConfiguration
}
