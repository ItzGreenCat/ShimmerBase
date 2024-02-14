package me.greencat.shimmer.mixins;

import me.greencat.src.ScreenManager;
import me.greencat.src.config.ConfigInstance;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.LinkedHashMap;

@Mixin(ScreenManager.class)
public interface ScreenManagerAccessor {
    @Accessor(value = "instances",remap = false)
    LinkedHashMap<String, ConfigInstance> getInstances();
}
