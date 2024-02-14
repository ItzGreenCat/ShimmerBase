package me.greencat.shimmer.mixins;

import me.greencat.shimmer.config.ModuleStatusConfig;
import me.greencat.shimmer.module.CatGirlManager;
import me.greencat.src.component.Component;
import me.greencat.src.component.ComponentContainer;
import me.greencat.src.component.config.BooleanComponent;
import me.greencat.src.config.ConfigInstance;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ConfigInstance.class)
public class MixinConfigInstance {
    @Redirect(method = "generateGui",at = @At(value = "INVOKE", target = "Lme/greencat/src/component/ComponentContainer;addComponent(Lme/greencat/src/component/Component;)V",ordinal = 1),remap = false)
    public void onCreateBooleanComponent(ComponentContainer instance, Component<Boolean> component){
        if(!component.name.contains("isEnable")){
            instance.addComponent(component);
        } else {
            BooleanComponent booleanComponent = new BooleanComponent(()->{
                boolean status = ModuleStatusConfig.config.get("general",component.name.split("_")[1],false, "").getBoolean();
                ModuleStatusConfig.config.load();
                return status;
            },(it) -> CatGirlManager.playTogether(component.name.split("_")[1],it));
            booleanComponent.name = component.name.split("_")[0];
            instance.addComponent(booleanComponent);
        }
    }
}
