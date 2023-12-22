import java.io.File;
import java.io.IOException;
import java.util.*;

public class AudioContentStore {
    private static ArrayList<AudioContent> contents;
    private static Map<String, Integer> titleSearch;
    private static Map<String, ArrayList<Integer>> artistSearch;
    private static Map<String, ArrayList<Integer>> genreSearch;

    public AudioContentStore() {
        audioContentStore();
        makeTitleSearchMap();
        makeArtistSearchMap();
        makeGenreSearchMap();
    }

    // makes map with string title as key and integer index as value
    public void makeTitleSearchMap()
    {
        titleSearch = new HashMap<>();
        for (int i = 0; i < contents.size(); i++)
        {
            int index = i + 1;
            titleSearch.put(contents.get(i).getTitle().toLowerCase(), index);
        }
    }

    // makes map with string title as key and arraylist of integer indexes as value
    public void makeArtistSearchMap()
    {
        artistSearch = new HashMap();
        int index = 1;
        for (AudioContent c : contents)
        {
            if (c.getType().equals(Song.TYPENAME))
            {
                Song temp = (Song) c;

                // initializes arraylist if first encounter of author or else creates arraylist from existing indexes
                ArrayList<Integer> tempIndex = (artistSearch.containsKey(temp.getArtist().toLowerCase())) ? artistSearch.get(temp.getArtist().toLowerCase()) : new ArrayList<>();
                tempIndex.add(index);
                artistSearch.put(temp.getArtist().toLowerCase(), tempIndex);
            }
            else
            {
                AudioBook temp = (AudioBook) c;

                // initializes arraylist if first encounter of author or creates arraylist from existing indexes
                ArrayList<Integer> tempIndex = (artistSearch.containsKey(temp.getAuthor().toLowerCase())) ? artistSearch.get(temp.getAuthor().toLowerCase()) : new ArrayList<>();
                tempIndex.add(index);
                artistSearch.put(temp.getAuthor().toLowerCase(), tempIndex);
            }
            index++;
        }
    }

    // makes map with string genre as key and arraylist of integer indexes as value
    public void makeGenreSearchMap()
    {
        genreSearch = new HashMap();
        int index = 1;
        for (AudioContent c : contents) {
            // only considers song types
            if (c.getType().equals(Song.TYPENAME)) {
                Song temp = (Song) c;
                String genre = temp.getGenre().toString();

                // initializes arraylist if first encounter of genre or creates arraylist from existing indexes
                ArrayList<Integer> tempIndex = (genreSearch.containsKey(genre)) ? genreSearch.get(genre) : new ArrayList<>();
                tempIndex.add(index);
                genreSearch.put(genre, tempIndex);
                index++;
            }
        }
    }

    // prints out given title's index and info using titleSearch map
    public void searchTitle(String title)
    {
        if (titleSearch.containsKey(title.toLowerCase()))
        {
            int index = titleSearch.get(title.toLowerCase());
            System.out.print(index + ". ");
            contents.get(index-1).printInfo();
        }
        else
        {
            System.out.println("No matches for " + title);
        }
    }

    // prints out given artist's indexes and info using artistSearch map
    public void searchArtist(String artist)
    {
        if (artistSearch.containsKey(artist.toLowerCase()))
        {
            ArrayList<Integer> index = artistSearch.get(artist.toLowerCase());
            for (Integer i : index) {
                System.out.print(i + ". ");
                contents.get(i - 1).printInfo();
                System.out.println();
            }
        }
        else
        {
            System.out.println("No matches for " + artist);
        }
    }

    // prints out given genre's indexes and info using genreSearch map
    public void searchGenre(String genre)
    {
        if (genreSearch.containsKey(genre.toUpperCase()))
        {
            ArrayList<Integer> index = genreSearch.get(genre.toUpperCase());
            for (Integer i : index) {
                System.out.print(i + ". ");
                contents.get(i - 1).printInfo();
                System.out.println();
            }
        }
        else
        {
            System.out.println("No matches for " + genre);
        }
    }

    // getters for maps
    public static Map<String, Integer> getTitleArtist()
    {
        return titleSearch;
    }

    public static Map<String, ArrayList<Integer>> getSearchArtist()
    {
        return artistSearch;
    }

    public static Map<String, ArrayList<Integer>> getSearchGenre()
    {
        return genreSearch;
    }

    // gets content at specific index
    public AudioContent getContent(int index)
    {
        if (index < 1 || index > contents.size())
        {
            return null;
        }
        return contents.get(index-1);
    }

    public int getContentSize()
    {
        return contents.size();
    }

    // getter for contents arraylist
    public static ArrayList<AudioContent> getAllContents()
    {
        return contents;
    }

    public void listAll()
    {
        for(int i = 0; i < this.contents.size(); ++i)
        {
            int index = i + 1;
            System.out.print("" + index + ". ");
            contents.get(i).printInfo();
            System.out.println();
        }
    }

    // creates objects in contents arraylist from reading store text file
    public void audioContentStore()
    {
        contents = new ArrayList<>();
        try
        {
            Scanner in = new Scanner(new File("store.txt"));

            while(in.hasNextLine())
            {
                String checkType = in.nextLine();
                if (checkType.equals("SONG"))
                {
                    // stores data for song content
                    String id = in.nextLine();
                    String title = in.nextLine();
                    int year = in.nextInt();
                    int length = in.nextInt();
                    in.nextLine();
                    String author = in.nextLine();
                    String narrator = in.nextLine();
                    Song.Genre genre = Song.Genre.valueOf(in.nextLine());
                    int numLyricLines = in.nextInt();
                    String file = "";

                    for(int i = 0; i <= numLyricLines; i++)
                    {
                        file = file.concat(in.nextLine() + "\n");
                    }
                    contents.add(new Song(title, year, id, "SONG", file, length, author, narrator, genre, file));
                }
                else if (checkType.equals("AUDIOBOOK"))
                {
                    // store data for audiobook content type
                    String id = in.nextLine();
                    String title = in.nextLine();
                    int year = in.nextInt();
                    int length = in.nextInt();
                    in.nextLine();
                    String author = in.nextLine();
                    String narrator = in.nextLine();
                    ArrayList<String> titles = new ArrayList();
                    int numChapterTitles = in.nextInt();
                    in.nextLine();

                    for(int i = 0; i < numChapterTitles; i++)
                    {
                        titles.add(in.nextLine());
                    }

                    ArrayList<String> chapters = new ArrayList();

                    for(int i = 0; i < numChapterTitles; ++i)
                    {
                        int chapterLines = in.nextInt();
                        in.nextLine();
                        String chapter = "";

                        for(int j = 0; j < chapterLines; ++j)
                        {
                            chapter = chapter + in.nextLine() + "\r\n";
                        }
                        chapters.add(chapter);
                    }
                    contents.add(new AudioBook(title, year, id, "AUDIOBOOK", "", length, author, narrator, titles, chapters));
                }
            }
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }
}
