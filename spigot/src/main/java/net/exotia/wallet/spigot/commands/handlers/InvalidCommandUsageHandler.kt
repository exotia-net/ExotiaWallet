package net.exotia.wallet.spigot.commands.handlers

import dev.rollczi.litecommands.command.LiteInvocation
import dev.rollczi.litecommands.handle.InvalidUsageHandler
import dev.rollczi.litecommands.schematic.Schematic
import eu.okaeri.injector.annotation.Inject
import net.exotia.wallet.spigot.configuration.files.MessagesConfiguration
import net.exotia.wallet.spigot.utils.MessageUtil.sendMessage
import org.bukkit.command.CommandSender

class InvalidCommandUsageHandler : InvalidUsageHandler<CommandSender?> {
    @Inject private val messages: MessagesConfiguration? = null

    override fun handle(sender: CommandSender?, invocation: LiteInvocation, schematic: Schematic) {
        val schematics = schematic.schematics
        if (schematics.size == 1) {
            sendMessage(sender!!, this.messages!!.invalidCommandUsage.replace("{command}", schematics[0]))
            return
        }
        sendMessage(sender!!, this.messages!!.invalidCommandUsageHeader)
        for (sch in schematics) {
            sendMessage(sender, this.messages!!.invalidCommandUsageHelper.replace("{schema}", sch))
        }
    }
}