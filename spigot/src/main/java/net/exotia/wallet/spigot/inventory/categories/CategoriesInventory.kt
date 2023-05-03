package net.exotia.wallet.spigot.inventory.categories

import eu.okaeri.injector.annotation.Inject
import net.exotia.wallet.spigot.inventory.InventoryConfiguration
import net.exotia.wallet.spigot.inventory.OpenableInventory
import xyz.xenondevs.invui.gui.Gui
import xyz.xenondevs.invui.gui.structure.Markers
import xyz.xenondevs.invui.item.builder.ItemBuilder
import xyz.xenondevs.invui.item.impl.SimpleItem

class CategoriesInventory : OpenableInventory {
    @Inject private val inventoryConfiguration: CategoriesInventoryConfiguration? = null

    override fun createGui(): Gui {
        val builder = Gui.normal()
            .setStructure(*inventoryConfiguration!!.getPattern())
            .addIngredient('x', Markers.CONTENT_LIST_SLOT_HORIZONTAL)

        this.inventoryConfiguration.getItems().forEach { (key, content) ->
            run {
                builder.addIngredient(key, SimpleItem(ItemBuilder(content.itemStack)))
            }
        }
        return builder.build()
    }

    override fun getConfiguration(): InventoryConfiguration {
        return this.inventoryConfiguration!!
    }
}
