package ml.stormmc.minetunestwelve.playlist;

import ml.stormmc.minetunestwelve.MineTunesTwelve;
import ml.stormmc.minetunestwelve.gui.GuiDevTools;
import ml.stormmc.minetunestwelve.playlist.provider.IProviderStateCallback;

import java.io.File;

public class ThreadPlaylistCreator extends Thread implements IProviderStateCallback {
    private volatile String state;

    File file;
    String name;

    public ThreadPlaylistCreator(File file, String name) {
        this.file = file;
        this.name = name;

        setDaemon(true);
        setName("mineTunes Playlist Generator Thread");
        start();
    }

    @Override
    public void run() {
        while(PlaylistList.playlistNames.contains(name))
            name += "_";

        GuiDevTools.debugLog("Building Playlist with name " + name);

        Playlist playlist = Playlist.build(file, this);
        if(playlist != null && playlist.metadataList.size() > 0) {
            GuiDevTools.debugLog("Valid Playlist, adding to set");
            GuiDevTools.debugLog("MP3 Count: " + playlist.metadataList.size());

            PlaylistList.playlistNames.add(name);
            PlaylistList.playlists.put(name, playlist);
        } else GuiDevTools.debugLog("Not adding Playlist to set, null or empty.");

        PlaylistList.findCompoundAndWrite();

        MineTunesTwelve.playlistCreatorThread = null;
    }

    @Override
    public void setState(String state) {
        this.state = state;
        GuiDevTools.debugLog(state);
    }

    public synchronized String getProgressState() {
        return state;
    }
}
