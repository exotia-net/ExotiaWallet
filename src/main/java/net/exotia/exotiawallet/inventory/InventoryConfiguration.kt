package net.exotia.exotiawallet.inventory

interface InventoryConfiguration {
    fun getPattern(): Array<String>
    fun getTitle(): String?
    fun getItems(): HashMap<Char, BaseItem>
}