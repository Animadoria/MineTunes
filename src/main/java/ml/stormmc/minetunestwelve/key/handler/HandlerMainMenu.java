package ml.stormmc.minetunestwelve.key.handler;

import ml.stormmc.minetunestwelve.gui.GuiPlaylistManager;
import ml.stormmc.minetunestwelve.key.KeyHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;

public class HandlerMainMenu extends KeyHandler {
    @Override
    public void keyDown(KeyBinding key) {
        Minecraft mc = Minecraft.getMinecraft();
        if(mc.currentScreen == null)
            mc.displayGuiScreen(new GuiPlaylistManager());
    }

    @Override
    public void keyUp(KeyBinding key) {
        // NO-OP
    }
}
