package de.buun.spigot.command;

import de.daver.buun.core.command.CommandArguments;
import org.apache.logging.log4j.util.Strings;

public class SpigotCommandArguments implements CommandArguments {

    private final String[] array;

    public SpigotCommandArguments(String[] array){
        this.array = array;
    }

    @Override
    public String getString(int index) {
        return array[index];
    }

    @Override
    public int getLength() {
        return array.length;
    }

    @Override
    public String[] toStringArray() {
        return array;
    }

    @Override
    public String toLine() {
        return String.join(" ", array);
    }
}
