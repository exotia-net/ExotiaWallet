package net.exotia.exotiawallet.commands.arguments

import dev.rollczi.litecommands.argument.ArgumentName
import dev.rollczi.litecommands.argument.simple.OneArgument
import dev.rollczi.litecommands.command.LiteInvocation
import dev.rollczi.litecommands.suggestion.Suggestion
import eu.okaeri.injector.annotation.Inject
import net.exotia.exotiawallet.configuration.files.MessagesConfiguration
import net.exotia.exotiawallet.inventory.InventoryOpener
import net.exotia.exotiawallet.utils.MessageUtil.implementColors
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import panda.std.Option
import panda.std.Result
import java.util.stream.Collectors

@ArgumentName("gracz")
class PlayerArgument : OneArgument<Player> {
    @Inject private val messages: MessagesConfiguration? = null

    override fun parse(invocation: LiteInvocation, argument: String): Result<Player, Any> {
        return Option.of(Bukkit.getPlayer(argument))
            .toResult(implementColors(this.messages!!.playerIsOffline))
    }

    override fun suggest(invocation: LiteInvocation): List<Suggestion> {
        return Bukkit.getOnlinePlayers().stream()
            .map { obj: Player -> obj.name }
            .map { suggestion: String? -> Suggestion.of(suggestion) }
            .collect(Collectors.toList())
    }
}
