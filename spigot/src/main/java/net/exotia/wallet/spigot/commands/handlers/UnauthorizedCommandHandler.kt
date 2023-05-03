package net.exotia.wallet.spigot.commands.handlers

import dev.rollczi.litecommands.command.LiteInvocation
import dev.rollczi.litecommands.command.permission.RequiredPermissions
import dev.rollczi.litecommands.handle.PermissionHandler
import eu.okaeri.injector.annotation.Inject
import net.exotia.wallet.spigot.configuration.files.MessagesConfiguration
import net.exotia.wallet.spigot.utils.MessageUtil.sendMessage
import org.bukkit.command.CommandSender
import java.lang.String

class UnauthorizedCommandHandler : PermissionHandler<CommandSender?> {
    @Inject private val messages: MessagesConfiguration? = null

    override fun handle(sender: CommandSender?, invocation: LiteInvocation, requiredPermissions: RequiredPermissions) {
        sendMessage(sender!!, messages!!.unauthorizedCommand.replace("{permissions}", String.join(", ", requiredPermissions.permissions)))
    }
}