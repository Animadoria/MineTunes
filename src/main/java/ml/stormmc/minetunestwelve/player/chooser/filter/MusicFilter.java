package ml.stormmc.minetunestwelve.player.chooser.filter;

public class MusicFilter extends Filter {
    public static final MusicFilter instance = new MusicFilter();

    public MusicFilter() {
        super("minetunes.player.chooser.filterMusic", ".mp3");
    }
}
