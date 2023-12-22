import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

/*
 * This class manages, stores, and plays audio content such as songs, podcasts and audiobooks.
 */

public class Library {
    private ArrayList<Song> songs = new ArrayList();
    private ArrayList<AudioBook> audiobooks = new ArrayList();
    private ArrayList<Playlist> playlists = new ArrayList();

    public Library()
    {
        songs = new ArrayList<Song>();
        audiobooks = new ArrayList<AudioBook>();
        playlists = new ArrayList<Playlist>();
    }

    public void download(int fromIndex, int toIndex)
    {
        for (int i = fromIndex; i <= toIndex; i++) {
            AudioContent content = AudioContentStore.getAllContents().get(i-1);

            if (content.getType().equalsIgnoreCase("SONG") && !songs.contains(content))
            {
                songs.add((Song) content);
                System.out.println(content.getType() + " " + content.getTitle() + " Added to Library");

            }
            else if (content.getType().equalsIgnoreCase("AUDIOBOOK") && !audiobooks.contains(content))
            {
                audiobooks.add((AudioBook)content);
                System.out.println(content.getType() + " " + content.getTitle() + " Added to Library");
            }
            else
            {
                System.out.println(content.getType() + " " + content.getTitle() + " already downloaded");
            }
        }
    }

    /**
     * downloads all song contents of either an artist or genre
     *
     * @param searchItem String containing either a specific artist or genre to download
     * @param searchType String indicating whether an artist or genre is being downloaded
     */
    public void downloadArtistGenre(String searchItem, String searchType)
    {
        ArrayList<Integer> indexes;
        if (searchType.equalsIgnoreCase("Artist") && AudioContentStore.getSearchArtist().containsKey(searchItem.toLowerCase()))
        {
            indexes = AudioContentStore.getSearchArtist().get(searchItem.toLowerCase());
        }
        else if (searchType.equalsIgnoreCase("Genre") && AudioContentStore.getSearchGenre().containsKey(searchItem.toUpperCase()))
        {
            indexes = AudioContentStore.getSearchGenre().get(searchItem.toUpperCase());
        }
        else
        {
            throw new AudioContentNotFoundException("No matches for " + searchItem);
        }

        // downloads content given the indexes
        for (Integer i : indexes)
        {
            AudioContent content = AudioContentStore.getAllContents().get(i-1);
            // matches content type to correct content playlist and that content is not already downloaded
            if (content.getType().equalsIgnoreCase("SONG") && !songs.contains(content))
            {
                songs.add((Song) content);
                System.out.println(content.getType() + " " + content.getTitle() + " Added to Library");
            }
            else
            {
                System.out.println(content.getType() + " " + content.getTitle() + " already downloaded");
            }
        }
    }

    public void listAllSongs()
    {
        for(int i = 0; i < this.songs.size(); ++i)
        {
            int index = i + 1;
            System.out.print("" + index + ". ");
            songs.get(i).printInfo();
            System.out.println();
        }
    }

    public void listAllAudioBooks()
    {
        for(int i = 0; i < this.audiobooks.size(); ++i)
        {
            int index = i + 1;
            System.out.print("" + index + ". ");
            audiobooks.get(i).printInfo();
            System.out.println();
        }
    }

    public void listAllPlaylists()
    {
        for(int i = 0; i < this.playlists.size(); ++i)
        {
            int index = i + 1;
            System.out.print("" + index + ". ");
            System.out.println(playlists.get(i).getTitle());
        }
    }

    public void listAllArtists()
    {
        ArrayList<String> artistList = new ArrayList();

        for(int i = 0; i < songs.size(); ++i) {
            if (!artistList.contains(songs.get(i).getArtist())) // if first occurrence of artist
            {
                artistList.add(songs.get(i).getArtist());
                System.out.print(i + 1 + ". "); 
                System.out.print(artistList.get(i));
                System.out.println();
            }
        }
    }

    // deletes song from the library and all playlists 
    public void deleteSong(int index)
    {
        if (index >= 1 && index <= songs.size())
        {
            String temp = (songs.get(index - 1)).getTitle();  
            this.songs.remove(index - 1);  
            
            // deletes song from playlists
            for (Playlist playlist : playlists) {
                for (int j = 0; j < playlist.getContent().size(); j++) {
                    if (playlist.getContent().get(j).getTitle().equals(temp)) {
                        playlist.getContent().remove(j);
                    }
                }
            }
        }
        else
        {
            throw new AudioContentNotFoundException("Song Not Found");
        }
    }

    public void sortSongsByYear()
    {
        Collections.sort(songs, new SongYearComparator());
    }

    private class SongYearComparator implements Comparator<Song>
    {
        @Override
        public int compare(Song song1, Song song2)
        {
            return song1.getYear() - song2.getYear();
        }
    }

    public void sortSongsByLength()
    {
        Collections.sort(songs, new SongLengthComparator());
    }

    private class SongLengthComparator implements Comparator<Song>
    {
        @Override
        public int compare(Song song1, Song song2)
        {
            return song1.getLength() - song2.getLength();
        }
    }

    public void sortSongsByName()
    {
        Collections.sort(songs);
    }

    /*
     * Play Content
     */

    public void playSong(int index)
    {
        if (index >= 1 && index <= this.songs.size())
        {
            songs.get(index - 1).play();
        }
        else
        {
            throw new AudioContentNotFoundException("Song Not Found");
        }
    }

    public void playAudioBook(int index, int chapter)
    {
        if (index >= 1 && index <= audiobooks.size())
        {
            if (chapter >= 1 && chapter <= audiobooks.get(index - 1).getNumberOfChapters())
            {
                audiobooks.get(index - 1).selectChapter(chapter);
                audiobooks.get(index - 1).play();
            }
            else
            {
                throw new AudioContentNotFoundException("Chapter Not Found");
            }
        }
        else
        {
            throw new AudioContentNotFoundException("Audiobook Not Found");
        }
    }

    public void printAudioBookTOC(int index)
    {
        if (index >= 1 && index <= audiobooks.size())
        {
            audiobooks.get(index - 1).printTOC();
        }
        else
        {
            throw new AudioContentNotFoundException("Audiobook Not Found");
        }
    }

    /*
     * Playlist Related Methods
     */

    
    public void makePlaylist(String title) {
        for (Playlist p : playlists)  // checks if playlist title already exists
        {
            if (p.getTitle().equalsIgnoreCase(title))
            {
                throw new AudioContentNotFoundException("Playlist " + title + "Already Exists");
            }
        }

        Playlist newPlaylist = new Playlist(title);  // creates new playlist
        playlists.add(newPlaylist);
    }

    public void printPlaylist(String title) {
        for (Playlist p : playlists) 
        {
            if (p.getTitle().equalsIgnoreCase(title))
            {
                p.printContents();
                return;
            }
        }
        throw new AudioContentNotFoundException("Playlist Not Found");
    }

    public void playPlaylist(String playlistTitle) {
        for (Playlist p : playlists) 
        {
            if (p.getTitle().equalsIgnoreCase(playlistTitle))
            {
                p.playAll();
                return;
            }
        }
        throw new AudioContentNotFoundException("Playlist Not Found");
    }

    public void playPlaylist(String playlistTitle, int indexInPL) {
        for (Playlist p : playlists) 
        {
            if (p.getTitle().equalsIgnoreCase(playlistTitle) && p.contains(indexInPL))
            {
                p.play(indexInPL-1);
                return;
            }
        }
        throw new AudioContentNotFoundException("Playlist Not Found");
    }

    public void addContentToPlaylist(String type, int index, String playlistTitle) {
        int playlistIndex = -1;
        for (int i = 0; i < playlists.size(); i++)  
        {
            if (playlists.get(i).getTitle().equalsIgnoreCase(playlistTitle))
            {
                playlistIndex = i;
            }
        }

        if (playlistIndex == -1) {
            throw new AudioContentNotFoundException("Playlist Not Found");
        }
        else
        {
            if (type.equalsIgnoreCase("SONG") && index <= songs.size() && index >= 1)
            {
                playlists.get(playlistIndex).addContent(songs.get(index - 1));
            }
            else if (type.equalsIgnoreCase("AUDIOBOOK") && index <= audiobooks.size() && index >= 1)
            {
                playlists.get(playlistIndex).addContent(audiobooks.get(index - 1));
            }
            else
            {
                throw new AudioContentNotFoundException("Library Content Not Found");
            }
        }
    }

    public void delContentFromPlaylist(int index, String title) {
        for (Playlist p : playlists)  
        {
            if (p.getTitle().equalsIgnoreCase(title) && p.contains(index))
            {
                p.deleteContent(index);
                return;
            }
        }
        throw new AudioContentNotFoundException("Playlist Not Found");
    }
}


class AudioContentNotFoundException extends RuntimeException {
    public AudioContentNotFoundException(String message) {
        super(message);
    }
}
