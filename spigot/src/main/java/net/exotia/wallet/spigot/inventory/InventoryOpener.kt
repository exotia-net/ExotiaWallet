package net.exotia.wallet.spigot.inventory

import eu.okaeri.injector.Injector
import eu.okaeri.injector.annotation.Inject
import eu.okaeri.injector.annotation.PostConstruct
import net.exotia.wallet.spigot.factory.ConfigurationFactory
import net.exotia.wallet.spigot.inventory.categories.CategoriesInventoryConfiguration
import net.exotia.wallet.spigot.inventory.services.ServicesInventoryConfiguration
import org.bukkit.entity.Player
import xyz.xenondevs.invui.window.Window

class InventoryOpener {
    @Inject private val injector: Injector? = null
    @Inject private val configurationFactory: ConfigurationFactory? = null

    @PostConstruct
    fun onConstruct() {
        this.injector!!.registerInjectable(this.configurationFactory!!.produce(CategoriesInventoryConfiguration::class.java, "inventories/main.yml"))
        this.injector.registerInjectable(this.configurationFactory.produce(ServicesInventoryConfiguration::class.java, "inventories/services.yml"))
    }

    fun open(player: Player, openableInventory: Class<out OpenableInventory?>, vararg params: String?) {
        val inventory: OpenableInventory? = this.injector!!.createInstance(openableInventory)
        val window: Window = Window.single()
            .setViewer(player)
            .setGui(inventory!!.createGui(this, *params))
            .setTitle(inventory.getConfiguration().getTitle()!!)
            .build()
        window.open()
    }
}
