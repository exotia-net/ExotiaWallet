package net.exotia.exotiawallet.commands.handlers

import dev.rollczi.litecommands.command.LiteInvocation
import dev.rollczi.litecommands.command.permission.RequiredPermissions
import dev.rollczi.litecommands.handle.PermissionHandler
import eu.okaeri.injector.annotation.Inject
import net.exotia.exotiawallet.configuration.files.MessagesConfiguration
import net.exotia.exotiawallet.utils.MessageUtil.sendMessage
import org.bukkit.command.CommandSender
import java.lang.String

class UnauthorizedCommandHandler : PermissionHandler<CommandSender?> {
    @Inject private val messages: MessagesConfiguration? = null

    override fun handle(sender: CommandSender?, invocation: LiteInvocation, requiredPermissions: RequiredPermissions) {
        sendMessage(sender!!, messages!!.unauthorizedCommand.replace("{permissions}", String.join(", ", requiredPermissions.permissions)))
    }
}