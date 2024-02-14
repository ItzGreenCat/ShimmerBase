package me.greencat.shimmer.keybind;

import me.greencat.shimmer.ShimmerClient;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.lwjgl.input.Keyboard;

public class ConfigUIKeyBind {
    public static KeyBinding keyBinding = new KeyBinding("Open ClickGUI", Keyboard.KEY_RSHIFT,"Shimmer");

    @SubscribeEvent
    public void onKey(InputEvent.KeyInputEvent event){
        if(keyBinding.isPressed()){
            ShimmerClient.config.display();
        }
    }
}
