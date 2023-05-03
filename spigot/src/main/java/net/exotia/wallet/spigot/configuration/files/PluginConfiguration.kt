package net.exotia.wallet.spigot.configuration.files

import eu.okaeri.configs.OkaeriConfig
import net.exotia.wallet.spigot.objects.Category
import net.exotia.wallet.spigot.objects.Service
import net.exotia.wallet.spigot.utils.ItemCreator
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment

class PluginConfiguration : OkaeriConfig() {
    val command: String = "portfel"
    val aliases: List<String> = listOf("skarbonka", "vpln")

    val categories = hashMapOf<String, Category>(
        "rangi" to Category(1, ItemCreator(Material.REDSTONE)
            .title("&8&l>> &aRangi")
            .lore(listOf("", " &8&l>> &7Kliknij aby wybrac"))
            .enchantment(Enchantment.ARROW_FIRE, 1000)
            .hideAttributes(true)
            .build(),
            listOf(
                Service("Ranga VIP", 15.98, ItemCreator(Material.GOLDEN_CHESTPLATE).build(),
                    listOf(
                        "[NOTIFY] ",
                        "[NOTIFY] &8&l>> &7Gracz &6&l{player} &7zakupil range vip!",
                        "[NOTIFY] &8&l>> &7Dziekujemy za wsparcie &c&l<3",
                        "[NOTIFY] ",
                        "[COMMAND] lp user {player} group set vip 30d"
                    )
                ),
                Service("Ranga SVIP", 30.98, ItemCreator(Material.DIAMOND_CHESTPLATE).build(),
                    listOf(
                        "[NOTIFY] ",
                        "[NOTIFY] &8&l>> &7Gracz &6&l{player} &7zakupil range vip!",
                        "[NOTIFY] &8&l>> &7Dziekujemy za wsparcie &c&l<3",
                        "[NOTIFY] ",
                        "[COMMAND] lp user {player} group set vip 30d"
                    )
                )
            )
        ),
        "inne" to Category(2, ItemCreator(Material.STONE)
            .title("&8&l>> &aInne")
            .lore(listOf("", " &8&l>> &7Kliknij aby wybrac"))
            .build(),
            listOf(
                Service("Kluczyki", 2.98, ItemCreator(Material.TRIPWIRE_HOOK).build(),
                    listOf(
                        "[NOTIFY] ",
                        "[NOTIFY] &8&l>> &7Gracz &6&l{player} &7zakupil klucze!",
                        "[NOTIFY] &8&l>> &7Dziekujemy za wsparcie &c&l<3",
                        "[NOTIFY] ",
                        "[COMMAND] dwerqer"
                    )
                ),
            )
        ),
    )
}