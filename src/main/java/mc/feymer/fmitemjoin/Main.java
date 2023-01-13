package mc.feymer.fmitemjoin;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public final class Main extends JavaPlugin implements Listener {

    private static Main instance;

    @Override
    public void onEnable() {

        instance = this;
        saveDefaultConfig();

        getLogger().info("Плагин включен!");
        Bukkit.getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onJoin(@NotNull PlayerJoinEvent e) {
        e.getPlayer().getInventory().setItem(0, HeadUtils.getSkull(getConfig().getString("item-join.skull")));
    }

    @EventHandler
    public void onAction(@NotNull PlayerInteractEvent e) {

        // Проверка на нажатие
        if (!(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK)) return;

        // Проверка на исключения и название предмета
        if (e.getItem() != null && e.getItem().getItemMeta() != null && e.getItem().getItemMeta().getDisplayName().equals(getConfig().getString("item-join.name"))) {
            e.getPlayer().chat(getConfig().getString("item-join.action"));
        }
    }

    public synchronized static Main getInstance() {
        return instance;
    }
}
