package net.exotia.wallet.spigot.commands.execute;

import dev.rollczi.litecommands.command.execute.Execute;
import dev.rollczi.litecommands.command.route.Route;
import eu.okaeri.configs.exception.OkaeriException;
import eu.okaeri.injector.annotation.Inject;
import net.exotia.wallet.spigot.configuration.files.MessagesConfiguration;
import net.exotia.wallet.spigot.configuration.files.PluginConfiguration;
import net.exotia.wallet.spigot.inventory.categories.CategoriesInventoryConfiguration;
import net.exotia.wallet.spigot.inventory.services.ServicesInventoryConfiguration;
import net.exotia.wallet.spigot.utils.MessageUtil;
import org.bukkit.command.CommandSender;

@Route(name = "wallet-admin")
public class WalletAdminCommand {
    @Inject private MessagesConfiguration messagesConfiguration;
    @Inject private PluginConfiguration pluginConfiguration;
    @Inject private CategoriesInventoryConfiguration categoriesInventoryConfiguration;
    @Inject private ServicesInventoryConfiguration servicesInventoryConfiguration;

    @Execute(route = "reload")
    public void reload(CommandSender sender) {
        try {
            this.messagesConfiguration.load(true);
            this.pluginConfiguration.load(true);
            this.categoriesInventoryConfiguration.load(true);
            this.servicesInventoryConfiguration.load(true);
            sender.sendMessage(MessageUtil.implementColors("&8&l>> &aPomyslnie przeladowano!"));
        } catch (OkaeriException exception) {
            exception.printStackTrace();
            sender.sendMessage(MessageUtil.implementColors("&8&l>> &cBlad podczas ladowania configu! Sprawdz konsole"));
        }
    }
}
