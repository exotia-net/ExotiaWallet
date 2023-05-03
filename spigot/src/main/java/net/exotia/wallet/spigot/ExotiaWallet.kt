package net.exotia.wallet.spigot

import org.bukkit.plugin.java.JavaPlugin
import eu.okaeri.injector.OkaeriInjector;
import net.exotia.wallet.spigot.commands.CommandsModule
import net.exotia.wallet.spigot.configuration.ConfigurationModule
import net.exotia.wallet.spigot.inventory.InventoryOpener

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
