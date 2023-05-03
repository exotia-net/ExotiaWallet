package net.exotia.wallet.spigot.commands.execute

import dev.rollczi.litecommands.command.execute.Execute
import dev.rollczi.litecommands.command.route.Route
import eu.okaeri.injector.annotation.Inject
import net.exotia.wallet.spigot.inventory.InventoryOpener
import net.exotia.wallet.spigot.inventory.categories.CategoriesInventory
import org.bukkit.entity.Player

@Route(name = "wallet")
class WalletCommand {
    @Inject private val inventoryOpener: InventoryOpener? = null

    @Execute
    fun openWallet(player: Player) {
        this.inventoryOpener!!.open(player, CategoriesInventory::class.java)
    }
}
