package net.exotia.exotiawallet.inventory

import eu.okaeri.injector.Injector
import eu.okaeri.injector.annotation.Inject
import eu.okaeri.injector.annotation.PostConstruct
import net.exotia.exotiawallet.factory.ConfigurationFactory
import net.exotia.exotiawallet.inventory.categories.CategoriesInventoryConfiguration
import org.bukkit.entity.Player
import xyz.xenondevs.invui.window.Window

class InventoryOpener {
    @Inject private val injector: Injector? = null
    @Inject private val configurationFactory: ConfigurationFactory? = null

    @PostConstruct
    fun onConstruct() {
        this.injector!!.registerInjectable(this.configurationFactory!!.produce(CategoriesInventoryConfiguration::class.java, "inventories/main.yml"))
    }

    fun open(player: Player, openableInventory: Class<out OpenableInventory?>?) {
        val inventory: OpenableInventory? = this.injector!!.createInstance(openableInventory)
        val window: Window = Window.single()
            .setViewer(player)
            .setGui(inventory!!.createGui())
            .setTitle(inventory.getConfiguration().getTitle()!!)
            .build();
        window.open()
    }
}
