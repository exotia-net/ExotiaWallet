package net.exotia.wallet.spigot.inventory

interface InventoryConfiguration {
    fun getPattern(): Array<String>
    fun getTitle(): String?
    fun getItems(): HashMap<Char, BaseItem>
}