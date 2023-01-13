package mc.feymer.fmitemjoin;

import com.google.common.collect.Lists;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

public class HeadUtils {

    public static @NotNull ItemStack getSkull(String url) {
        ItemStack itemStack = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
        if (url == null || url.isEmpty()) return itemStack;

        SkullMeta skullMeta = (SkullMeta) itemStack.getItemMeta();
        GameProfile gameProfile = new GameProfile(UUID.randomUUID(), null);

        // Уникальный код головы Value
        String toEncode = "{\"textures\":{\"SKIN\":{\"url\":\"" + url + "\"}}}";
        Base64.getEncoder().encodeToString(toEncode.getBytes());

        // Добавление текстурки
        gameProfile.getProperties().put("textures", new Property("textures", toEncode));

        Field field;
        try {
            field = skullMeta.getClass().getDeclaredField("profile");
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }

        field.setAccessible(true);
        try {
            field.set(skullMeta, gameProfile);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        skullMeta.setDisplayName(color(Main.getInstance().getConfig().getString("item-join.name")));

        List<String> lores = Main.getInstance().getConfig().getStringList("item-join.lore");
        List<String> lore = Lists.newArrayList();

        lores.forEach(line -> lore.add(color(line)));
        skullMeta.setLore(lore);

        itemStack.setItemMeta(skullMeta);
        return itemStack;
    }

    private static String color(String path) {
        return ChatColor.translateAlternateColorCodes('&', path);
    }
}
