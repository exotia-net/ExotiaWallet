package net.exotia.wallet.spigot.inventory.categories

import eu.okaeri.injector.annotation.Inject
import net.exotia.wallet.spigot.configuration.files.PluginConfiguration
import net.exotia.wallet.spigot.inventory.InventoryConfiguration
import net.exotia.wallet.spigot.inventory.OpenableInventory
import org.bukkit.entity.Player
import org.bukkit.event.inventory.ClickType
import org.bukkit.event.inventory.InventoryClickEvent
import xyz.xenondevs.invui.gui.Gui
import xyz.xenondevs.invui.gui.SlotElement
import xyz.xenondevs.invui.gui.structure.Markers
import xyz.xenondevs.invui.item.Item
import xyz.xenondevs.invui.item.ItemProvider
import xyz.xenondevs.invui.item.builder.ItemBuilder
import xyz.xenondevs.invui.item.impl.AbstractItem
import xyz.xenondevs.invui.item.impl.SimpleItem

class CategoriesInventory : OpenableInventory {
    @Inject private val inventoryConfiguration: CategoriesInventoryConfiguration? = null
    @Inject private val pluginConfiguration: PluginConfiguration? = null;

    override fun createGui(): Gui {
        val builder = Gui.normal()
            .setStructure(*inventoryConfiguration!!.getPattern())
            .addIngredient('x', Markers.CONTENT_LIST_SLOT_HORIZONTAL)

        this.inventoryConfiguration.getItems().forEach { (key, content) ->
            run {
                builder.addIngredient(key, SimpleItem(ItemBuilder(content.itemStack)))
            }
        }
        val gui = builder.build()

        this.pluginConfiguration!!.categories.forEach { (key, content) ->
            run {
                val item: Item = object : AbstractItem() {
                    override fun getItemProvider(): ItemProvider {
                        return ItemBuilder(content.itemStack)
                    }
                    override fun handleClick(clickType: ClickType, player: Player, inventoryClickEvent: InventoryClickEvent) {
                        player.sendMessage(key)
                    }
                }
                gui.setItem(content.slot, item)
                //gui[content.slot] = item
            }
        }
        return gui
    }

    override fun getConfiguration(): InventoryConfiguration {
        return this.inventoryConfiguration!!
    }
}
