package ml.stormmc.minetunestwelve.config;

import ml.stormmc.minetunestwelve.gui.GuiDevTools;
import ml.stormmc.minetunestwelve.lib.LibMisc;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class CacheHelper {
    public static File getCacheFile(String filename) throws IOException {
        File loc = new File(".");
        File cacheFile = new File(loc, LibMisc.MOD_ID + "/" + filename);

        if(!cacheFile.exists()) {
            cacheFile.getParentFile().mkdirs();
            cacheFile.createNewFile();
        }

        return cacheFile;
    }

    public static NBTTagCompound getCacheCompound(File file) {
        if(file == null)
            throw new RuntimeException("No cache file!");

        try {
            NBTTagCompound cmp = CompressedStreamTools.readCompressed(new FileInputStream(file));
            return cmp;
        } catch(IOException e) {
            NBTTagCompound cmp = new NBTTagCompound();

            try {
                CompressedStreamTools.writeCompressed(cmp, new FileOutputStream(file));
                return getCacheCompound(file);
            } catch (IOException e1) {
                GuiDevTools.logThrowable(e1);
                return null;
            }
        }
    }

    public static void injectNBTToFile(File file, NBTTagCompound cmp) {
        try {
            CompressedStreamTools.writeCompressed(cmp, new FileOutputStream(file));
        } catch(IOException e) {
            GuiDevTools.logThrowable(e);
        }
    }
}
