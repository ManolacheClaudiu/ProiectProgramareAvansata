import Main.Database;
import com.SetLocale;
import dao.*;

import repositories.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.ResourceBundle;
import java.util.Stack;

public class ProiectPA {
    private JPanel Main;
    private JTextField txtTitle;
    private JTextField txtRelease_date;
    private JTextField txtDuration;
    private JButton updateButton;
    private JButton saveButton1;
    private JButton deleteButton1;
    private JTextField txtIdMovie;
    private JTextField txtScore;
    private JButton searchMovieByIdButton;
    private JTextField txtActorsId;
    private JTextField txtActorsMovieId;
    private JTextField txtActorsName;
    private JTextField txtAssociativeMovieId;
    private JTextField txtAssociativeGenreId;
    private JTextField txtAssociativeId;
    private JTextField txtDirectorsMovieId;
    private JTextField txtDirectorsName;
    private JTextField txtDirectorsId;
    private JTextField txtGenresName;
    private JTextField txtGenresId;
    private JButton ActorsSaveButton;
    private JButton ActorsDeleteButton;
    private JButton ActorsUpdateButton;
    private JButton ActorsSearchButton;
    private JButton AssociativeSaveButton;
    private JButton AssociativeDeleteButton;
    private JButton AssociativeUpdateButton;
    private JButton AssociativeSearchButton;
    private JButton DirectorsSaveButton;
    private JButton DirectorsDeleteButton;
    private JButton DirectorsUpdateButton;
    private JButton DirectorsSearchButton;
    private JButton GenresSaveButton;
    private JButton GenresDeleteButton;
    private JButton GenresUpdateButton;
    private JButton GenresSearchButton;
    private JTabbedPane ProiectPa;
    private JComboBox comboBox1;
    private JPanel Actors;
    private JPanel Movies;
    private JPanel Association;
    private JPanel Directors;
    private JPanel Genres;
    private JLabel GenresID;
    private JLabel ActorsMoviesID;
    private JLabel ActorsName;
    private JLabel MoviesID;
    private JLabel GenresName;
    private JLabel ActorsID;
    private JLabel DirectorsName;
    private JLabel AssociativeMoviesID;
    private JLabel DirectorsMoviesID;
    private JLabel AssociativeGenresID;
    private JLabel AssociativeID;
    private JLabel Title;
    private JLabel ReleaseDate;
    private JLabel Duration;
    private JLabel Score;
    private JLabel DirectorsID;
    private JLabel Msg;
    private JScrollPane TableScroll;
    private JTable ActorsTable;
    private JTable MoviesTable;
    private JTable AssociationTable;
    private JTable DirectorsTable;
    private JTable GenresTable;
    ResourceBundle r;
    SetLocale setLocale = new SetLocale();
    public static void main(String[] args) {
        JFrame frame = new JFrame("ProiectPA");
        frame.setContentPane(new ProiectPA().Main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public ArrayList<Actors> actorList(){

    ArrayList<Actors> actorsList = new ArrayList<>();
    try {

        Connection conn = Database.getConnection();
        String sql = "SELECT * FROM actors";
        Statement statement = conn.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            Actors actor = new Actors();
            actor.setId_actor(resultSet.getInt(1));
            actor.setId_movie(resultSet.getInt(2));
            actor.setName(resultSet.getString(3));
            actorsList.add(actor);
        }
    }catch (SQLException e1) {
        JOptionPane.showMessageDialog(null,e1);
    }
    return actorsList;
}
    public void showActors() {
        //Actors actor = new Actors();

        Object[][] actorsData = {};
        ActorsTable.setModel(new DefaultTableModel(actorsData, new String[]{r.getString("ActorsID"), r.getString("ActorsMoviesId"), r.getString("ActorsName")}));
        Object[][] moviesData = {};
        ArrayList<Actors> list = actorList();
        DefaultTableModel model = (DefaultTableModel)ActorsTable.getModel();
        Object[] row = new Object[3];
        for(int i=0;i<list.size();i++){
            row[0]=list.get(i).getId_actor();
            row[1]=list.get(i).getId_movie();
            row[2]=list.get(i).getName();
            model.addRow(row);
        }
    }

    public ArrayList<Movies> movieList(){
        ArrayList<Movies> moviesList = new ArrayList<>();
        try {
            Connection conn = Database.getConnection();
            String sql = "SELECT * FROM movies";
            Statement statement = conn.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Movies movies = new Movies();
                movies.setId_movie(resultSet.getInt(1));
                movies.setTitle(resultSet.getString(2));
                movies.setRelease_date(resultSet.getString(3));
                movies.setDuration(resultSet.getInt(4));
                movies.setScore(resultSet.getInt(5));
                moviesList.add(movies);
            }
        }catch (SQLException e1) {
            JOptionPane.showMessageDialog(null,e1);
        }
        return moviesList;
    }
    public void showMovies() {

        Object[][] moviesData = {};
        MoviesTable.setModel(new DefaultTableModel(moviesData, new String[]{r.getString("MoviesID"), r.getString("MoviesTitle"),
                r.getString("ReleaseDate"), r.getString("Duration"), r.getString("Score")}));
        ArrayList<Movies> list = movieList();
        DefaultTableModel model = (DefaultTableModel)MoviesTable.getModel();

        Object[] row = new Object[5];
        for(int i=0;i<list.size();i++){
            row[0]=list.get(i).getId_movie();
            row[1]=list.get(i).getTitle();
            row[2]=list.get(i).getRelease_date();
            row[3]=list.get(i).getDuration();
            row[4]=list.get(i).getScore();
            model.addRow(row);
        }
    }

    public ArrayList<Genres> genreList(){
        ArrayList<Genres> genresList = new ArrayList<>();
        try {
            Connection conn = Database.getConnection();
            String sql = "SELECT * FROM genres";
            Statement statement = conn.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
               Genres genres = new Genres();
                genres.setId_gen(resultSet.getInt(1));
                genres.setName(resultSet.getString(2));
                genresList.add(genres);
            }
        }catch (SQLException e1) {
            JOptionPane.showMessageDialog(null,e1);
        }
        return genresList;
    }
    public void showGenres() {

        Object[][] genresData = {};
        GenresTable.setModel(new DefaultTableModel(genresData, new String[]{r.getString("GenresID"), r.getString("GenresName")}));
        ArrayList<Genres> list = genreList();
        DefaultTableModel model = (DefaultTableModel)GenresTable.getModel();

        Object[] row = new Object[4];
        for(int i=0;i<list.size();i++){
            row[0]=list.get(i).getId_gen();
            row[1]=list.get(i).getName();
            model.addRow(row);
        }
    }

    public ArrayList<Directors> directorList(){
        ArrayList<Directors> directorsList = new ArrayList<>();
        try {
            Connection conn = Database.getConnection();
            String sql = "SELECT * FROM directors";
            Statement statement = conn.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Directors directors = new Directors();
                directors.setId_director(resultSet.getInt(1));
                directors.setId_movie(resultSet.getInt(2));
                directors.setName(resultSet.getString(3));
                directorsList.add(directors);
            }
        }catch (SQLException e1) {
            JOptionPane.showMessageDialog(null,e1);
        }
        return directorsList;
    }
    public void showDirectors() {

        Object[][] directorsData = {};
        DirectorsTable.setModel(new DefaultTableModel(directorsData, new String[]{r.getString("DirectorsID"), r.getString("DirectorsMoviesId"),
                r.getString("DirectorsName")}));
        ArrayList<Directors> list = directorList();
        DefaultTableModel model = (DefaultTableModel)DirectorsTable.getModel();

        Object[] row = new Object[3];
        for(int i=0;i<list.size();i++){
            row[0]=list.get(i).getId_director();
            row[1]=list.get(i).getId_movie();
            row[2]=list.get(i).getName();
            model.addRow(row);
        }
    }
    public ArrayList<Associative> associativeList(){
        ArrayList<Associative> associativesList = new ArrayList<>();
        try {
            Connection conn = Database.getConnection();
            String sql = "SELECT * FROM associative";
            Statement statement = conn.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Associative associative = new Associative();
               associative.setId_associative(resultSet.getInt(1));
                associative.setId_movie(resultSet.getInt(2));
                associative.setId_gen(resultSet.getInt(3));
                associativesList.add(associative);
            }
        }catch (SQLException e1) {
            JOptionPane.showMessageDialog(null,e1);
        }
        return associativesList;
    }
    public void showAssociative() {

        Object[][] associativeData = {};
        AssociationTable.setModel(new DefaultTableModel(associativeData, new String[]{r.getString("AssociativeID"), r.getString("AssociativeMoviesId"),
                r.getString("AssociativeGenresId")}));
        ArrayList<Associative> list = associativeList();
        DefaultTableModel model = (DefaultTableModel)AssociationTable.getModel();

        Object[] row = new Object[4];
        for(int i=0;i<list.size();i++){
            row[0]=list.get(i).getId_associative();
            row[1]=list.get(i).getId_movie();
            row[2]=list.get(i).getId_gen();

            model.addRow(row);
        }
    }


    public ProiectPA() {
        SetLocale setLocale = new SetLocale();
        r = ResourceBundle.getBundle("res/Bundle", setLocale.getUsLocale());

        showActors();
        showDirectors();
        showAssociative();
        showGenres();
        showMovies();
        searchMovieByIdButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int idMovie = Integer.parseInt(txtIdMovie.getText());
                try {
                    Movies movies = new Movies();
                    MoviesRepo moviesRepo = new MoviesRepo();
                    movies.setId_movie((idMovie));
                    movies = moviesRepo.findById(movies);
                    if (movies.getId_movie() != -1) {
                        txtTitle.setText(movies.getTitle());
                        txtRelease_date.setText(movies.getRelease_date());
                        txtDuration.setText(Integer.toString(movies.getDuration()));
                        txtScore.setText(Integer.toString(movies.getScore()));
                        JOptionPane.showMessageDialog(null, r.getString("Founded"));
                    } else {
                        txtTitle.setText("");
                        txtRelease_date.setText("");
                        txtDuration.setText("");
                        txtScore.setText("");
                        JOptionPane.showMessageDialog(null, r.getString("NotFounded"));
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }

            }

        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Movies movies = new Movies();
                movies.setId_movie(Integer.parseInt(txtIdMovie.getText()));
                movies.setTitle(txtTitle.getText());
                movies.setRelease_date(txtRelease_date.getText());
                movies.setDuration(Integer.parseInt(txtDuration.getText()));
                movies.setScore(Integer.parseInt(txtScore.getText()));
                try {
                    MoviesRepo moviesRepo = new MoviesRepo();
                    moviesRepo.update(movies);
                    JOptionPane.showMessageDialog(null, r.getString("Updated"));
                    txtTitle.setText("");
                    txtRelease_date.setText("");
                    txtDuration.setText("");
                    txtScore.setText("");
                    txtTitle.requestFocus();
                } catch (SQLException | ParseException e1) {
                    e1.printStackTrace();
                }

                showMovies();
            }
        });

        deleteButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int idMovie = Integer.parseInt(txtIdMovie.getText());
                try {
                    Movies movies = new Movies();
                    MoviesRepo moviesRepo = new MoviesRepo();
                    movies.setId_movie((idMovie));
                    moviesRepo.delete(movies);
                    txtTitle.setText("");
                    txtRelease_date.setText("");
                    txtDuration.setText("");
                    txtScore.setText("");
                    txtIdMovie.setText("");
                    JOptionPane.showMessageDialog(null, r.getString("Deleted"));
                } catch (SQLException | ParseException e1) {
                    e1.printStackTrace();
                }
                showMovies();
            }
        });

        saveButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Movies movies = new Movies();
                movies.setTitle(txtTitle.getText());
                movies.setRelease_date(txtRelease_date.getText());
                movies.setDuration(Integer.parseInt(txtDuration.getText()));
                movies.setScore(Integer.parseInt(txtScore.getText()));
                try {
                    MoviesRepo moviesRepo = new MoviesRepo();
                    moviesRepo.create(movies);
                    JOptionPane.showMessageDialog(null, r.getString("Added"));

                    txtTitle.setText("");
                    txtRelease_date.setText("");
                    txtDuration.setText("");
                    txtScore.setText("");
                    txtTitle.requestFocus();
                    txtIdMovie.setText("");
                } catch (SQLException | ParseException e1) {
                    e1.printStackTrace();
                }
                showMovies();
            }
        });

        ActorsSaveButton.addActionListener(new ActionListener() {
            @Override
                public void actionPerformed(ActionEvent e) {
                Actors actors = new Actors();
                actors.setId_movie(Integer.parseInt(txtActorsMovieId.getText()));
                actors.setName(txtActorsName.getText());
                try {
                    ActorsRepo actorsRepo = new ActorsRepo();
                    actorsRepo.create(actors);
                    JOptionPane.showMessageDialog(null, r.getString("Added"));
                    txtActorsName.setText("");
                    txtActorsMovieId.setText("");
                    txtActorsId.setText("");
                    txtActorsName.requestFocus();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                showActors();
            }
        });

        ActorsDeleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int idActor = Integer.parseInt(txtActorsId.getText());
                try {
                    Actors actors = new Actors();
                    ActorsRepo actorsRepo = new ActorsRepo();
                    actors.setId_actor(idActor);
                    actorsRepo.delete(actors);
                    txtActorsName.setText("");
                    txtActorsMovieId.setText("");
                    txtActorsId.setText("");
                    txtActorsName.requestFocus();
                    JOptionPane.showMessageDialog(null, r.getString("Deleted"));
                } catch (SQLException | ParseException e1) {
                    e1.printStackTrace();
                }
                showActors();
            }
        });

        ActorsUpdateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Actors actors = new Actors();
                actors.setId_movie(Integer.parseInt(txtActorsMovieId.getText()));
                actors.setName(txtActorsName.getText());
                actors.setId_actor(Integer.parseInt(txtActorsId.getText()));
                try {
                    ActorsRepo actorsRepo = new ActorsRepo();
                    actorsRepo.update(actors);
                    JOptionPane.showMessageDialog(null, r.getString("Updated"));
                    txtActorsName.setText("");
                    txtActorsMovieId.setText("");
                    txtActorsId.setText("");
                    txtActorsName.requestFocus();
                } catch (SQLException | ParseException e1) {
                    e1.printStackTrace();
                }
                showActors();
            }
        });

        ActorsSearchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int idActor = Integer.parseInt(txtActorsId.getText());
                try {
                    Actors actors = new Actors();
                    ActorsRepo actorsRepo = new ActorsRepo();
                    actors.setId_actor(idActor);
                    actors = actorsRepo.findById(actors);
                    if (actors.getId_actor() != -1) {
                        txtActorsName.setText(actors.getName());
                        txtActorsId.setText(Integer.toString(actors.getId_actor()));
                        txtActorsMovieId.setText(Integer.toString(actors.getId_movie()));
                        JOptionPane.showMessageDialog(null, r.getString("Founded"));
                    } else {
                        txtActorsName.setText("");
                        txtActorsMovieId.setText("");
                        txtActorsId.setText("");
                        txtActorsName.requestFocus();
                        JOptionPane.showMessageDialog(null, r.getString("NotFounded"));
                    }
                } catch (SQLException  e1) {
                    e1.printStackTrace();
                }
                showActors();
            }
        });

        AssociativeSaveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Associative associative = new Associative();
                associative.setId_movie(Integer.parseInt(txtAssociativeMovieId.getText()));
                associative.setId_gen(Integer.parseInt(txtAssociativeGenreId.getText()));
                try {
                    AssociativeRepo associativeRepo = new AssociativeRepo();
                    associativeRepo.create(associative);
                    JOptionPane.showMessageDialog(null, r.getString("Added"));
                    txtAssociativeGenreId.setText("");
                    txtAssociativeMovieId.setText("");
                    txtAssociativeId.setText("");
                    txtAssociativeId.requestFocus();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                showAssociative();
            }
        });

        AssociativeDeleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int idAssociative = Integer.parseInt(txtAssociativeId.getText());
                try {
                    Associative  associative = new Associative();
                    AssociativeRepo associativeRepo= new AssociativeRepo();
                    associative.setId_associative(idAssociative);
                    associativeRepo.delete(associative);
                    txtAssociativeGenreId.setText("");
                    txtAssociativeMovieId.setText("");
                    txtAssociativeId.setText("");
                    txtAssociativeId.requestFocus();
                    JOptionPane.showMessageDialog(null, r.getString("Deleted"));
                } catch (SQLException | ParseException e1) {
                    e1.printStackTrace();
                }
                showAssociative();
            }
        });

        AssociativeUpdateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Associative  associative = new Associative();
                associative.setId_movie(Integer.parseInt(txtAssociativeMovieId.getText()));
                associative.setId_associative(Integer.parseInt(txtAssociativeId.getText()));
                associative.setId_gen(Integer.parseInt(txtAssociativeGenreId.getText()));
                try {
                    AssociativeRepo associativeRepo = new AssociativeRepo();
                    associativeRepo.update(associative);
                    JOptionPane.showMessageDialog(null, r.getString("Updated"));
                    txtAssociativeGenreId.setText("");
                    txtAssociativeId.setText("");
                    txtAssociativeMovieId.setText("");
                    txtAssociativeId.requestFocus();
                } catch (SQLException | ParseException e1) {
                    e1.printStackTrace();
                }
                showAssociative();
            }
        });

        AssociativeSearchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int idAssociative = Integer.parseInt(txtAssociativeId.getText());
                try {
                    Associative  associative = new Associative();
                    AssociativeRepo associativeRepo= new AssociativeRepo();
                    associative.setId_associative(idAssociative);
                    associative = associativeRepo.findById(associative);
                    if (associative.getId_associative() != -1) {
                        txtAssociativeGenreId.setText(Integer.toString(associative.getId_gen()));
                        txtAssociativeMovieId.setText(Integer.toString(associative.getId_movie()));
                        txtAssociativeId.setText(Integer.toString(associative.getId_associative()));
                        JOptionPane.showMessageDialog(null, r.getString("Founded"));
                    } else {
                        txtAssociativeGenreId.setText("");
                        txtAssociativeMovieId.setText("");
                        txtAssociativeId.setText("");
                        txtAssociativeId.requestFocus();
                        JOptionPane.showMessageDialog(null, r.getString("NotFounded"));
                    }
                } catch (SQLException  e1) {
                    e1.printStackTrace();
                }
                showAssociative();
            }
        });

        DirectorsSaveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Directors directors = new Directors();
                directors.setId_movie(Integer.parseInt(txtDirectorsMovieId.getText()));
                directors.setName(txtDirectorsName.getText());
                try {
                    DirectorsRepo directorsRepo = new DirectorsRepo();
                    directorsRepo.create(directors);
                    JOptionPane.showMessageDialog(null, r.getString("Added"));
                    txtDirectorsName.setText("");
                    txtDirectorsMovieId.setText("");
                    txtDirectorsId.setText("");
                    txtDirectorsName.requestFocus();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                showDirectors();
            }
        });

        DirectorsDeleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int idDirector = Integer.parseInt(txtDirectorsId.getText());
                try {
                    Directors directors = new Directors();
                    DirectorsRepo directorsRepo = new DirectorsRepo();
                    directors.setId_director(idDirector);
                    directorsRepo.delete(directors);
                    txtDirectorsName.setText("");
                    txtDirectorsMovieId.setText("");
                    txtDirectorsId.setText("");
                    txtDirectorsName.requestFocus();
                    JOptionPane.showMessageDialog(null, r.getString("Deleted"));
                } catch (SQLException | ParseException e1) {
                    e1.printStackTrace();
                }
                showDirectors();
            }
        });

        DirectorsUpdateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Directors directors = new Directors();
                directors.setId_movie(Integer.parseInt(txtDirectorsMovieId.getText()));
                directors.setName(txtDirectorsName.getText());
                directors.setId_director(Integer.parseInt(txtDirectorsId.getText()));
                try {
                    DirectorsRepo directorsRepo = new DirectorsRepo();
                    directorsRepo.update(directors);
                    JOptionPane.showMessageDialog(null, r.getString("Updated"));
                    txtDirectorsName.setText("");
                    txtDirectorsMovieId.setText("");
                    txtDirectorsId.setText("");
                    txtDirectorsName.requestFocus();
                } catch (SQLException | ParseException e1) {
                    e1.printStackTrace();
                }
                showDirectors();
            }
        });

        DirectorsSearchButton.addActionListener(new ActionListener() {
            @Override
        public void actionPerformed(ActionEvent e) {
            int idDirector = Integer.parseInt(txtDirectorsId.getText());
            try {
                Directors directors = new Directors();
                DirectorsRepo directorsRepo = new DirectorsRepo();
                directors.setId_director(idDirector);
                directors = directorsRepo.findById(directors);
                if (directors.getId_director() != -1) {
                    txtDirectorsName.setText(directors.getName());
                    txtDirectorsId.setText(Integer.toString(directors.getId_director()));
                    txtDirectorsMovieId.setText(Integer.toString(directors.getId_movie()));
                    JOptionPane.showMessageDialog(null, r.getString("Founded"));
                } else {
                    txtDirectorsName.setText("");
                    txtDirectorsMovieId.setText("");
                    txtDirectorsId.setText("");
                    txtDirectorsName.requestFocus();
                    JOptionPane.showMessageDialog(null, r.getString("NotFounded"));
                }
            } catch (SQLException  e1) {
                e1.printStackTrace();
            }
                showDirectors();
        }
        });

        GenresSaveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               Genres genres = new Genres();
                genres.setName(txtGenresName.getText());
                try {
                    GenresRepo genresRepo = new GenresRepo();
                    genresRepo.create(genres);
                    JOptionPane.showMessageDialog(null, r.getString("Added"));
                    txtGenresName.setText("");
                    txtGenresName.requestFocus();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                showGenres();
            }
        });

        GenresDeleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int idGenre = Integer.parseInt(txtGenresId.getText());
                try {
                    Genres genres = new Genres();
                    GenresRepo genresRepo = new GenresRepo();
                    genres.setId_gen(idGenre);
                    genresRepo.delete(genres);
                    txtGenresId.setText("");
                    txtGenresName.setText("");
                    txtGenresName.requestFocus();
                    JOptionPane.showMessageDialog(null, r.getString("Deleted"));
                } catch (SQLException | ParseException e1) {
                    e1.printStackTrace();
                }
                showGenres();
            }
        });

        GenresUpdateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Genres genres = new Genres();
                genres.setId_gen(Integer.parseInt(txtGenresId.getText()));
                genres.setName(txtGenresName.getText());
                try {
                    GenresRepo genresRepo = new GenresRepo();
                    genresRepo.update(genres);
                    JOptionPane.showMessageDialog(null, r.getString("Updated"));
                    txtGenresId.setText("");
                    txtGenresName.setText("");
                    txtGenresName.requestFocus();
                } catch (SQLException | ParseException e1) {
                    e1.printStackTrace();
                }
                showGenres();
            }
        });

        GenresSearchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int idGenre = Integer.parseInt(txtGenresId.getText());
                try {
                    Genres genres = new Genres();
                    GenresRepo genresRepo = new GenresRepo();
                    genres.setId_gen(idGenre);
                    genres =    genresRepo.findById(genres);
                    if (   genres.getId_gen() != -1) {
                        txtGenresName.setText(genres.getName());
                        //txtGenresId.setText(Integer.toString(genres.getId_gen()));
                        JOptionPane.showMessageDialog(null, r.getString("Founded"));
                    } else {
                        txtGenresId.setText("");
                        txtGenresName.setText("");
                        txtGenresName.requestFocus();
                        JOptionPane.showMessageDialog(null, r.getString("NotFounded"));
                    }
                } catch (SQLException  e1) {
                    e1.printStackTrace();
                }
                showGenres();
            }
        });

        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedValue = comboBox1.getSelectedItem().toString();

                    if(selectedValue =="Română") {
                        r = ResourceBundle.getBundle("res/Bundle", setLocale.getRoLocale());
                    }
                    else if (selectedValue =="Français"){
                            r = ResourceBundle.getBundle("res/Bundle",setLocale.getFrLocale());
                        }
                        else {
                            r  = ResourceBundle.getBundle("res/Bundle",setLocale.getUsLocale());
                        }
               Enumeration read = r.getKeys();
                showActors();
                showDirectors();
                showAssociative();
                showGenres();
                showMovies();
                JTableHeader th1 = ActorsTable.getTableHeader();
                JTableHeader th2 = MoviesTable.getTableHeader();
                JTableHeader th3 = AssociationTable.getTableHeader();
                JTableHeader th4 = DirectorsTable.getTableHeader();
                JTableHeader th5 = GenresTable.getTableHeader();
                TableColumnModel tcm = th1.getColumnModel();
                TableColumn tc;

                tcm = th1.getColumnModel();
                tc = tcm.getColumn(0);
                tc.setHeaderValue( r.getString("ActorsID") );
                tc = tcm.getColumn(1);
                tc.setHeaderValue(r.getString("ActorsMoviesId"));
                tc = tcm.getColumn(2);
                tc.setHeaderValue(  r.getString("ActorsName") );
                th1.repaint();

                tcm = th2.getColumnModel();
                tc = tcm.getColumn(0);
                tc.setHeaderValue( r.getString("MoviesID") );
                tc = tcm.getColumn(1);
                tc.setHeaderValue( r.getString("MoviesTitle"));
                tc = tcm.getColumn(2);
                tc.setHeaderValue( r.getString("ReleaseDate") );
                tc = tcm.getColumn(3);
                tc.setHeaderValue( r.getString("Duration") );
                tc = tcm.getColumn(4);
                tc.setHeaderValue( r.getString("Score") );
                th2.repaint();

                tcm = th3.getColumnModel();
                tc = tcm.getColumn(0);
                tc.setHeaderValue( r.getString("AssociativeID") );
                tc = tcm.getColumn(1);
                tc.setHeaderValue(r.getString("AssociativeMoviesId"));
                tc = tcm.getColumn(2);
                tc.setHeaderValue(  r.getString("AssociativeGenresId") );
                th3.repaint();

                tcm = th4.getColumnModel();
                tc = tcm.getColumn(0);
                tc.setHeaderValue( r.getString("DirectorsID") );
                tc = tcm.getColumn(1);
                tc.setHeaderValue(r.getString("DirectorsMoviesId"));
                tc = tcm.getColumn(2);
                tc.setHeaderValue(  r.getString("DirectorsName") );
                th4.repaint();

                tcm = th5.getColumnModel();
                tc = tcm.getColumn(0);
                tc.setHeaderValue( r.getString("GenresID") );
                tc = tcm.getColumn(1);
                tc.setHeaderValue(r.getString("GenresName"));
                th5.repaint();

               while(read.hasMoreElements()){
                            String key = (String)read.nextElement();
                            String value =(String)r.getObject(key);
                            if(key.equals("Save")){
                                saveButton1.setText(value);
                                ActorsSaveButton.setText(value);
                                AssociativeSaveButton.setText(value);
                                DirectorsSaveButton.setText(value);
                                GenresSaveButton.setText(value);
                            }
                            if(key.equals("Update")){
                                updateButton.setText(value);
                                ActorsUpdateButton.setText(value);
                                AssociativeUpdateButton.setText(value);
                                DirectorsUpdateButton.setText(value);
                                GenresUpdateButton.setText(value);
                            }
                            if(key.equals("Delete")){
                                deleteButton1.setText(value);
                                ActorsDeleteButton.setText(value);
                                AssociativeDeleteButton.setText(value);
                                DirectorsDeleteButton.setText(value);
                                GenresDeleteButton.setText(value);
                            }
                            if(key.equals("Search")){
                                searchMovieByIdButton.setText(value);
                                ActorsSearchButton.setText(value);
                                AssociativeSearchButton.setText(value);
                                DirectorsSearchButton.setText(value);
                                GenresSearchButton.setText(value);
                            }
                            if(key.equals("Name")){
                                ActorsName.setText(value);
                                DirectorsName.setText(value);
                                GenresName.setText(value);
                            }
                            if(key.equals("ActorsID")){
                                ActorsID.setText(value);
                            }
                            if(key.equals("MoviesID")){
                               AssociativeMoviesID.setText(value);
                                MoviesID.setText(value);
                                DirectorsMoviesID.setText(value);
                                ActorsMoviesID.setText(value);
                            }
                            if(key.equals("GenresID")){
                               AssociativeGenresID.setText(value);
                                GenresID.setText(value);
                            }
                            if(key.equals("AssociativeID")){
                                AssociativeID.setText(value);
                            }
                            if(key.equals("DirectorsID")){
                                DirectorsID.setText(value);
                            }
                            if(key.equals("Title")){
                                Title.setText(value);
                            }
                            if(key.equals("ReleaseDate")){
                                ReleaseDate.setText(value);
                            }
                            if(key.equals("Duration")){
                                Duration.setText(value);
                            }
                            if(key.equals("Score")){
                                Score.setText(value);
                            }
                            if(key.equals("Msg")){
                                Msg.setText(value);
                            }
                            if(key.equals("Actors")){
                                Actors.setName(value);
                                Actors.setToolTipText(value);
                            }
                            if(key.equals("Directors")){
                                Directors.setName(value);
                            }
                            if(key.equals("Associative")){
                                Association.setName(value);
                            }
                            if(key.equals("Genres")){
                                Genres.setName(value);
                            }
                            if(key.equals("Movies")){
                                Movies.setName(value);
                            }
                   if(key.equals("DirectorsMoviesId")){
                       DirectorsMoviesID.setName(value);
                   }
                        }
                    }
        });

    }
}

