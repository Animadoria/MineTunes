package ml.stormmc.minetunestwelve.gui;

import ml.stormmc.minetunestwelve.MineTunesTwelve;
import ml.stormmc.minetunestwelve.player.chooser.action.ActionPlayMp3;
import ml.stormmc.minetunestwelve.playlist.MP3Metadata;
import ml.stormmc.minetunestwelve.playlist.Playlist;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.Tessellator;
import org.lwjgl.opengl.GL11;

public class GuiMusicSlot extends GuiScrollingListMTT {

    GuiPlaylistManager parent;

    public GuiMusicSlot(GuiPlaylistManager parent) {
        super(parent.width - 200, parent.height - parent.getTopSize(), parent.getTopSize(), 200, 40);
        this.parent = parent;
    }

    @Override
    protected int getSize() {
        return parent.visibleSongs.size();
    }

    @Override
    protected void elementClicked(int i, boolean doubleclick) {
        MP3Metadata metadata = parent.visibleSongs.get(i);

        if(doubleclick) {
            int index = parent.getSelectedPlaylistIndex();
            Playlist playlist = parent.getSelectedPlaylist();
            if(playlist != null) {
                ActionPlayMp3.instance.play(metadata);
                parent.selectCurrentPlaylist(index, i);
            }
        }

        parent.selectSong(metadata);
    }

    @Override
    protected boolean isSelected(int i) {
        return parent.visibleSongs.get(i) == parent.getSelectedSong();
    }

    @Override
    protected void drawSlot(int i, int j, int k, int l, Tessellator tessellator) {
        MP3Metadata metadata = parent.visibleSongs.get(i);
        if(metadata != null) {
            FontRenderer font = Minecraft.getMinecraft().fontRenderer;
            boolean selected = isSelected(i);

            int s = j + 20 - listWidth;

            boolean playing = false;
            float playedFraction = 0;
            boolean paused = false;
            if(MineTunesTwelve.musicPlayerThread != null) {
                MP3Metadata playingMeta = MineTunesTwelve.musicPlayerThread.getPlayingMetadata();
                if(playingMeta != null && playingMeta.isEqualFile(metadata)) {
                    playing = true;
                    playedFraction = MineTunesTwelve.musicPlayerThread.getFractionPlayed();
                    paused = MineTunesTwelve.musicPlayerThread.isPaused();
                }
            }

            int colorMain = playing ? 0xAAFFFF : 0xFFFFFF;
            int colorSub = playing ? 0x00AAAA : 0xAAAAAA;
            int colorLength = playing ? paused ? 0xFF5555 : 0x55FF55 : 0x555555;
            int colorBg = 0x44000000;

            if(!selected)
                parent.drawBox(s - 6, k + 2, listWidth + 6, 32);

            font.drawStringWithShadow(metadata.title, s, k + 3, colorMain);
            font.drawStringWithShadow(metadata.artist, s + 4, k + 13, colorSub);
            font.drawStringWithShadow(metadata.album, s + 4, k + 23, colorSub);

            GL11.glScalef(2F, 2F, 2F);
            String length = metadata.length;

            if(playing)
                length = MP3Metadata.getLengthStr((int) (metadata.lengthMs * playedFraction)) + "/" + length;

            font.drawString(length, (j - 2) / 2 - font.getStringWidth(length), (k + 6) / 2, colorLength);
            GL11.glScalef(0.5F, 0.5F, 0.5F);
        }
    }
}
