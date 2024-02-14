package me.greencat.shimmer.mixins;

import me.greencat.shimmer.screen.ShimmerSplash;
import net.minecraftforge.fml.client.SplashProgress;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SplashProgress.class)
public class MixinSplashScreen {
    @Inject(method = "start",at = @At("HEAD"), cancellable = true,remap = false)
    private static void onStart(CallbackInfo ci){
        ShimmerSplash.start();
        ci.cancel();
    }
    @Inject(method = "finish",at = @At("HEAD"), cancellable = true,remap = false)
    private static void onFinish(CallbackInfo ci){
        ShimmerSplash.finish();
        ci.cancel();
    }
    @Inject(method = "drawVanillaScreen",at = @At("HEAD"), cancellable = true,remap = false)
    private static void onDrawVanillaScreen(CallbackInfo ci){
        ci.cancel();
    }
}
