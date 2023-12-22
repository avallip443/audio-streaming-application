public class Song extends AudioContent implements Comparable <Song> { 
    public static final String TYPENAME = "SONG";
    public static enum Genre {POP, ROCK, JAZZ, HIPHOP, RAP, CLASSICAL};
    private String artist;
    private String composer; 	
    private Genre  genre;
    private String lyrics;


    public Song(String title, int year, String id, String type, String audioFile, int length, String artist,
                String composer, Genre genre, String lyrics)
    {
        super(title, year, id, type, audioFile, length);
        this.artist = artist;
        this.composer = composer;
        this.genre = genre;
        this.lyrics = lyrics;
    }

    public String getType()
    {
        return TYPENAME;
    }

    public void printInfo()
    {
        super.printInfo();
        System.out.println("Artist: " + artist + " Composer: " + composer + " Genre: " + genre);
    }

    public void play()
    {
        super.setAudioFile(lyrics);
        super.play();
    }

    public String getComposer()
    {
        return this.composer;
    }

    public void setComposer(String composer)
    {
        this.composer = composer;
    }

    public String getArtist()
    {
        return this.artist;
    }

    public void setArtist(String artist)
    {
        this.artist = artist;
    }

    public String getLyrics()
    {
        return this.lyrics;
    }

    public void setLyrics(String lyrics)
    {
        this.lyrics = lyrics;
    }

    public Genre getGenre()
    {
        return this.genre;
    }

    public void setGenre(Genre genre)
    {
        this.genre = genre;
    }

    public boolean equals(Object other)
    {
        Song otherSong = (Song) other;
        return super.equals(otherSong) && composer.equals(otherSong.getComposer()) && artist.equals(otherSong.getArtist());
    }

    public int compareTo(Song other) {
        return super.getTitle().compareTo(other.getTitle());
    }
}
