package mc.feymer.fmitemjoin;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin implements Listener {
    public static Main instance;

    @Override
    public void onEnable() {
        instance = this;
        getLogger().info("Плагин включен!");
        Bukkit.getPluginManager().registerEvents(this, this);

        // Привет, я MishaNeYT

    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        e.getPlayer().getInventory().setItem(0, HeadUtils.getSkull("238286f53f228c4a1bde1125df06e0105c1f62e3e609d4326b0172c2b759717e"));
    }

    @Override
    public void onDisable() {
        getLogger().info("Плагин выключен!");
    }

    public static Main getInstance() {
        return instance;
    }
}
