package jdbcGUI;

import java.awt.*;
import java.awt.event.*;
import java.awt.print.*;
import java.sql.*;

import javax.swing.*;
import javax.swing.table.*;
import javax.swing.border.*;



/**
 * Created by Shibu on 5/30/2015.
 */
public class ViewStudents extends JFrame implements ActionListener{
    JFrame frame = new JFrame();
    JLabel iconLabel1, iconLabel2;
    JButton printButton, exitButton;
    JLabel label;

    private ResultSetTable tableModel;
    JTable resultTable;

    public ViewStudents() throws ClassNotFoundException, SQLException{
        frame.setTitle("PeopleNTech");

        Icon icon1 = new ImageIcon("images/logo1.png");
        Icon icon2 = new ImageIcon("images/logo2.png");

        iconLabel1 = new JLabel();
        iconLabel1.setIcon(icon1);
        iconLabel1.setBounds(100, 40, 300, 160);

        iconLabel2 = new JLabel();
        iconLabel2.setIcon(icon2);
        iconLabel2.setBounds(400, 40, 400, 160);

        tableModel = new ResultSetTable( "com.mysql.jdbc.Driver", "jdbc:mysql:///peoplentech",
                "SELECT * FROM Students" );

        // create JTable delegate for tableModel
        resultTable = new JTable( tableModel );
        resultTable.setPreferredScrollableViewportSize(new Dimension(800, 300));
        resultTable.setFillsViewportHeight(true);
        resultTable.setBackground(new Color(205,255,255));
        resultTable.setForeground(new Color(205,0,205));
        JScrollPane scrollPane = new JScrollPane(resultTable);
        scrollPane.setBounds( 100, 240, 800, 300 );
        scrollPane.setBorder( createCompoundBorder( )  );

        //set column size
        int[] columnsWidth = { 100, 100, 100, 100, 200, 100, 100 };

        int i = 0;
        for (int width : columnsWidth) {
            TableColumn column = resultTable.getColumnModel().getColumn(i++);
            column.setMinWidth(width);
            column.setMaxWidth(width);
            column.setPreferredWidth(width);
        }

        DisplayMode dMode = null;
        GraphicsConfiguration gc = getGraphicsConfiguration();
        GraphicsDevice gd = gc.getDevice();
        dMode = gd.getDisplayMode();
        int width = dMode.getWidth();
        int height = dMode.getHeight();

        label = new JLabel("Students Currently In The List");
        label.setFont(new Font("Ariel", Font.BOLD, 14));
        label.setBounds( 100, 600, 300, 24 );
        label.setBackground(new Color(205,255,255));

        printButton = new JButton("Print");
        printButton.setBounds( 720, 700, 80, 24 );
        printButton.addActionListener(ViewStudents.this);
        printButton.setForeground(new Color(205,0,205));
        printButton.setBackground(new Color(205,255,205));

        exitButton = new JButton("Exit");
        exitButton.setBounds( 820, 700, 80, 24 );
        exitButton.addActionListener(ViewStudents.this);
        exitButton.setForeground(new Color(205,0,205));
        exitButton.setBackground(new Color(205,255,205));

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();

        JPanel mainPanel = new JPanel();
        mainPanel.setBounds( 0, 0, dimension.width, dimension.height );
        mainPanel.setLayout( null );
        mainPanel.setBackground(new Color(205,255,255));
        mainPanel.add(iconLabel1);
        mainPanel.add(iconLabel2);
        mainPanel.add( scrollPane );
        mainPanel.add( label );
        mainPanel.add( printButton );
        mainPanel.add( exitButton );

        add(mainPanel);

        Container c = frame.getContentPane();
        c.setLayout( null );
        c.add( mainPanel );
        c.setVisible( true );

        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible( true );
    }


    public void actionPerformed(ActionEvent ae) {
        if(ae.getActionCommand() == "Print"){
            try {
                resultTable.print();
            }catch (PrinterException pe) {}
        }

        if(ae.getActionCommand() == "Exit"){
            frame.dispose();
        }
    }


    public Border createCompoundBorder( ) {
        Border raisedbevel = BorderFactory.createRaisedBevelBorder();
        Border loweredbevel = BorderFactory.createLoweredBevelBorder();
        Border redline = BorderFactory.createLineBorder(Color.red);
        Border compound;
        compound = BorderFactory.createCompoundBorder(raisedbevel, loweredbevel);
        return compound = BorderFactory.createCompoundBorder(redline, compound);
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException{
        try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch(Exception e){}

        new ViewStudents();

    }
}


class ResultSetTable extends AbstractTableModel{

    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    private ResultSetMetaData metaData;
    private int numberOfRows;

    // keep track of database connection status
    private boolean connectedToDatabase = false;

    // initialize resultSet and obtain its meta data object; determine number of rows
    public ResultSetTable( String driver, String url, String query )
            throws SQLException, ClassNotFoundException{
        // load database driver class
        Class.forName( driver );

        // connect to database
        connection = DriverManager.getConnection( url, "root", "sddev" );

        // create Statement to query database
        statement =
                connection.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY );

        // update database connection status
        connectedToDatabase = true;

        // set query and execute it
        setQuery( query );
    }

    // get class that represents column type
    public Class getColumnClass( int column ) throws IllegalStateException {
        // ensure database connection is available
        if ( !connectedToDatabase )
            throw new IllegalStateException( "Not Connected to Database" );

        // determine Java class of column
        try {
            String className = metaData.getColumnClassName( column + 1 );

            // return Class object that represents className
            return Class.forName( className );
        }

        // catch SQLExceptions and ClassNotFoundExceptions
        catch ( Exception exception ) {
            exception.printStackTrace();
        }

        // if problems occur above, assume type Object
        return Object.class;
    }

    // get number of columns in ResultSet
    public int getColumnCount() throws IllegalStateException  {
        // ensure database connection is available
        if ( !connectedToDatabase )
            throw new IllegalStateException( "Not Connected to Database" );

        // determine number of columns
        try {
            return metaData.getColumnCount();
        }

        // catch SQLExceptions and print error message
        catch ( SQLException sqlException ) {
            sqlException.printStackTrace();
        }

        // if problems occur above, return 0 for number of columns
        return 0;
    }

    // get name of a particular column in ResultSet
    public String getColumnName( int column ) throws IllegalStateException  {
        // ensure database connection is available
        if ( !connectedToDatabase )
            throw new IllegalStateException( "Not Connected to Database" );

        // determine column name
        try {
            return metaData.getColumnName( column + 1 );
        }

        // catch SQLExceptions and print error message
        catch ( SQLException sqlException ) {
            sqlException.printStackTrace();
        }

        // if problems, return empty string for column name
        return "";
    }

    // return number of rows in ResultSet
    public int getRowCount() throws IllegalStateException {
        // ensure database connection is available
        if ( !connectedToDatabase )
            throw new IllegalStateException( "Not Connected to Database" );

        return numberOfRows;
    }

    // obtain value in particular row and column
    public Object getValueAt( int row, int column )  throws IllegalStateException  {
        // ensure database connection is available
        if ( !connectedToDatabase )
            throw new IllegalStateException( "Not Connected to Database" );

        // obtain a value at specified ResultSet row and column
        try {
            resultSet.absolute( row + 1 );

            return resultSet.getObject( column + 1 );
        }

        // catch SQLExceptions and print error message
        catch ( SQLException sqlException ) {
            sqlException.printStackTrace();
        }

        // if problems, return empty string object
        return "";
    }

    // set new database query string
    public void setQuery( String query ) throws SQLException, IllegalStateException  {
        // ensure database connection is available
        if ( !connectedToDatabase )
            throw new IllegalStateException( "Not Connected to Database" );

        // specify query and execute it
        resultSet = statement.executeQuery( query );

        // obtain meta data for ResultSet
        metaData = resultSet.getMetaData();

        // determine number of rows in ResultSet
        resultSet.last();                   // move to last row
        numberOfRows = resultSet.getRow();  // get row number

        // notify JTable that model has changed
        fireTableStructureChanged();
    }

    // close Statement and Connection
    public void disconnectFromDatabase() {
        // close Statement and Connection
        try {
            statement.close();
            connection.close();
        } catch ( SQLException sqlException ) {
            sqlException.printStackTrace();
        }
    }
}

