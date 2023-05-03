package net.exotia.wallet.spigot.configuration.files

import eu.okaeri.configs.OkaeriConfig

class MessagesConfiguration : OkaeriConfig() {
    val playerIsOffline: String = "&8&l>> &cTen gracz jest offline!"
    val playerNotFound: String = "&8&l>> &cTen gracz nie zostal odnaleziony!"
    val commandOnlyForPlayer: String = "&8&l>> &cTej komendy nie mozesz uzyc w konsoli!"
    val invalidCommandUsage: String = "&cNie poprawne użycie komendy &8>> &7{command}"
    val invalidCommandUsageHeader: String = "&cNie poprawne użycie komendy!"
    val invalidCommandUsageHelper: String = "&8 >> &7{schema}"
    val unauthorizedCommand: String = "&cNie masz permisji do tej komendy! &7({permissions})"
}
