package net.exotia.exotiawallet.utils

import net.md_5.bungee.api.ChatColor
import net.md_5.bungee.api.ChatMessageType
import net.md_5.bungee.api.chat.TextComponent
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import java.util.function.Consumer
import java.util.regex.Pattern

object MessageUtil {
    private val hexPattern = Pattern.compile("#[a-fA-F0-9]{6}")

    fun sendMessage(sender: CommandSender, message: String?) {
        sender.sendMessage(implementColors(message)!!)
    }

    fun sendMessage(player: Player, message: String?) {
        player.sendMessage(implementColors(message)!!)
    }

    @JvmStatic
    fun implementColors(message: String?): String? {
        var message: String? = message ?: return null
        var matcher = hexPattern.matcher(message)
        while (matcher.find()) {
            val color = message!!.substring(matcher.start(), matcher.end())
            message = message.replace(color, ChatColor.of(color).toString() + "")
            matcher = hexPattern.matcher(message)
        }
        return ChatColor.translateAlternateColorCodes('&', message!!.replace("<<", "«").replace(">>", "»"))
    }

    fun implementColors(message: List<String?>?): List<String?>? {
        if (message == null) return null
        val messages: MutableList<String?> = ArrayList()
        message.forEach(Consumer { s: String? -> messages.add(implementColors(s)) })
        return messages
    }

    fun sendTitle(player: Player, fadeIn: Int?, stay: Int?, fadeOut: Int?, message: String?, subtitle: String?) {
        player.sendTitle(implementColors(message), implementColors(subtitle), fadeIn!!, stay!!, fadeOut!!)
    }

    fun sendActionbar(player: Player, message: String?) {
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent(implementColors(message)))
    }

    fun clearTitle(player: Player) {
        sendTitle(player, 0, 0, 0, "", "")
    }
}