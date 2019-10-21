package net.euphalys.euphawarp;

import net.euphalys.core.api.EuphalysApi;
import net.euphalys.euphawarp.commands.WarpCommands;
import net.euphalys.euphawarp.data.WarpManager;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Dinnerwolph
 */
public class EuphaWarp extends JavaPlugin {

    public static Map<String, Location> warp = new ConcurrentHashMap<>();
    public static WarpManager warpManager;

    @Override
    public void onEnable() {
        warpManager = new WarpManager(EuphalysApi.getInstance());
        warpManager.getAllWarps();
        getCommand("warp").setExecutor(new WarpCommands());
    }
}
