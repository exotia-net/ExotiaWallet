package net.exotia.wallet.spigot.inventory.services

import eu.okaeri.injector.annotation.Inject
import net.exotia.wallet.spigot.configuration.files.PluginConfiguration
import net.exotia.wallet.spigot.inventory.InventoryConfiguration
import net.exotia.wallet.spigot.inventory.InventoryOpener
import net.exotia.wallet.spigot.inventory.OpenableInventory
import net.exotia.wallet.spigot.objects.Category
import org.bukkit.entity.Player
import org.bukkit.event.inventory.ClickType
import org.bukkit.event.inventory.InventoryClickEvent
import xyz.xenondevs.invui.gui.Gui
import xyz.xenondevs.invui.gui.structure.Markers
import xyz.xenondevs.invui.item.Item
import xyz.xenondevs.invui.item.ItemProvider
import xyz.xenondevs.invui.item.builder.ItemBuilder
import xyz.xenondevs.invui.item.impl.AbstractItem
import xyz.xenondevs.invui.item.impl.SimpleItem

class ServicesInventory : OpenableInventory {
    @Inject private val inventoryConfiguration: ServicesInventoryConfiguration? = null
    @Inject private val pluginConfiguration: PluginConfiguration? = null;
    override fun createGui(inventoryOpener: InventoryOpener?, vararg params: String?): Gui {
        val builder = Gui.normal()
            .setStructure(*inventoryConfiguration!!.getPattern())
            .addIngredient('x', Markers.CONTENT_LIST_SLOT_HORIZONTAL)

        this.inventoryConfiguration.getItems().forEach { (key, content) ->
            run {
                builder.addIngredient(key, SimpleItem(ItemBuilder(content.itemStack)))
            }
        }

        val gui: Gui = builder.build();
        val category: Category? = this.pluginConfiguration!!.categories[params[0]]

        category!!.services.forEach { service ->
            run {
                val item: Item = object : AbstractItem() {
                    override fun getItemProvider(): ItemProvider {
                        return ItemBuilder(service.icon)
                    }
                    override fun handleClick(clickType: ClickType, player: Player, inventoryClickEvent: InventoryClickEvent) {
                        service.execute()
                    }
                }
                gui.addItems(item)
            }
        }
        return gui
    }

    override fun getConfiguration(): InventoryConfiguration {
        return this.inventoryConfiguration!!
    }
}
