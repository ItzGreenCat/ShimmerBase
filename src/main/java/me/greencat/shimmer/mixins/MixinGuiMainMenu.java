package me.greencat.shimmer.mixins;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiYesNoCallback;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiMainMenu.class)
public class MixinGuiMainMenu extends GuiScreen implements GuiYesNoCallback {
    @Inject(
            method = "drawScreen",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/GuiScreen;drawScreen(IIF)V"
            ),
            cancellable = true)
    public void drawScreen(int mouseX, int mouseY, float partialTicks, CallbackInfo ci){
        ScaledResolution scaledResolution = new ScaledResolution(Minecraft.getMinecraft());
        Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("textures/ui/background.png"));
        Gui.drawModalRectWithCustomSizedTexture(0,0,0,0,scaledResolution.getScaledWidth(),scaledResolution.getScaledHeight(),scaledResolution.getScaledWidth(),scaledResolution.getScaledHeight());
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        int screenWidth = scaledResolution.getScaledWidth();
        int screenHeight = scaledResolution.getScaledHeight();
        float factorX = 30 * (1 + ((float) mouseX / screenWidth) / 0.8F);
        float factorY = screenHeight / 16.0F * (1 + ((float) mouseY / screenHeight) / 0.8F);
        int ScreenWidth = scaledResolution.getScaledWidth();
        int ScreenHeight = scaledResolution.getScaledHeight();
        int yCoord = (int) (ScreenHeight / 20 * 3.5D);
        int xCoord = ScreenWidth / 19;
        int height = (int) (ScreenHeight / 9.0D);
        Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("textures/ui/title.png"));
        Gui.drawModalRectWithCustomSizedTexture((int) (xCoord + factorX), (int) (yCoord + factorY),0,0,height * 5,height,height * 5,height);

        super.drawScreen(mouseX,mouseY,partialTicks);
        ci.cancel();
    }
}
