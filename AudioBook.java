import java.util.ArrayList;

public class AudioBook extends AudioContent
{
    public static final String TYPENAME = "AUDIOBOOK";

    private String author;
    private String narrator;
    private ArrayList<String> chapterTitles;
    private ArrayList<String> chapters;
    private int currentChapter = 0;

    public AudioBook(String title, int year, String id, String type, String audioFile, int length,
                     String author, String narrator, ArrayList<String> chapterTitles, ArrayList<String> chapters) {
        super(title, year, id, type, audioFile, length);
        this.author = author;
        this.narrator = narrator;
        this.chapterTitles = chapterTitles;
        this.chapters = chapters;
    }

    public String getType()
    {
        return TYPENAME;
    }

    public void printInfo()
    {
        super.printInfo();
        System.out.println("Author: " + author + " Narrated by: " + narrator);
    }

    // plays audiobook - sets  audioFile to the current chapter title, followed by the current chapter
    public void play()
    {
        super.setAudioFile(chapterTitles.get(currentChapter) + ".\n" + chapters.get(currentChapter));
        super.play();  // plays selected chapter
    }

    // prints the table of contents of the book
    public void printTOC()
    {
        for (int i = 0; i < chapters.size(); i++)
        {
            int index = i + 1;
            System.out.print("Chapter " + index + ". ");
            System.out.println(chapterTitles.get(i));
            System.out.println();
        }
    }

    public void selectChapter(int chapter)
    {
        if (chapter >= 1 && chapter <= chapters.size())
        {
            currentChapter = chapter - 1;
        }
    }

    public boolean equals(Object other)
    {
        AudioBook otherBook = (AudioBook) other;
        return super.equals(otherBook) && getAuthor().equals(otherBook.getAuthor()) && getNarrator().equals(otherBook.getNarrator());
    }

    public int getNumberOfChapters()
    {
        return chapters.size();
    }

    public String getAuthor()
    {
        return author;
    }

    public void setAuthor(String author)
    {
        this.author = author;
    }

    public String getNarrator()
    {
        return narrator;
    }

    public void setNarrator(String narrator)
    {
        this.narrator = narrator;
    }

    public ArrayList<String> getChapterTitles()
    {
        return chapterTitles;
    }

    public void setChapterTitles(ArrayList<String> chapterTitles)
    {
        this.chapterTitles = chapterTitles;
    }

    public ArrayList<String> getChapters()
    {
        return chapters;
    }

    public void setChapters(ArrayList<String> chapters)
    {
        this.chapters = chapters;
    }
}
