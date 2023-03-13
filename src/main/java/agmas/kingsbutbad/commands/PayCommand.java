package agmas.kingsbutbad.commands;

import agmas.kingsbutbad.KingsButBad;
import agmas.kingsbutbad.utils.CreateText;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

public class PayCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player))
            return true;

        if (args.length < 2)
            return false;

        Player player = (Player) sender;
        Player target = Bukkit.getPlayer(args[0]);

        if (target == null || !args[1].matches("\\d+(\\.\\d+)?") || player.equals(target))
            return false;

        double amount = Double.parseDouble(args[1]);

        if (amount < 0)
            return false; // Shouldn't be possible but better safe than sorry

        PersistentDataContainer playerContainer = player.getPersistentDataContainer();
        double playerOriginal = playerContainer.get(KingsButBad.money, PersistentDataType.DOUBLE);

        PersistentDataContainer targetContainer = target.getPersistentDataContainer();
        double targetOriginal = targetContainer.get(KingsButBad.money, PersistentDataType.DOUBLE);

        playerContainer.set(KingsButBad.money, PersistentDataType.DOUBLE, playerOriginal - amount);
        targetContainer.set(KingsButBad.money, PersistentDataType.DOUBLE, targetOriginal + amount);

        player.sendMessage(CreateText.addColors("<gray>Sent <green>$" + amount + " <gray>to <white>" + target.getName() + "<gray>."));

        return true;
    }
}
