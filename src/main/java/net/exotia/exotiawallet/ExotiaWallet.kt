package net.exotia.exotiawallet

import org.bukkit.plugin.java.JavaPlugin
import eu.okaeri.injector.OkaeriInjector;
import net.exotia.exotiawallet.commands.CommandsModule
import net.exotia.exotiawallet.configuration.ConfigurationModule
import net.exotia.exotiawallet.inventory.InventoryOpener

class ExotiaWallet : JavaPlugin() {
    private val injector: OkaeriInjector = OkaeriInjector.create();

    override fun onEnable() {
        this.injector.registerInjectable(this);
        this.injector.registerInjectable(this.injector);

        this.injector.createInstance(ConfigurationModule::class.java)

        this.injector.registerInjectable(this.injector.createInstance(InventoryOpener::class.java))
        this.injector.createInstance(CommandsModule::class.java)
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }
}
