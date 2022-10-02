package de.buun.spigot.command;

import de.daver.buun.core.command.Sender;
import org.bukkit.command.CommandSender;

public class SpigotSender implements Sender {

    private final CommandSender sender;

    public SpigotSender(CommandSender sender){
        this.sender = sender;
    }

    @Override
    public boolean hasPermission(String permission) {
        return sender.hasPermission(permission);
    }
}
