package ml.stormmc.minetunestwelve.key;

import ml.stormmc.minetunestwelve.gui.GuiPlaylistManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;

public abstract class KeyHandler {
    public abstract void keyDown(KeyBinding key);

    public abstract void keyUp(KeyBinding key);

    public boolean isInValidGui() {
        Minecraft mc = Minecraft.getMinecraft();
        return mc.currentScreen == null || mc.currentScreen instanceof GuiPlaylistManager;
    }
}
