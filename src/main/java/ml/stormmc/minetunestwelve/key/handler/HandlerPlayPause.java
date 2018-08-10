package ml.stormmc.minetunestwelve.key.handler;

import ml.stormmc.minetunestwelve.MineTunesTwelve;
import ml.stormmc.minetunestwelve.key.KeyHandler;
import net.minecraft.client.settings.KeyBinding;

public class HandlerPlayPause extends KeyHandler {
    boolean down = false;

    @Override
    public void keyDown(KeyBinding key) {
        if(!down && isInValidGui()) {
            if(MineTunesTwelve.musicPlayerThread != null)
                MineTunesTwelve.musicPlayerThread.pauseOrPlay();
            else {
                MineTunesTwelve.startMusicPlayerThread();
                MineTunesTwelve.musicPlayerThread.next();
            }
        }


        down = true;
    }

    @Override
    public void keyUp(KeyBinding key) {
        down = false;
    }
}
