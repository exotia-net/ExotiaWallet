package net.exotia.wallet.spigot.utils

import net.exotia.wallet.spigot.utils.MessageUtil.implementColors
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.SkullMeta
import java.util.*
import java.util.function.Function

class ItemCreator {
    private var lore: MutableList<String?> = ArrayList()
    private val enchantments = HashMap<Enchantment, Int>()
    private val material: Material?
    private var hideAttributes = false
    private var amount: Int
    private var displayName: String?
    private var uuid: UUID? = null

    @JvmOverloads
    constructor(material: Material?, amount: Int = 1) {
        displayName = null
        this.material = material
        this.amount = amount
    }

    constructor(material: Material?, uuid: UUID?) {
        displayName = null
        this.material = material
        amount = 1
        this.uuid = uuid
    }

    fun title(title: String?): ItemCreator {
        displayName = implementColors(title)
        return this
    }

    fun lore(lore: String?): ItemCreator {
        this.lore.add(implementColors(lore))
        return this
    }

    fun lore(loreLines: List<String>): ItemCreator {
        lore.addAll(
            loreLines.stream().map<String?>(Function<String, String?> { obj: String -> implementColors(obj) }).toList()
        )
        return this
    }

    fun lore(loreLines: Array<String>?): ItemCreator {
        lore.addAll(
            Arrays.stream<String>(loreLines)
                .map<String?>(Function<String, String?> { obj: String -> implementColors(obj) }).toList()
        )
        return this
    }

    fun setLoreForce(lore: MutableList<String?>): ItemCreator {
        this.lore = lore
        return this
    }

    fun setEnchantmentForce(enchantment: Enchantment?, level: Int): ItemCreator {
        enchantments.clear()
        if (enchantment == null) return this
        enchantments[enchantment] = level
        return this
    }

    fun enchantment(enchantment: Enchantment?, level: Int): ItemCreator {
        if (enchantment == null) return this
        enchantments.remove(enchantment)
        enchantments[enchantment] = level
        return this
    }

    fun amount(amount: Int): ItemCreator {
        this.amount = amount
        return this
    }

    fun hideAttributes(hideAttributes: Boolean): ItemCreator {
        this.hideAttributes = hideAttributes
        return this
    }

    fun hasEnchantment(enchantment: Enchantment): Boolean {
        return enchantments.containsKey(enchantment)
    }

    fun build(): ItemStack {
        val material = material ?: Material.BARRIER
        val itemStack = ItemStack(material, amount)
        val itemMeta = itemStack.itemMeta
        if (displayName != null && itemMeta != null) {
            itemMeta.setDisplayName(displayName)
        }
        if (material == Material.PLAYER_HEAD && uuid != null) {
            val skullMeta = itemMeta as SkullMeta
            if (skullMeta != null) {
                val offlinePlayer = Bukkit.getOfflinePlayer(uuid!!)
                skullMeta.setOwningPlayer(offlinePlayer)
            }
        }
        if (itemMeta != null && !lore.isEmpty()) {
            itemMeta.lore = lore
        }
        if (itemMeta != null && hideAttributes) {
            itemMeta.addItemFlags(*arrayOf(ItemFlag.HIDE_ATTRIBUTES))
            itemMeta.addItemFlags(*arrayOf(ItemFlag.HIDE_ENCHANTS))
        }
        itemStack.setItemMeta(itemMeta)
        itemStack.addUnsafeEnchantments(enchantments)
        return itemStack
    }
}