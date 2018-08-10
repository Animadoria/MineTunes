package ml.stormmc.minetunestwelve.player.chooser.action;

import ml.stormmc.minetunestwelve.gui.GuiDevTools;

import java.io.File;

public class ActionDebug implements ISelectorAction {
    public static final ActionDebug instance = new ActionDebug();

    @Override
    public void select(File file) {
        GuiDevTools.debugLog("File: " + file.getAbsolutePath());
    }
}
