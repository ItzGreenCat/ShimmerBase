package me.greencat.shimmer;

import me.greencat.shimmer.config.ModuleStatusConfig;
import me.greencat.shimmer.keybind.ConfigUIKeyBind;
import me.greencat.shimmer.module.CatGirlManager;
import me.greencat.shimmer.module.test.DummyModule;
import me.greencat.shimmer.screen.ShimmerMainMenu;
import me.greencat.src.ScreenManager;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.io.File;

@Mod(modid = ShimmerClient.MODID, version = ShimmerClient.VERSION)
public class ShimmerClient {
    public static final String MODID = "shimmer";
    public static final String VERSION = "1.0";
    public static ScreenManager config = new ScreenManager();
    public static File modDataDir = new File(Minecraft.getMinecraft().mcDataDir,"Shimmer");
    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        if(!modDataDir.exists()){
            modDataDir.mkdir();
        }
    }
    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        ClientRegistry.registerKeyBinding(ConfigUIKeyBind.keyBinding);

        MinecraftForge.EVENT_BUS.register(new ShimmerMainMenu());
        MinecraftForge.EVENT_BUS.register(new ConfigUIKeyBind());
    }
    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        CatGirlManager.adopt(DummyModule.class);

        config.postInitialization();
        ModuleStatusConfig.load();
    }
}
