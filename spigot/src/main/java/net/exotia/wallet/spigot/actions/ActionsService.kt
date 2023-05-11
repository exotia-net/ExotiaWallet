package net.exotia.wallet.spigot.actions

import org.bukkit.entity.Player
import java.util.*

class ActionsService {
    private val actions: MutableList<Action>? = mutableListOf()

    fun parse(text: String, player: Player) {
        val args = text.split("]")
        val identifier = args[0].replaceFirst("[", "")
        val action: Optional<Action>? = this.actions?.stream()?.filter { action -> action.identifier() == identifier }?.findFirst()
        action?.get()?.execute(player, args[1])
    }
    fun parse(list: List<String>, player: Player) {
        list.forEach { line -> this.parse(line, player) }
    }

    fun registerAction(action: Action) {
        this.actions!!.add(action)
    }
}
