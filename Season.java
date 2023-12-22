/*
Name: Arathi Vallipuranathan
ID: 501168322
 */

import java.util.ArrayList;

public class Season
{
    public ArrayList<String> episodeFiles;
    public ArrayList<String> episodeTitles;
    public ArrayList<Integer> episodeLengths;

    public Season()
    {
        this.episodeFiles = new ArrayList<>();
        this.episodeTitles = new ArrayList<>();
        this.episodeLengths = new ArrayList<>();
    }

    public ArrayList<String> getEpisodeFiles()
    {
        return this.episodeFiles;
    }

    public void setEpisodeFiles(ArrayList<String> episodeFiles)
    {
        this.episodeFiles = episodeFiles;
    }

    public ArrayList<String> getEpisodeTitles()
    {
        return this.episodeTitles;
    }

    public void setEpisodeTitles(ArrayList<String> episodeTitles)
    {
        this.episodeTitles = episodeTitles;
    }

    public ArrayList<Integer> getEpisodeLengths()
    {
        return this.episodeLengths;
    }

    public void setEpisodeLengths(ArrayList<Integer> episodeLengths)
    {
        this.episodeLengths = episodeLengths;
    }

    public int numberOfEpisodes()
    {
        return episodeFiles.size();
    }
}
