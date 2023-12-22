import java.util.Scanner;

public class MyAudioUI
{
    public static void main(String[] args)
    {
        AudioContentStore store = new AudioContentStore();
        Library mylibrary = new Library();
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            System.out.print(">");
            try {
                String action = scanner.nextLine();

                if (action == null || action.equals(""))
                {
                    System.out.print("\n>");
                    continue;
                }
                else if (action.equalsIgnoreCase("Q") || action.equalsIgnoreCase("QUIT"))
                {
                    return;
                }
                else if (action.equalsIgnoreCase("STORE"))
                {
                    store.listAll();
                }
                else if (action.equalsIgnoreCase("SONGS"))
                {
                    mylibrary.listAllSongs();
                }
                else if (action.equalsIgnoreCase("BOOKS"))
                {
                    mylibrary.listAllAudioBooks();
                }
                else if (action.equalsIgnoreCase("ARTISTS"))
                {
                    mylibrary.listAllArtists();
                }
                else if (action.equalsIgnoreCase("PLAYLISTS"))
                {
                    mylibrary.listAllPlaylists();
                }
                else if (action.equalsIgnoreCase("DOWNLOAD"))
                {
                    int fromIndex = 0;
                    int toIndex = 0;

                    System.out.print("From Store Content #: ");
                    if (scanner.hasNextInt()) {
                        fromIndex = scanner.nextInt();
                        scanner.nextLine();
                    }

                    System.out.print("To Store Content #: ");
                    if (scanner.hasNextInt()) {
                        toIndex = scanner.nextInt();
                        scanner.nextLine();
                    }

                    if (fromIndex < 0 || fromIndex > store.getContentSize() || toIndex < 0 || toIndex > store.getContentSize())
                    {
                        System.out.println("Content Not Found in Store");
                    }
                    else
                    {
                        mylibrary.download(fromIndex, toIndex);
                    }
                }
                else if (action.equalsIgnoreCase("PLAYSONG"))
                {
                    int index = 0;
                    System.out.print("Song Number: ");
                    if (scanner.hasNextInt())
                    {
                        index = scanner.nextInt();
                        scanner.nextLine(); 
                    }
                    mylibrary.playSong(index);
                }
                else if (action.equalsIgnoreCase("BOOKTOC"))
                {
                    int index = 0;
                    System.out.print("Audio Book Number: ");
                    if (scanner.hasNextInt())
                    {
                        index = scanner.nextInt();
                        scanner.nextLine();
                    }

                    mylibrary.printAudioBookTOC(index);
                }
                else if (action.equalsIgnoreCase("PLAYBOOK"))
                {
                    int index = 0;
                    System.out.print("Audio Book Number: ");
                    if (scanner.hasNextInt())
                    {
                        index = scanner.nextInt();
                        scanner.nextLine(); 
                    }

                    int chapter = 0;
                    System.out.print("Chapter: ");
                    if (scanner.hasNextInt())
                    {
                        chapter = scanner.nextInt();
                        scanner.nextLine(); 
                    }

                    mylibrary.playAudioBook(index, chapter);
                }
                else if (action.equalsIgnoreCase("PLAYALLPL"))
                {
                    System.out.print("Playlist Title: ");
                    String title = scanner.nextLine();

                    while (title == null || title.equals(""))
                    {
                        System.out.print("Playlist Title: ");
                        title = scanner.nextLine();
                    }

                    mylibrary.playPlaylist(title);
                }
                else if (action.equalsIgnoreCase("PLAYPL"))
                {
                    System.out.print("Playlist Title: ");
                    String title = scanner.nextLine();
                    while (title == null || title.equals(""))
                    {
                        System.out.print("Playlist Title: ");
                    }

                    int index = 0;
                    System.out.print("Index #: ");
                    if (scanner.hasNextInt())
                    {
                        index = scanner.nextInt();
                        scanner.nextLine(); 
                    }

                    mylibrary.playPlaylist(title, index);
                }
                else if (action.equalsIgnoreCase("DELSONG"))
                {
                    int index = 0;
                    System.out.print("Library Song #: ");
                    if (scanner.hasNextInt())
                    {
                        index = scanner.nextInt();
                        scanner.nextLine(); 
                    }

                    mylibrary.deleteSong(index);
                }
                else if (action.equalsIgnoreCase("MAKEPL"))
                {
                    System.out.print("Playlist Title: ");
                    String title = scanner.nextLine();

                    while (title == null || title.equals(""))
                    {
                        System.out.print("Playlist Title: ");
                    }

                    mylibrary.makePlaylist(title);
                }
                else if (action.equalsIgnoreCase("PRINTPL")) 
                {
                    System.out.print("Playlist Title: ");
                    String title = scanner.nextLine();

                    while (title == null || title.equals("")) {
                        System.out.print("Playlist Title: ");
                    }

                    mylibrary.printPlaylist(title);
                }
                else if (action.equalsIgnoreCase("ADDTOPL"))
                {
                    System.out.print("Playlist Title: ");
                    String playlist = scanner.nextLine();
                    while (playlist == null || playlist.equals(""))
                    {
                        System.out.print("Playlist Title: ");
                    }

                    System.out.print("Content Type [SONG, AUDIOBOOK]: ");
                    String type = scanner.nextLine();
                    while (type == null || type.equals(""))
                    {
                        System.out.print("Content Type: ");
                        type = scanner.nextLine();
                    }

                    int index = 0;
                    System.out.print("Library Content #: ");
                    if (scanner.hasNextInt())
                    {
                        index = scanner.nextInt();
                        scanner.nextLine(); 
                    }

                    mylibrary.addContentToPlaylist(type, index, playlist);
                }
                else if (action.equalsIgnoreCase("DELFROMPL"))
                {
                    System.out.print("Playlist Title: ");
                    String playlist = scanner.nextLine();
                    while (playlist == null || playlist.equals(""))
                    {
                        System.out.print("Playlist Title: ");
                        playlist = scanner.nextLine();
                    }

                    int index = 0;
                    System.out.print("Playlist Content #: ");
                    if (scanner.hasNextInt())
                    {
                        index = scanner.nextInt();
                        scanner.nextLine(); 
                    }

                    mylibrary.delContentFromPlaylist(index, playlist);
                }
                else if (action.equalsIgnoreCase("SORTBYYEAR"))
                {
                    mylibrary.sortSongsByYear();
                }
                else if (action.equalsIgnoreCase("SORTBYNAME")) 
                {
                    mylibrary.sortSongsByName();
                }
                else if (action.equalsIgnoreCase("SORTBYLENGTH")) 
                {
                    mylibrary.sortSongsByLength();
                }
                else if (action.equalsIgnoreCase("SEARCH"))
                {
                    System.out.print("Title: ");
                    String title = scanner.nextLine();
                    while (title == null || title.equals(""))
                    {
                        System.out.print("Title: ");
                        title = scanner.nextLine();
                    }

                    store.searchTitle(title);
                }
                else if (action.equalsIgnoreCase("SEARCHA")) 
                {
                    System.out.print("Artist: ");
                    String artist = scanner.nextLine();
                    while (artist == null || artist.equals(""))
                    {
                        System.out.print("Artist: ");
                        artist = scanner.nextLine();
                    }
                    store.searchArtist(artist);
                }
                else if (action.equalsIgnoreCase("SEARCHG"))
                {
                    System.out.print("Genre [POP, ROCK, JAZZ, HIPHOP, RAP, CLASSICAL]: ");
                    String genre = scanner.nextLine();
                    while (genre == null || genre.equals(""))
                    {
                        System.out.print("Genre [POP, ROCK, JAZZ, HIPHOP, RAP, CLASSICAL]: ");
                        genre = scanner.nextLine();
                    }
                    store.searchGenre(genre);
                }
                else if (action.equalsIgnoreCase("DOWNLOADA"))
                {
                    System.out.print("Artist Name: ");
                    String artist = scanner.nextLine();
                    while (artist == null || artist.equals(""))
                    {
                        System.out.print("Artist Name: ");
                        artist = scanner.nextLine();
                    }
                    mylibrary.downloadArtistGenre(artist, "Artist");
                }
                else if (action.equalsIgnoreCase("DOWNLOADG")) 
                {
                    System.out.print("Genre: ");
                    String genre = scanner.nextLine();
                    while (genre == null || genre.equals(""))
                    {
                        System.out.print("Genre: ");
                        genre = scanner.nextLine();
                    }
                    mylibrary.downloadArtistGenre(genre, "Genre");
                }
                System.out.print("\n>");
            }
            catch (AudioContentNotFoundException e)
            {
                System.out.println(e.getMessage());
            }
        }
    }
}

