package de.buun.spigot.command;

import de.daver.buun.core.command.Command;
import de.daver.buun.core.command.CommandArguments;
import de.daver.buun.core.command.CommandProvider;
import de.daver.buun.core.command.Sender;
import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class SpigotCommandProvider implements CommandProvider {

    private final CommandMap commandMap;

    public SpigotCommandProvider(JavaPlugin plugin){
        this.commandMap = plugin.getServer().getCommandMap();
    }

    @Override
    public void register(Command command) {
        BukkitCommand bukkitCommand = new BukkitCommand(command.getName()) {
            @Override
            public boolean execute(@NotNull CommandSender commandSender, @NotNull String commandLabel, @NotNull String[] args) {
                Sender sender = new SpigotSender(commandSender);
                CommandArguments arguments = new SpigotCommandArguments(args);
                Command searched = command.searchSubCommand(arguments);
                if(!searched.getChecks().check(searched, arguments, sender)) return false;
                searched.getAction().accept(sender, arguments);
                return true;
            }

            @Override
            public @NotNull List<String> tabComplete(@NotNull CommandSender sender, @NotNull String alias, @NotNull String[] args) throws IllegalArgumentException {
                return new ArrayList<>(); //TODO Suggestions
            }
        };
        commandMap.register("buun", bukkitCommand);
    }
}
