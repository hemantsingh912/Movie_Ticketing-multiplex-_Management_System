package View;

import Dao.MovieDAO;
import Model.MovieModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class AdminMoviePanel extends JPanel {

    JTextField txtSearch;

    JTextField txtName;
    JTextField txtGenre;
    JComboBox<String> cmbLanguage;
    JTextField txtDuration;
    JTextField txtPoster;
    JTextField txtReleaseDate;
    JTextField txtCertificate;
    JTextField txtTrailer;

    JTextArea txtDescription;

    JComboBox<String> cmbStatus;

    JButton btnSearch;
    JButton btnRefresh;
    JButton btnSave;
    JButton btnDelete;

    JTable table;
    DefaultTableModel model;

    MovieDAO dao = new MovieDAO();

    public AdminMoviePanel() {

        setLayout(null);
        setBackground(new Color(245,247,250));

        Font f = new Font("Arial", Font.BOLD, 15);

        

        //---------------- SEARCH PANEL ----------------

        JLabel lblSearch = new JLabel("Movie ID");
        lblSearch.setBounds(30,20,100,30);
        lblSearch.setForeground(Color.BLACK);
        add(lblSearch);

        txtSearch = new JTextField();
        txtSearch.setBounds(140,20,250,35);
        add(txtSearch);

        btnSearch = new JButton("Search");
       btnSearch.setBounds(410,20,120,35);
        btnSearch.setBackground(
        new Color(163,128,196));
        btnSearch.setForeground(Color.WHITE);
        add(btnSearch);

        btnRefresh = new JButton("Refresh");
        btnRefresh.setBounds(550,20,120,35);
        btnRefresh.setBackground(new Color(243,224,178));
        add(btnRefresh);

        //---------------- FORM PANEL ----------------

        JPanel form = new JPanel();
        form.setLayout(null);
        form.setBackground(Color.WHITE);
        form.setBorder( BorderFactory.createTitledBorder(BorderFactory.createLineBorder(
                        new Color(180,180,180)),"Movie Details",
                        0,0,new Font("Segoe UI",Font.BOLD,16)));
        form.setBounds(20,70,1460,320);

        add(form);

        txtName = new JTextField();
        txtGenre = new JTextField();
        cmbLanguage = new JComboBox<>(new String[]{"Hindi","English","Japanese","Tamil","Telugu","Kannada","Malayalam","Punjabi"});
        txtDuration = new JTextField();
        txtPoster = new JTextField();
        txtReleaseDate = new JTextField();
        txtCertificate = new JTextField();
        txtTrailer = new JTextField();

        txtDescription = new JTextArea();

        cmbStatus = new JComboBox<>(
                new String[]{
                    "Now Showing",
                    "Coming Soon",
                    "Ended"
                });

        addLabel(form,"Movie Name",30,35);
        txtName.setBounds(170,35,250,35);
        form.add(txtName);

        addLabel(form,"Genre",440,35);
        txtGenre.setBounds(520,35,250,35);
        form.add(txtGenre);

        addLabel(form,"Language",820,35);
        cmbLanguage.setBounds(920,35,250,35);
        form.add(cmbLanguage);

        addLabel(form,"Duration",30,90);
        txtDuration.setBounds(170,90,250,35);
        form.add(txtDuration);

        addLabel(form,"Poster",440,90);
        txtPoster.setBounds(520,90,250,35);
        form.add(txtPoster);

        addLabel(form,"Release Date",800,90);
        txtReleaseDate.setBounds(920,90,250,35);
       // txtReleaseDate.setText("2026-06-22");
        form.add(txtReleaseDate);

        addLabel(form,"Certificate",30,145);
        txtCertificate.setBounds(170,145,250,35);
        form.add(txtCertificate);

        addLabel(form,"Trailer",440,145);
        txtTrailer.setBounds(520,145,250,35);
        form.add(txtTrailer);

        addLabel(form,"Description",30,200);
        JScrollPane descPane =
        new JScrollPane(txtDescription);
        descPane.setBounds(170,200,700,60);
        form.add(descPane);

        addLabel(form,"Status",820,145);
cmbStatus.setBounds(920,145,250,35);
        form.add(cmbStatus);

        btnSave = new JButton("Save Movie");
        btnSave.setBounds(900,210,120,40);
        btnSave.setBackground(new Color(40,167,69));
        btnSave.setForeground(Color.WHITE);
        form.add(btnSave);

        btnDelete = new JButton("Delete Movie");
       btnDelete.setBounds(1050,210,120,40);
        btnDelete.setBackground(new Color(220,53,69));
        btnDelete.setForeground(Color.WHITE);
        btnDelete.setForeground(Color.WHITE);
        form.add(btnDelete);

        //---------------- TABLE ----------------

        model = new DefaultTableModel();

        model.setColumnIdentifiers(
                new String[]{
                    "ID",
                    "Movie Name",
                    "Genre",
                    "Language",
                    "Duration",
                    "Poster",
                    "Status"
                });

        table = new JTable(model);

        table.setRowHeight(30);
        table.setFont(new Font("Arial",Font.PLAIN,14));
        table.setBackground(new Color(60,63,65));
        table.setForeground(Color.WHITE);
        table.setSelectionBackground(new Color(52,152,219));
        table.setSelectionForeground(Color.WHITE);
        table.setGridColor(Color.GRAY);

        DefaultTableCellRenderer center =
new DefaultTableCellRenderer();

center.setHorizontalAlignment(
SwingConstants.CENTER);

for(int i=0;i<table.getColumnCount();i++){
    table.getColumnModel()
         .getColumn(i)
         .setCellRenderer(center);
}

       JTableHeader header = table.getTableHeader();
        header.setBackground(new Color(33,37,41));
        header.setForeground(Color.WHITE);
        header.setFont(new Font("Segoe UI",Font.BOLD,14));

        JScrollPane sp =
                new JScrollPane(table);

        sp.setBounds(20,430,1460,380);
        add(sp);

        loadMovies();

        //---------------- SAVE ----------------

        btnSave.addActionListener(e -> saveMovie());

        //---------------- DELETE ----------------

        btnDelete.addActionListener(e -> deleteMovie());

        //---------------- SEARCH ----------------

        btnSearch.addActionListener(e -> searchMovie());

        //---------------- REFRESH ----------------

        btnRefresh.addActionListener(e -> refreshForm());

        table.addMouseListener(new MouseAdapter() {

    @Override   
    public void mouseClicked(
            MouseEvent e){

        int row =
                table.getSelectedRow();

        if(row==-1)
            return;

        int movieId =
                Integer.parseInt(
                        model.getValueAt(
                                row,0)
                                .toString());

        MovieModel movie =
                dao.searchMovie(
                        movieId);

        if(movie!=null){

            txtName.setText(
                    movie.getMovieName());

            txtGenre.setText(
                    movie.getGenre());

            cmbLanguage.setSelectedItem(movie.getLanguage());

            txtDuration.setText(
                    String.valueOf(
                            movie.getDurationMinutes()));

            txtPoster.setText(
                    movie.getPosterPath());

            txtDescription.setText(
                    movie.getDescription());

            txtCertificate.setText(
                    movie.getCertificate());

            txtTrailer.setText(
                    movie.getTrailerLink());

            if(movie.getReleaseDate()!=null){

                txtReleaseDate.setText(
                        new SimpleDateFormat(
                                "yyyy-MM-dd")
                                .format(
                                        movie.getReleaseDate()));
            }

            cmbStatus.setSelectedItem(
                    movie.getMovieStatus());
        }
    }
});

    }

        private void addLabel(JPanel p,String text,int x,int y){

                JLabel lbl = new JLabel(text);

                lbl.setBounds(x,y,140,35);
                lbl.setForeground(Color.BLACK);
                lbl.setFont(new Font("Segoe UI",Font.BOLD,18));

                p.add(lbl);
        }

    private void saveMovie(){

        if(txtName.getText().trim().isEmpty()
        || txtGenre.getText().trim().isEmpty()
        || cmbLanguage.getSelectedItem().toString().equals("Select Language")
        || txtDuration.getText().trim().isEmpty()){

            JOptionPane.showMessageDialog(this, "Please Fill All Required Fields");

            return;
        }

        try{

            MovieModel movie =
                    new MovieModel();

            movie.setMovieName(
                    txtName.getText());

            movie.setGenre(
                    txtGenre.getText());

            movie.setLanguage( cmbLanguage.getSelectedItem().toString());

            movie.setDurationMinutes(
                    Integer.parseInt(
                            txtDuration.getText()));

            movie.setDescription(
                    txtDescription.getText());

            movie.setPosterPath(
                    txtPoster.getText());

            movie.setReleaseDate(new SimpleDateFormat( "yyyy-MM-dd").parse( txtReleaseDate.getText()));

            movie.setCertificate(
                    txtCertificate.getText());

            movie.setTrailerLink(
                    txtTrailer.getText());

            movie.setMovieStatus(
                    cmbStatus.getSelectedItem()
                            .toString());

            dao.addMovie(movie);

            loadMovies();

            refreshForm();

            JOptionPane.showMessageDialog(this,"Movie Saved Successfully");

        }catch(Exception e){

            JOptionPane.showMessageDialog(this,e.getMessage());
        }
    }

    private void deleteMovie(){

        int row = table.getSelectedRow();

        if(row==-1){

            JOptionPane.showMessageDialog(
                    this,
                    "Select Movie First");

            return;
        }

        int movieId =
                Integer.parseInt(
                        model.getValueAt(row,0)
                                .toString());
        
        int choice =
        JOptionPane.showConfirmDialog(
                this,
                "Delete Selected Movie ?",
                "Confirm Delete",
                JOptionPane.YES_NO_OPTION);

        if(choice != JOptionPane.YES_OPTION){
        return;
        }
        
        dao.deleteMovie(movieId);
        refreshForm();
        loadMovies();
    }

    private void searchMovie(){

        try{

            int id =
                    Integer.parseInt(
                            txtSearch.getText());

            MovieModel movie =
                    dao.searchMovie(id);

            if(movie==null){

                JOptionPane.showMessageDialog(
                        this,
                        "Movie Not Found");

                return;
            }

            txtName.setText(
                    movie.getMovieName());

            txtGenre.setText(
                    movie.getGenre());

            cmbLanguage.setSelectedItem(
                        movie.getLanguage());

            txtDuration.setText(
                    String.valueOf(
                            movie.getDurationMinutes()));

            txtPoster.setText(
                    movie.getPosterPath());

            txtDescription.setText(
                    movie.getDescription());

            txtCertificate.setText(
                    movie.getCertificate());

            txtTrailer.setText(
                    movie.getTrailerLink());

           if(movie.getReleaseDate()!=null){

                        txtReleaseDate.setText(
                                new SimpleDateFormat(
                                "yyyy-MM-dd")
                                .format(
                                        movie.getReleaseDate()));
                }

                cmbStatus.setSelectedItem(movie.getMovieStatus());         

        }catch(Exception e){

            JOptionPane.showMessageDialog(
                    this,
                    "Invalid Movie ID");
        }
    }

    private void loadMovies(){

        model.setRowCount(0);

        ArrayList<MovieModel> list =
                dao.getMovieList();

        for(MovieModel m : list){

            model.addRow(new Object[]{
                m.getMovieId(),
                m.getMovieName(),
                m.getGenre(),
                m.getLanguage(),
                m.getDurationMinutes(),
                m.getPosterPath(),
                m.getMovieStatus()
            });
        }
    }

    private void refreshForm(){

    txtSearch.setText("");

    txtName.setText("");
    txtGenre.setText("");
    cmbLanguage.setSelectedIndex(0);
    txtDuration.setText("");
    txtPoster.setText("");
    txtReleaseDate.setText("");
    txtCertificate.setText("");
    txtTrailer.setText("");
    txtDescription.setText("");

    cmbStatus.setSelectedIndex(0);

    table.clearSelection();

    loadMovies();
}
}



//JTextField txtLanguage;