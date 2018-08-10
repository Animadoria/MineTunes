package ml.stormmc.minetunestwelve.key.handler;

import ml.stormmc.minetunestwelve.MineTunesTwelve;
import ml.stormmc.minetunestwelve.key.KeyHandler;
import ml.stormmc.minetunestwelve.key.KeySubscriber;
import ml.stormmc.minetunestwelve.player.HUDHandler;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.settings.KeyBinding;

public class HandlerVolume extends KeyHandler {

    boolean down = false;

    boolean positive = false;

    public HandlerVolume(boolean positive) {
        this.positive = positive;
    }

    @Override
    public void keyDown(KeyBinding key) {
        if(isInValidGui() && MineTunesTwelve.musicPlayerThread != null)
            if(GuiScreen.isCtrlKeyDown()) {
                if(!down) {
                    if(positive)
                        MineTunesTwelve.musicPlayerThread.prev();
                    else MineTunesTwelve.musicPlayerThread.next();
                }
            } else {
                float gainVal = 0.5F * KeySubscriber.delta;
                MineTunesTwelve.musicPlayerThread.addGain(positive ? -gainVal : gainVal);
                HUDHandler.showVolume = true;
            }

        down = true;
    }

    @Override
    public void keyUp(KeyBinding key) {
        down = false;
    }
}
