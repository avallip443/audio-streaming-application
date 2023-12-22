/*
Name: Arathi Vallipuranathan
ID: 501168322
 */

import java.util.ArrayList;

public class Podcast extends AudioContent
{
    public static final String TYPENAME = "PODCAST";

    private String host;
    private ArrayList<Season> seasons;
    private int currentEpisode = 0;
    private int currentSeason = 0;

    public Podcast(String title, int year, String id, String type, String audioFile, int length, String host,
                   ArrayList<Season> seasons)
    {
        super(title, year, id, type, audioFile, length);
        this.host = host;
        this.seasons = seasons;
    }

    public String getType()
    {
        return TYPENAME;
    }

    public void printInfo()
    {
        super.printInfo();
        System.out.println("Host: " + host + " \nSeasons: " + seasons.size());
    }

    public void play()
    {
        // sets audioFile to included both the selected episode title and the episode file
        super.setAudioFile(seasons.get(currentSeason).getEpisodeTitles().get(currentEpisode) + ".\n" +
                seasons.get(currentSeason).getEpisodeFiles().get(currentEpisode));
        super.play();  // plays selected episode
    }

    // prints table of contents of podcast (episode titles)
    public void printTOC()
    {
        for (int i = 0; i < seasons.get(currentSeason).numberOfEpisodes(); i++)
        {
            int index = i + 1;
            System.out.print("Episode " + index + ". ");
            System.out.println(seasons.get(currentSeason).getEpisodeTitles().get(i));
            System.out.println();
        }
    }

    // selects episode to play
    public void selectEpisode(int episode)
    {
        if (episode >= 1 && episode <= seasons.get(currentSeason).numberOfEpisodes())
        {
            currentEpisode = episode - 1;
        }
    }

    // selects season to play
    public void selectSeason(int season)
    {
        if (season >= 1 && season <= seasons.size())
        {
            currentSeason = season - 1;
        }
    }

    public String getHost()
    {
        return this.host;
    }

    public void setHost(String host)
    {
        this.host = host;
    }

    public ArrayList<Season> getSeasons()
    {
        return this.seasons;
    }

    public void setSeasons(ArrayList<Season> seasons)
    {
        this.seasons = seasons;
    }

    public int getNumberOfSeasons()
    {
        return seasons.size();
    }
}
