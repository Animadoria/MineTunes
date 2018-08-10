package ml.stormmc.minetunestwelve.key;

import ml.stormmc.minetunestwelve.key.handler.HandlerMainMenu;
import ml.stormmc.minetunestwelve.key.handler.HandlerPlayPause;
import ml.stormmc.minetunestwelve.key.handler.HandlerVolume;
import ml.stormmc.minetunestwelve.lib.LibKeys;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.FMLCommonHandler;
import org.lwjgl.input.Keyboard;


import java.util.HashMap;

public final class KeyBindings {
    public static HashMap<KeyBinding, KeyHandler> handlers = new HashMap();

    public static KeyBinding keyMenu;
    public static KeyBinding keyPlayPause;
    public static KeyBinding keyPlus;
    public static KeyBinding keyMinus;

    public static void init() {
        keyMenu = registerKey(new KeyBinding(LibKeys.MENU, Keyboard.KEY_HOME, LibKeys.KEY_CATEGORY), new HandlerMainMenu());
        keyPlayPause = registerKey(new KeyBinding(LibKeys.PLAY_PAUSE, Keyboard.KEY_END, LibKeys.KEY_CATEGORY), new HandlerPlayPause());
        keyPlus = registerKey(new KeyBinding(LibKeys.PLUS, Keyboard.KEY_NEXT, LibKeys.KEY_CATEGORY), new HandlerVolume(true));
        keyMinus = registerKey(new KeyBinding(LibKeys.MINUS, Keyboard.KEY_PRIOR, LibKeys.KEY_CATEGORY), new HandlerVolume(false));

        FMLCommonHandler.instance().bus().register(KeySubscriber.instance);
    }

    private static KeyBinding registerKey(KeyBinding key, KeyHandler handler) {
        ClientRegistry.registerKeyBinding(key);
        handlers.put(key, handler);
        return key;
    }
}
