package net.exotia.wallet.spigot

import org.bukkit.plugin.java.JavaPlugin
import eu.okaeri.injector.OkaeriInjector;
import net.exotia.wallet.spigot.actions.ActionsService
import net.exotia.wallet.spigot.actions.providers.ExecuteCommandAction
import net.exotia.wallet.spigot.actions.providers.NotifyAction
import net.exotia.wallet.spigot.commands.CommandsModule
import net.exotia.wallet.spigot.configuration.ConfigurationModule
import net.exotia.wallet.spigot.inventory.InventoryOpener

class ExotiaWallet : JavaPlugin() {
    private val injector: OkaeriInjector = OkaeriInjector.create();

    override fun onEnable() {
        this.injector.registerInjectable(this);
        this.injector.registerInjectable(this.injector);

        this.server.messenger.registerOutgoingPluginChannel(this, NOTIFICATION_CHANNEL);

        this.injector.createInstance(ConfigurationModule::class.java)

        val actionsService: ActionsService = ActionsService()
        actionsService.registerAction(NotifyAction(this))
        actionsService.registerAction(ExecuteCommandAction())
        this.injector.registerInjectable(actionsService)

        this.injector.registerInjectable(this.injector.createInstance(InventoryOpener::class.java))
        this.injector.createInstance(CommandsModule::class.java)
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }

    companion object {
        const val NOTIFICATION_CHANNEL: String = "wallet:notification"
    }
}
