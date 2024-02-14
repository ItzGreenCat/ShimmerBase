package me.greencat.shimmer.config;

import me.greencat.shimmer.module.CatGirlManager;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.config.Configuration;

import java.io.File;

public class ModuleStatusConfig {
    public static Configuration config = new Configuration(new File(Minecraft.getMinecraft().mcDataDir,"Shimmer" + File.pathSeparator + "CatGirl.cfg"));
    public static void add(String key){
        config.get("general", key, false, "").set(true);
        config.save();
        config.load();

    }
    public static void remove(String key) {
        config.get("general", key, false, "").set(false);
        config.save();
        config.load();
    }

    public static void load() {
        for(String name : CatGirlManager.catGirlHome.keySet()) {
            boolean status = config.get("general",name,false, "").getBoolean();
            config.load();
            CatGirlManager.catGirlStatus.put(name,status);
        }
    }
}
