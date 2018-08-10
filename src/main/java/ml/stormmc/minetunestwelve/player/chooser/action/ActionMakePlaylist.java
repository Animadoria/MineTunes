package ml.stormmc.minetunestwelve.player.chooser.action;

import ml.stormmc.minetunestwelve.playlist.PlaylistList;

import java.io.File;

public class ActionMakePlaylist implements ISelectorAction {

    public static final ActionMakePlaylist instance = new ActionMakePlaylist();
    String name;

    @Override
    public void select(File file) {
        PlaylistList.loadPlaylist(file, name);
        ActionDebug.instance.select(file);
    }

    public ISelectorAction withName(String name) {
        this.name = name;
        return this;
    }
}
