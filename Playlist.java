import java.util.ArrayList;

/*
 * A Playlist contains an array list of AudioContent (i.e. Song, AudioBooks, Podcasts) from the library
 */

public class Playlist {
    private String title;
    private ArrayList<AudioContent> contents; 

    public Playlist(String title)
    {
        this.title = title;
        contents = new ArrayList<AudioContent>();
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public void addContent(AudioContent content)
    {
        contents.add(content);
    }

    public ArrayList<AudioContent> getContent()
    {
        return contents;
    }

    public void setContent(ArrayList<AudioContent> contents)
    {
        this.contents = contents;
    }

    public void printContents()
    {
        for (int i = 0; i < contents.size(); i++)
        {
            int index = i + 1;
            System.out.print("" + index + ". ");
            contents.get(i).printInfo();
            System.out.println();
        }
    }

    // play all the AudioContent in the contents list
    public void playAll()
    {
        for (int i = 0; i < contents.size(); i++)
        {
            contents.get(i).play();
            System.out.println();
        }
    }

    // play the specific AudioContent from the contents array list.
    public void play(int index)
    {
        contents.get(index).play();
    }

    public boolean contains(int index)
    {
        return index >= 1 && index <= contents.size();
    }

    public boolean equals(Object other)
    {
        Playlist otherpl = (Playlist) other;
        return this.title.equalsIgnoreCase(otherpl.title);
    }

    public void deleteContent(int index)
    {
        if (!(index < 1 || index > contents.size()))
        {
            contents.remove(index-1);
        }
    }
}
