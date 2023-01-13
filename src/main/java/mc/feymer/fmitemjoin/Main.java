package mc.feymer.fmitemjoin;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public final class Main extends JavaPlugin implements Listener {
    public static Main instance;

    @Override
    public void onEnable() {

        instance = this;
        saveDefaultConfig();

        getLogger().info("Плагин включен!");
        Bukkit.getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onJoin(@NotNull PlayerJoinEvent e) {
        Player player = e.getPlayer();
        player.getInventory().setItem(0, HeadUtils.getSkull(getConfig().getString("item-join.skull")));
    }

    @EventHandler
    public void onAction(@NotNull PlayerInteractEvent e) {
        if (e.getItem() != null && e.getItem().getItemMeta() != null && e.getItem().getItemMeta().getDisplayName().equals(getConfig().getString("item-join.name"))) {
            e.getPlayer().performCommand(getConfig().getString("item-join.action"));
        }
    }

    @Override
    public void onDisable() {
        getLogger().info("Плагин выключен!");
    }

    public static Main getInstance() {
        return instance;
    }
}
