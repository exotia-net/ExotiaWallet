package net.exotia.exotiawallet.configuration.files

import eu.okaeri.configs.OkaeriConfig
import eu.okaeri.configs.annotation.Header
import net.exotia.exotiawallet.utils.ItemCreator
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.ItemStack

@Header(
    "################################",
    "# ",
    "# ExotiaWallet configuration",
    "# ",
    "################################"
)
class PluginConfiguration : OkaeriConfig() {
    val command: String = "portfel"
    val aliases: List<String> = listOf("skarbonka", "vpln")

    val categories = hashMapOf<String, Category>(
        "rangi" to Category(1, ItemCreator(Material.REDSTONE)
            .title("&8&l>> &aRangi")
            .lore(listOf("", " &8&l>> &7Kliknij aby wybrac"))
            .enchantment(Enchantment.ARROW_FIRE, 1000)
            .hideAttributes(true)
            .build()),
        "inne" to Category(2, ItemCreator(Material.STONE)
            .title("&8&l>> &aInne")
            .lore(listOf("", " &8&l>> &7Kliknij aby wybrac"))
            .build()),
    )

    class Category(private var _slot: Int, private var _itemStack: ItemStack) : OkaeriConfig() {
        var slot: Int
            get() = _slot
            set(value) { _slot = value }
        var itemStack: ItemStack
            get() = _itemStack
            set(value) { _itemStack = value }
    }
}

