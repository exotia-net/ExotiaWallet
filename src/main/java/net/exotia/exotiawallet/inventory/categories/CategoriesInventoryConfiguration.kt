package net.exotia.exotiawallet.inventory.categories

import eu.okaeri.configs.OkaeriConfig
import net.exotia.exotiawallet.inventory.BaseItem
import net.exotia.exotiawallet.inventory.InventoryConfiguration
import net.exotia.exotiawallet.utils.ItemCreator
import net.exotia.exotiawallet.utils.MessageUtil.implementColors
import org.bukkit.Material

class CategoriesInventoryConfiguration : OkaeriConfig(), InventoryConfiguration {
    private val title: String = "&8&lSklep za vPLN"

    private val pattern = listOf<String>(
        "# # # # # # # # #",
        "# # # # # # # # #",
        "# # # # # # # # #",
        "# # # # # # # # #")

    private val items = hashMapOf<Char, BaseItem>(
        '#' to BaseItem(ItemCreator(Material.BLACK_STAINED_GLASS_PANE).title("&8l*").build(), emptyList()),
        'i' to BaseItem(ItemCreator(Material.NAME_TAG)
            .title("&6ItemShop")
            .lore(listOf("", " &8&l>> &7Kliknij aby zakupic vPLN", ""))
            .build(),
            listOf("[SEND_MESSAGE] ")
        )
    )

    override fun getPattern(): Array<String> {
        return this.pattern.toTypedArray<String>();
    }
    override fun getTitle(): String? {
        return implementColors(this.title)
    }
    override fun getItems(): HashMap<Char, BaseItem> {
        return this.items
    }
}
