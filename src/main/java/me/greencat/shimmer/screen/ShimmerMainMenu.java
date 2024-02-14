package me.greencat.shimmer.screen;

import me.greencat.shimmer.ShimmerClient;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ShimmerMainMenu {
    @SubscribeEvent
    public void onMenu(GuiScreenEvent.InitGuiEvent event){
        if(event.gui instanceof GuiMainMenu){
            List<GuiButton> removedButton = new ArrayList<>();
            for(GuiButton button : event.buttonList){
                if(button.id == 0 || button.id == 4 || button.id == 5 || button.id == 1 || button.id == 2 || button.id == 14 || button.id == 6){
                    removedButton.add(button);
                }
            }
            if(!removedButton.isEmpty()){
                for(GuiButton button : removedButton){
                    event.buttonList.remove(button);
                }
            }
            ScaledResolution scaledResolution = new ScaledResolution(Minecraft.getMinecraft());
            int screenWidth = scaledResolution.getScaledWidth();
            int screenHeight = scaledResolution.getScaledHeight();
            int MaxButtonY = (int) (screenHeight / 10 * 3.5D);
            int ButtonSpace = 10 / scaledResolution.getScaleFactor();
            int ButtonX = screenWidth / 19;
            int ButtonHeight = (int) (screenHeight / 19.0D);
            int counter = 0;
            GuiCustomButton SinglePlayerButton = new GuiCustomButton(1,ButtonX, MaxButtonY + counter * (ButtonHeight + ButtonSpace), ButtonHeight * 10, ButtonHeight, "SinglePlayer", "single");
            counter++;
            GuiCustomButton MultiPlayerButton = new GuiCustomButton(2, ButtonX, MaxButtonY + counter * (ButtonHeight + ButtonSpace), ButtonHeight * 10, ButtonHeight, "MultiPlayer", "multi");
            counter++;
            GuiCustomButton ModsButton = new GuiCustomButton(6,ButtonX, MaxButtonY + counter * (ButtonHeight + ButtonSpace), ButtonHeight * 10, ButtonHeight, "Mods", "mods");
            counter++;
            GuiCustomButton LanguageButton = new GuiCustomButton(5,ButtonX, MaxButtonY + counter * (ButtonHeight + ButtonSpace), ButtonHeight * 10, ButtonHeight, "Language", "language");
            counter++;
            GuiCustomButton SettingsButton = new GuiCustomButton(0,ButtonX, MaxButtonY + counter * (ButtonHeight + ButtonSpace), ButtonHeight * 10, ButtonHeight, "Settings", "settings");
            counter++;
            GuiCustomButton QuitButton = new GuiCustomButton(4,ButtonX, MaxButtonY + counter * (ButtonHeight + ButtonSpace), ButtonHeight * 10, ButtonHeight, "Quit", "quit");

            event.buttonList.add(SinglePlayerButton);
            event.buttonList.add(MultiPlayerButton);
            event.buttonList.add(ModsButton);
            event.buttonList.add(LanguageButton);
            event.buttonList.add(SettingsButton);
            event.buttonList.add(QuitButton);
        }
    }
    public static class GuiCustomButton extends GuiButton{
        final String iconLocation;
        static final int coveredColor = new Color(171, 171, 171,120).getRGB();
        static final int uncoveredColor = new Color(171, 171, 171,60).getRGB();
        public GuiCustomButton(int id, int x, int y, int w, int h,String str,String Icon) {
            super(id,x,y,w,h,str);
            iconLocation = Icon;
        }
        public void drawButton(Minecraft mc, int mouseX, int mouseY) {
            if (this.visible) {
                mc.getTextureManager().bindTexture(new ResourceLocation(ShimmerClient.MODID,"menu/" + iconLocation + ".png"));
                ScaledResolution scaledResolution = new ScaledResolution(Minecraft.getMinecraft());
                int screenWidth = scaledResolution.getScaledWidth();
                int screenHeight = scaledResolution.getScaledHeight();
                float factorX = 30 * (1 + ((float) mouseX / screenWidth) / 0.8F);
                float factorY = screenHeight / 16.0F * (1 + ((float) mouseY / screenHeight) / 0.8F);
                GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
                boolean isCoveredByMouse = mouseX >= this.xPosition + factorX && mouseY >= this.yPosition + factorY && mouseX < this.xPosition + factorX + this.width && mouseY < this.yPosition + factorY + this.height;
                if(!isCoveredByMouse) {
                    Gui.drawRect((int) (this.xPosition + factorX), (int) (this.yPosition + factorY), (int) (this.xPosition + factorX + this.width), (int) (this.yPosition + factorY + this.height),uncoveredColor);
                    GlStateManager.color(0.9F, 0.9F, 0.9F, 1.0F);
                    Gui.drawModalRectWithCustomSizedTexture((int) (this.xPosition + factorX), (int) (this.yPosition + factorY), 0, 0,this.width,this.height,this.width,this.height);
                    GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
                } else {
                    Gui.drawRect((int) (this.xPosition + factorX), (int) (this.yPosition + factorY), (int) (this.xPosition + factorX + this.width), (int) (this.yPosition + factorY + this.height),coveredColor);
                    GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
                    Gui.drawModalRectWithCustomSizedTexture((int) (this.xPosition + factorX), (int) (this.yPosition + factorY), 0, 0,this.width,this.height,this.width,this.height);
                }
            }
        }
        public boolean mousePressed(Minecraft mc, int mouseX, int mouseY){
            ScaledResolution scaledResolution = new ScaledResolution(Minecraft.getMinecraft());
            int screenWidth = scaledResolution.getScaledWidth();
            int screenHeight = scaledResolution.getScaledHeight();
            float factorX = 30 * (1 + ((float) mouseX / screenWidth) / 0.8F);
            float factorY = screenHeight / 16.0F * (1 + ((float) mouseY / screenHeight) / 0.8F);
            return this.enabled && this.visible && mouseX >= this.xPosition + factorX && mouseY >= this.yPosition + factorY && mouseX < this.xPosition + factorX + this.width && mouseY < this.yPosition + factorY + this.height;
        }
    }
}
