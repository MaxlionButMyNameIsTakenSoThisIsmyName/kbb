package agmas.kingsbutbad.tasks;

import agmas.kingsbutbad.KingsButBad;
import agmas.kingsbutbad.utils.CreateText;
import agmas.kingsbutbad.utils.Role;
import agmas.kingsbutbad.utils.RoleManager;
import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;


// role related stuff here

public class RoleTask extends BukkitRunnable {
    @Override
    public void run(){

        for (Player p : Bukkit.getOnlinePlayers()) {
            KingsButBad.princeGender.putIfAbsent(p, "Prince");
            if (KingsButBad.roles.get(p).isPowerful) {
                if (KingsButBad.isInside(p, new Location(Bukkit.getWorld("world"), -82, -44, -15), new Location(Bukkit.getWorld("world"), -70, -56, -27))) {
                    p.teleport(new Location(Bukkit.getWorld("world"), -101.5, -57, -18.5));
                    for (Entity pe : p.getPassengers()) {
                        pe.leaveVehicle();
                    }
                    p.sendMessage(CreateText.addColors("<red><b>>> </b>You can't be in here!"));
                }
            }
            KingsButBad.roles.putIfAbsent(p, Role.PEASANT);

            if (KingsButBad.api.getPlayerAdapter(Player.class).getUser(p).getCachedData().getMetaData().getPrefix() != null) {
                if (KingsButBad.king2 == p) {
                    p.setPlayerListName(CreateText.addColors("<dark_gray>[" + KingsButBad.api.getPlayerAdapter(Player.class).getUser(p).getCachedData().getMetaData().getPrefix() + "<dark_gray>] " + "<dark_gray>[" + "<gradient:#FFFF52:#FFBA52><b>" + KingsButBad.kingGender2.toUpperCase() + "<dark_gray></b><dark_gray>] <white>") + KingsButBad.roles.get(p).chatColor + p.getName());
                } else {
                    if (KingsButBad.roles.get(p).equals(Role.PRINCE)) {
                        p.setPlayerListName(CreateText.addColors("<dark_gray>[" + KingsButBad.api.getPlayerAdapter(Player.class).getUser(p).getCachedData().getMetaData().getPrefix() + "<dark_gray>] " + "[<gradient:#FFFF52:#FFBA52>" + KingsButBad.princeGender.get(p).toUpperCase() + "<dark_gray>] ") + KingsButBad.roles.get(p).chatColor + p.getName());
                    }  else {
                        p.setPlayerListName(CreateText.addColors("<dark_gray>[" + KingsButBad.api.getPlayerAdapter(Player.class).getUser(p).getCachedData().getMetaData().getPrefix() + "<dark_gray>]  " +"<dark_gray>[" + "<gradient:#FFFF52:#FFBA52>" + KingsButBad.roles.get(p).uncompressedColors + "<dark_gray><dark_gray>] <white>") + KingsButBad.roles.get(p).chatColor + p.getName());
                    }
                }

            } else {
                if (KingsButBad.king2 == p) {
                    p.setPlayerListName(CreateText.addColors("<dark_gray>[" + "<gradient:#FFFF52:#FFBA52><b>" + KingsButBad.kingGender2.toUpperCase() + "<dark_gray></b><dark_gray>] <white>") + KingsButBad.roles.get(p).chatColor + p.getName());
                } else {
                    if (KingsButBad.roles.get(p).equals(Role.PRINCE)) {
                        p.setPlayerListName(CreateText.addColors("<dark_gray>[<gradient:#FFFF52:#FFBA52>" + KingsButBad.princeGender.get(p).toUpperCase() + "<dark_gray>] <white>") + KingsButBad.roles.get(p).chatColor + p.getName());
                    } else {
                        p.setPlayerListName(CreateText.addColors("<dark_gray>[" + "<gradient:#FFFF52:#FFBA52>" + KingsButBad.roles.get(p).uncompressedColors + "<dark_gray><dark_gray>] <white>") + KingsButBad.roles.get(p).chatColor + p.getName());
                    }
                }

            }
            p.setDisplayName(p.getPlayerListName());
            if (KingsButBad.king2 != null) {
                if (!KingsButBad.king2.isOnline()) {
                    KingsButBad.king2 = null;
                }
            }



            if (KingsButBad.roles.get(p).equals(Role.KING) && !RoleManager.isKingAtAll(p)) {
                KingsButBad.roles.put(p, Role.PEASANT);
                RoleManager.givePlayerRole(p);
            }
        }
    }
}