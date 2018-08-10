package ml.stormmc.minetunestwelve;

import ml.stormmc.minetunestwelve.config.MTTConfig;
import ml.stormmc.minetunestwelve.key.KeyBindings;
import ml.stormmc.minetunestwelve.lib.LibMisc;
import ml.stormmc.minetunestwelve.player.HUDHandler;
import ml.stormmc.minetunestwelve.player.ThreadMusicPlayer;
import ml.stormmc.minetunestwelve.playlist.PlaylistList;
import ml.stormmc.minetunestwelve.playlist.ThreadPlaylistCreator;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.lang.management.ManagementFactory;

@Mod(modid = LibMisc.MOD_ID, name = LibMisc.MOD_NAME, version = LibMisc.VERSION, clientSideOnly = true)
public class MineTunesTwelve {

    public static final Logger logger = LogManager.getLogger(LibMisc.MOD_ID);
    public volatile static ThreadMusicPlayer musicPlayerThread;
    public volatile static ThreadPlaylistCreator playlistCreatorThread;

    public static boolean DEBUG_MODE = ManagementFactory.getRuntimeMXBean().getInputArguments().toString().indexOf("-agentlib:jdwp") > 0;


   @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent e) {
       logger.info("Starting up MineTunesTwelve!");
       KeyBindings.init();

       MinecraftForge.EVENT_BUS.register(new HUDHandler());

       MTTConfig.findCompoundAndLoad();
       PlaylistList.findCompoundAndLoad();
   }

    public static void startMusicPlayerThread() {
        musicPlayerThread = new ThreadMusicPlayer();
    }

    public static void startPlaylistCreatorThread(File file, String name) {
        playlistCreatorThread = new ThreadPlaylistCreator(file, name);
    }
}
