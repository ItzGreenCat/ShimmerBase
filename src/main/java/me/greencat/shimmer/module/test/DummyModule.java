package me.greencat.shimmer.module.test;

import me.greencat.shimmer.module.CatGirl;
import me.greencat.src.utils.ClassCategory;
import me.greencat.src.utils.ConfigEntry;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@ClassCategory("Test")
public class DummyModule extends CatGirl {
    @ConfigEntry
    public static String functionTest = "测试";

    public DummyModule() {
        super("DummyModule");
    }


    @SubscribeEvent
    public void onChatReceive(ClientChatReceivedEvent event){
        if(!getStatus()){
            return;
        }
        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(functionTest));
    }
}
