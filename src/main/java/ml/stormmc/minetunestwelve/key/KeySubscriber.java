package ml.stormmc.minetunestwelve.key;

import ml.stormmc.minetunestwelve.MineTunesTwelve;
import ml.stormmc.minetunestwelve.player.HUDHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class KeySubscriber {
    public static final KeySubscriber instance = new KeySubscriber();

    public static float delta = 0;
    float lastPartTicks = 0;

    @SubscribeEvent
    public void playerTick(TickEvent.RenderTickEvent event) {
        delta = event.renderTickTime - lastPartTicks;
        if(delta < 0)
            delta = event.renderTickTime;
        lastPartTicks = event.renderTickTime;

        if(Minecraft.getMinecraft().player != null) {
            if(event.phase == TickEvent.Phase.START) {
                for(KeyBinding key : KeyBindings.handlers.keySet()) {
                    KeyHandler handler = KeyBindings.handlers.get(key);
                    if(key.isKeyDown())
                        handler.keyDown(key);
                    else handler.keyUp(key);
                }
            } else HUDHandler.showVolume = false;
        } else if(MineTunesTwelve.musicPlayerThread != null) {
            MineTunesTwelve.musicPlayerThread.forceKill();
            MineTunesTwelve.startMusicPlayerThread();
        }
    }
}
