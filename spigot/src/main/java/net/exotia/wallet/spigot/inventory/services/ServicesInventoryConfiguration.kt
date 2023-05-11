package net.exotia.wallet.spigot.inventory.services

import eu.okaeri.configs.OkaeriConfig
import net.exotia.wallet.spigot.inventory.BaseItem
import net.exotia.wallet.spigot.inventory.InventoryConfiguration
import net.exotia.wallet.spigot.utils.ItemCreator
import net.exotia.wallet.spigot.utils.MessageUtil
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment

class ServicesInventoryConfiguration : OkaeriConfig(), InventoryConfiguration {
    private val title: String = "&8&lUslugi"

    private val pattern = listOf<String>(
        "# # # # # # # # #",
        "# x x x x x x x #",
        "# x x x x x x x #",
        "# # # # r # # # #")

    private val items = hashMapOf<Char, BaseItem>(
        '#' to BaseItem(ItemCreator(Material.BLACK_STAINED_GLASS_PANE).title("&8l*").build(), emptyList()),
        'r' to BaseItem(
            ItemCreator(Material.BARRIER)
            .title("&c&lPOWROT")
            .enchantment(Enchantment.ARROW_DAMAGE, 100)
            .hideAttributes(true)
            .build(),
            listOf("[BACK] ")
        )
    )

    override fun getPattern(): Array<String> {
        return this.pattern.toTypedArray<String>();
    }
    override fun getTitle(): String? {
        return MessageUtil.implementColors(this.title)
    }
    override fun getItems(): HashMap<Char, BaseItem> {
        return this.items
    }
}
