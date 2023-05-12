package net.exotia.wallet.proxy.utils

import net.md_5.bungee.api.ChatColor
import net.md_5.bungee.api.chat.TextComponent
import net.md_5.bungee.api.connection.ProxiedPlayer
import java.util.regex.Pattern

object MessageUtil {
    private val hexPattern = Pattern.compile("#[a-fA-F0-9]{6}")

    fun sendMessage(player: ProxiedPlayer, message: String) {
        player.sendMessage(*TextComponent.fromLegacyText(implementColors(message)))
    }

    fun implementColors(string: String): String {
        var message: String = string
        var matcher = hexPattern.matcher(message)
        while (matcher.find()) {
            val color = message.substring(matcher.start(), matcher.end())
            message = message.replace(color, ChatColor.of(color).toString() + "")
            matcher = hexPattern.matcher(message)
        }
        return ChatColor.translateAlternateColorCodes('&', message.replace("<<", "«").replace(">>", "»"))
    }

    fun implementColors(message: List<String>?): List<String?>? {
        if (message == null) return null
        val messages: MutableList<String?> = ArrayList()
        message.forEach { string: String -> messages.add(implementColors(string)) }
        return messages
    }
}