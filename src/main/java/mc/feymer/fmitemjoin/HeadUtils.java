package mc.feymer.fmitemjoin;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

public class HeadUtils {
    public static ItemStack getSkull(String url ) {
        ItemStack itemStack = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
        if (url == null || url.isEmpty())
            return itemStack;

        SkullMeta skullMeta = (SkullMeta) itemStack.getItemMeta();
        GameProfile gameProfile = new GameProfile(UUID.randomUUID(), null);
        byte [] skullbyte = Base64.getEncoder().encode(String.format("{textures:{SKIN:{url:\"%s\"}}}", "http://textures.minecraft.net/texture/" + url ).getBytes());
        gameProfile.getProperties().put("textures", new Property("textures", new String(skullbyte)));
        Field field = null;
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
        List <String> lores = new ArrayList<>();
        lores.add("§fЕбой!!".replace("&", "§"));
        lores.add("§fЕбой!!".replace("&", "§"));
        lores.add("§fЕбой!!".replace("&", "§"));
        skullMeta.setDisplayName("§fГолова нахуй");
        skullMeta.setLore(lores);
        itemStack.setItemMeta(skullMeta);
        return itemStack;
    }
}