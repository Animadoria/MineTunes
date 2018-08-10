package ml.stormmc.minetunestwelve.player.chooser.filter;

import net.minecraft.client.resources.I18n;

import javax.swing.filechooser.FileFilter;
import java.io.File;

public class Filter extends FileFilter implements java.io.FileFilter {

    String desc;
    String accept;

    public Filter(String name, String accept) {
        desc = I18n.format(name);
        this.accept = accept;
    }

    @Override
    public boolean accept(File f) {
        return f.isDirectory() || f.getName().endsWith(accept);
    }

    @Override
    public String getDescription() {
        return desc;
    }

}