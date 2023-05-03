package net.exotia.exotiawallet.commands

import dev.rollczi.litecommands.bukkit.LiteBukkitFactory
import dev.rollczi.litecommands.bukkit.tools.BukkitOnlyPlayerContextual
import dev.rollczi.litecommands.bukkit.tools.BukkitPlayerArgument
import dev.rollczi.litecommands.factory.CommandEditor
import eu.okaeri.injector.Injector
import eu.okaeri.injector.annotation.Inject
import eu.okaeri.injector.annotation.PostConstruct
import net.exotia.exotiawallet.commands.arguments.PlayerArgument
import net.exotia.exotiawallet.commands.execute.WalletCommand
import net.exotia.exotiawallet.commands.handlers.InvalidCommandUsageHandler
import net.exotia.exotiawallet.commands.handlers.UnauthorizedCommandHandler
import net.exotia.exotiawallet.configuration.files.MessagesConfiguration
import net.exotia.exotiawallet.configuration.files.PluginConfiguration
import org.bukkit.entity.Player
import org.bukkit.plugin.Plugin

class CommandsModule {
    @Inject private val configuration: PluginConfiguration? = null
    @Inject private val messages: MessagesConfiguration? = null
    @Inject private val plugin: Plugin? = null
    @Inject private val injector: Injector? = null

    @PostConstruct
    fun onConstruct() {
        LiteBukkitFactory.builder(this.plugin?.server, this.plugin?.name)
            .argument(Player::class.java, BukkitPlayerArgument(this.plugin?.server, this.messages!!.playerNotFound))
            .contextualBind(Player::class.java, BukkitOnlyPlayerContextual(this.messages.commandOnlyForPlayer))

            // Arguments
            .argument(Player::class.java, this.injector!!.createInstance(PlayerArgument::class.java))
            // Commands instances
            .commandInstance(this.injector.createInstance(WalletCommand::class.java))

            .commandEditor("wallet") {
                    command: CommandEditor.State -> command.name(this.configuration!!.command).aliases(this.configuration!!.aliases)
            }
            .invalidUsageHandler(this.injector.createInstance(InvalidCommandUsageHandler::class.java))
            .permissionHandler(this.injector.createInstance(UnauthorizedCommandHandler::class.java))
            .register()
    }
}