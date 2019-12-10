package com.example.movietrailerapp.moviecontent;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.movietrailerapp.moviecontent.MovieContract.*;
import androidx.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class MovieDbHelper extends SQLiteOpenHelper{

    private static final String DATABASE_NAME = "YourTrailerAppDatabase";
    private static final int DATABASE_VERSION = 1;
    private SQLiteDatabase db;

    public MovieDbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;
        final String SQL_CREATE_QUESTINS_TABLE =
                "CREATE TABLE " + MovieContract.movieTable.TABLE_NAME  +
                        " ( " +
                        movieTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        movieTable.COLUMN_NAME + " TEXT, " +
                        movieTable.COLUMN_THUMBNAIL + " TEXT, " +
                        movieTable.COLUMN_DESCRIPTION + " TEXT, " +
                        movieTable.COLUMN_LINK + " TEXT, " +
                        movieTable.COLUMN_RATING + " INTEGER " +
                        ")";

        db.execSQL(SQL_CREATE_QUESTINS_TABLE);
        fillmovieTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + movieTable.TABLE_NAME);
        onCreate(db);
    }

    private void fillmovieTable()
    {
        MovieItem m1 = new MovieItem(1,"NO TIME TO DIE","","PLOT: Bond has left active service. His peace is short-lived when his old friend Felix " +
                "Leiter from the CIA turns up asking for help, leading Bond onto the trail of a mysterious villain armed with dangerous new technology." +
                "\nCAST: Daniel Craig, Rami Malek, Ana de Armas, Léa Seydoux","ZGSBSRnrCnQ",(int)(Math.random()*5+5));

        MovieItem m2 = new MovieItem(2,"BLACK WIDOW","","PLOT: A film about Natasha Romanoff in her quests between the films Civil War and Infinity War.\n" +
                "CAST: Scarlett Johansson, Florence Pugh, Robert Downey Jr.","R7fIN2juq98",(int)(Math.random()*5+5));

        MovieItem m3 = new MovieItem(3,"The Assistant","","Starring: Julia Garner, Matthew Macfadyen, Makenzie Leigh \n" +
                "Directed By: Kitty Green\n" +
                "Synopsis: A look at the day in the life of the assistant to a powerful corporate executive. ","z9761kQCNWc",(int)(Math.random()*5+5));

        MovieItem m4 = new MovieItem(4,"Knives Out","","US Release Date: November 27, 2019\n" +
                "Starring: Ana de Armas, Chris Evans, Daniel Craig\n" +
                "Directed By: Rian Johnson\n" +
                "Synopsis: A detective investigates the death of a patriarch of an eccentric, combative family.","xi-1NchUqMA",(int)(Math.random()*5+5));

        MovieItem m5 = new MovieItem(5,"Pati Patni Aur Woh",""," \"Pati Patni Aur Woh\"  romantic comedy film Directed By Mudassar Aziz and Produced By " +
                "Bhushan Kumar, Renu Ravi Chopra, Krishan Kumar. The film is a remake of the 1978 film of the same name and is starring Kartik Aaryan, Bhumi Pednekar and " +
                "Ananya Panday in lead roles along with Aparshakti Khurrana in supporting role.","L7a1JSeqaXk",(int)(Math.random()*5+5));

        MovieItem m6 = new MovieItem(6,"Dark Waters","","Starring: Anne Hathaway, Mark Ruffalo, William Jackson Harper \n" +
                "Directed By: Todd Haynes\n" +
                "Synopsis: Inspired by a shocking true story, a tenacious attorney (Ruffalo) uncovers a dark secret that connects a growing number of unexplained deaths due " +
                "to one of the world's largest corporations. In the process, he risks everything – his future, his family, and his own life - to expose the truth.",
                "jvQUIt0BWcU",(int)(Math.random()*5+5));

        MovieItem m7 = new MovieItem(7,"Hustlers","","Starring: Jennifer Lopez, Constance Wu, Cardi B\n" +
                "Directed By: Lorene Scafaria\n" +
                "Synopsis: Inspired by the viral New York Magazine article, Hustlers follows a crew of savvy former strip club employees who band together to turn the " +
                "tables on their Wall Street clients.","LUG2U-IxPx0",(int)(Math.random()*5+5));

        MovieItem m8 = new MovieItem(8,"Panipat","","PANIPAT is set in 1761, when the Maratha Empire had reached its zenith and their grip on Hindostan " +
                "reigned supreme with no-one to challenge them until an invader set his eyes on the throne of Hindostan. That’s when Sadashiv Rao Bhau (Arjun Kapoor), " +
                "the Commander-in-Chief of the Maratha army led a northern expedition in order to repel the invading forces of Ahmad Shah Abdali (Sanjay Dutt), the " +
                "King of Afghanistan. This War epic entails the events that led to the Third Battle of Panipat, watch it in cinemas on 6th December.",
                "zpXnmy-6w1g",(int)(Math.random()*5+5));

        MovieItem m9 = new MovieItem(9,"21 Bridges","","US Release Date: July 12, 2019\n" +
                "Starring: Taylor Kitsch, Sienna Miller, Chadwick Boseman \n" +
                "Directed By: Brian Kirk\n" +
                "Synopsis: A disgraced detective in the NYPD is given a shot at redemption.","BVZDhunTrYA",(int)(Math.random()*5+5));

        MovieItem m10 = new MovieItem(10,"Messiah","","A CIA officer investigates a charismatic figure whose followers believe that he can perform miracles. " +
                "Is he a divine entity or a dangerous con artist?","mjLWuzGVyew",(int)(Math.random()*5+5));

        MovieItem m11= new MovieItem(11,"Mardaani 2","","She's back. She's unstoppable. She is Shivani Shivaji Roy. ",
                "dKeRIOA28Jk",(int)(Math.random()*5+5));

        MovieItem m12= new MovieItem(12,"ONCE UPON A TIME IN HOLLYWOOD","","Quentin Tarantino’s ONCE UPON A TIME IN HOLLYWOOD visits 1969 Los " +
                "Angeles, where everything is changing, as TV star Rick Dalton (Leonardo DiCaprio) and his longtime stunt double Cliff Booth (Brad Pitt) " +
                "make their way around an industry they hardly recognize anymore. The ninth film from the writer-director features a large ensemble cast and " +
                "multiple storylines in a tribute to the final moments of Hollywood’s golden age.","ELeMaP8EPAA",(int)(Math.random()*5+5));

        MovieItem m13 = new MovieItem(13,"Upgrade","","Starring: Logan Marshall-Green, Rosco Campbell, Richard Cawthorne \n" +
                "Directed By: Leigh Whannell\n" +
                "Synopsis: Set in the near-future, technology controls nearly all aspects of life. But when Grey, a self-identified technophobe, has his " +
                "world turned upside down, his only hope for revenge is an experimental computer chip implant called Stem. ",
                "36PDeN9NRZ0",(int)(Math.random()*5+5));

        MovieItem m14= new MovieItem(14,"ROBIBAAR","","Starring : Prosenjit Chatterjee, Jaya Ahsan, Sudipa Basu, Tathagato Banerjee, " +
                "Mithun Debnath, Saswati Sinha and Srijato Banerjee.","H-vUuGBDqQU",(int)(Math.random()*5+5));

        MovieItem m15= new MovieItem(15,"JUMANJI: THE NEXT LEVEL","","In Jumanji: The Next Level, the gang is back but the game has " +
                "changed. As they return to Jumanji to rescue one of their own, they discover that nothing is as they expect. The players will have to " +
                "brave parts unknown and unexplored, from the arid deserts to the snowy mountains, in order to escape the world’s most dangerous game.",
                "rBxcF-r9Ibs",(int)(Math.random()*5+5));

        MovieItem m16= new MovieItem(16,"Pagalpanti","","An Anees Bazmee Film, Presenting the official trailer of the upcoming movie " +
                "Pagalpanti. The movie is produced by Bhushan Kumar & Krishan Kumar, also produced by Kumar Mangat Pathak & Abhishek Pathak. This comedy " +
                "film directed by Anees Bazmee The movie is starring Anil Kapoor, John Abraham, Ileana D'Cruz, Arshad Warsi, Pulkit Samrat, Kriti Kharbanda, " +
                "Urvashi Rautela, and Saurabh Shukla.","3-7jehmURuM",(int)(Math.random()*5+5));

        MovieItem m17= new MovieItem(17,"Parasite","","Bong Joon Ho brings his singular mastery home to Korea in this pitch-black modern " +
                "fairytale. Meet the Park Family: the picture of aspirational wealth. And the Kim Family, rich in street smarts but not much else. " +
                "Be it chance or fate, these two houses are brought together and the Kims sense a golden opportunity. Masterminded by college-aged Ki-woo, " +
                "the Kim children expediently install themselves as tutor and art therapist, to the Parks. Soon, a symbiotic relationship forms between " +
                "the two families. The Kims provide \"indispensable\" luxury services while the Parks obliviously bankroll their entire household. " +
                "When a parasitic interloper threatens the Kims' newfound comfort, a savage, underhanded battle for dominance breaks out, threatening " +
                "to destroy the fragile ecosystem between the Kims and the Parks.","5xH0HfJHsaY",(int)(Math.random()*5+5));

        addMovieItem(m1);
        addMovieItem(m2);
        addMovieItem(m3);
        addMovieItem(m4);
        addMovieItem(m5);
        addMovieItem(m6);
        addMovieItem(m7);
        addMovieItem(m8);
        addMovieItem(m9);
        addMovieItem(m10);
        addMovieItem(m11);
        addMovieItem(m12);
        addMovieItem(m13);
        addMovieItem(m14);
        addMovieItem(m15);
        addMovieItem(m16);
        addMovieItem(m17);
    }

    public void addMovieItem(MovieItem movieitem)
    {
        ContentValues cv = new ContentValues();
        cv.put(movieTable.COLUMN_NAME,movieitem.name);
        cv.put(movieTable.COLUMN_THUMBNAIL,movieitem.thumbnail);
        cv.put(movieTable.COLUMN_DESCRIPTION,movieitem.description);
        cv.put(movieTable.COLUMN_LINK,movieitem.link);
        cv.put(movieTable.COLUMN_RATING,movieitem.rating);

        db.insert(movieTable.TABLE_NAME,null,cv);
    }

    public void addMovie(MovieItem movieitem)
    {
        db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(movieTable.COLUMN_NAME,movieitem.name);
        cv.put(movieTable.COLUMN_THUMBNAIL,movieitem.thumbnail);
        cv.put(movieTable.COLUMN_DESCRIPTION,movieitem.description);
        cv.put(movieTable.COLUMN_LINK,movieitem.link);
        cv.put(movieTable.COLUMN_RATING,movieitem.rating);

        db.insert(movieTable.TABLE_NAME,null,cv);
    }

    public List<MovieItem> getAllMovies()
    {
        List<MovieItem> movieList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + movieTable.TABLE_NAME, null);

        if(c.moveToFirst())
        {
            do{
                MovieItem movieItem = new MovieItem();
                movieItem.id = Integer.parseInt(c.getString(c.getColumnIndex(movieTable._ID)));
                movieItem.name = c.getString(c.getColumnIndex(movieTable.COLUMN_NAME));
                movieItem.thumbnail = c.getString(c.getColumnIndex(movieTable.COLUMN_THUMBNAIL));
                movieItem.description = c.getString(c.getColumnIndex(movieTable.COLUMN_DESCRIPTION));
                movieItem.link = c.getString(c.getColumnIndex(movieTable.COLUMN_LINK));
                movieItem.rating =  Integer.parseInt(c.getString(c.getColumnIndex(movieTable.COLUMN_RATING)));

                movieList.add(movieItem);

            }while(c.moveToNext());
        }
        c.close();

        return movieList;
    }

    public int deleteMovie(int mid) {
        db = getWritableDatabase();
        int a = db.delete(movieTable.TABLE_NAME,movieTable._ID+"=?",new String[]{String.valueOf(mid)});
        return a;
    }

    public boolean updaterating(String id, int rating)
    {
        db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(movieTable.COLUMN_RATING,rating);
        db.update(movieTable.TABLE_NAME,cv,movieTable._ID+"=?",new String[]{id});
        return true;
    }

    public String movielink(String id)
    {
        String s = "";
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT " + movieTable.COLUMN_LINK + " FROM " + movieTable.TABLE_NAME +" WHERE " + movieTable._ID + "=" + id + " ;", null);
        if(c.moveToFirst())
        {
            do{
                s = c.getString(c.getColumnIndex(movieTable.COLUMN_LINK));
            }while(c.moveToNext());
        }
        c.close();

        return s;
    }

    public String movieid(String name)
    {
        String s = "";
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT " + movieTable._ID + " FROM " + movieTable.TABLE_NAME +" WHERE " + movieTable.COLUMN_NAME + " like '%" + name + "%'" +" ;", null);
        if(c.moveToFirst())
        {
            do{
                s = c.getString(c.getColumnIndex(movieTable._ID));
            }while(c.moveToNext());
        }
        c.close();

        return s;
    }
}