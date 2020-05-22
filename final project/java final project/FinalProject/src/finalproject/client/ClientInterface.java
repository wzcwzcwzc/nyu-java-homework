package finalproject.client;

import finalproject.db.DBInterface;
import finalproject.entities.Person;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Barry
 *
 * pay attention to directory of db file when you run the code
 * the server.db file need to be placed under the directory of FinalProject
 * */
public class ClientInterface extends JFrame {

    private static final long serialVersionUID = 1L;

    public static final int DEFAULT_PORT = 8001;

    private static final int FRAME_WIDTH = 600;
    private static final int FRAME_HEIGHT = 400;
    final int AREA_ROWS = 10;
    final int AREA_COLUMNS = 40;

    /**
     * database connection interface
     * */
    DBInterface dbInterface;
    Connection con;
    PreparedStatement preparedStatement;


    Label activeDb;
    Label activeDbContent;

    Label activeConnection;
    Label activeConnectionContent;

    JComboBox peopleSelect;
    JFileChooser jFileChooser;
    JPanel clientPanel;
    Socket socket;
    JMenuBar menuBar;
    JButton openCon;
    JButton closeCon;
    JButton sendData;
    JButton queryData;
    JTextArea resultArea;

    OutputStream os = null;
    BufferedOutputStream bos = null;
    ObjectOutputStream oos = null;

    int port;


    public ClientInterface() {
        this(DEFAULT_PORT);
        this.setLayout(new GridLayout(2,1));
        // create file chooser function
        jFileChooser = new JFileChooser(".");

        // create menu bar
        JMenu menu = createFileMenu();
        menuBar = new JMenuBar();
        menuBar.add(menu);
        this.setJMenuBar(menuBar);

        // client panel function
        getClientPanel();
        this.add(clientPanel);

        // result area (log area)
        resultArea = new JTextArea(AREA_ROWS, AREA_COLUMNS);
        resultArea.setEditable(false);

        /* scrollPanel is optional */
        JScrollPane scrollPane = new JScrollPane(resultArea);
        JPanel textPanel = new JPanel();
        textPanel.add(scrollPane);
        this.add(textPanel);
    }

    public ClientInterface(int port) {
        this.port = port;
    }

    private void getClientPanel(){
        clientPanel = new JPanel();
        clientPanel.setLayout(new GridLayout(5,1));

        activeDb = new Label("ActiveDB:");
        activeDbContent = new Label("<None>");
        activeConnection = new Label("Active Connection:");
        activeConnectionContent = new Label("<None>");
        activeConnectionContent.setSize(200,30);
        peopleSelect = new JComboBox();

        // active db label part
        JPanel adb = new JPanel();
        adb.add(activeDb);
        adb.add(activeDbContent);

        // active connection label part
        JPanel aCon = new JPanel();
        aCon.add(activeConnection);
        aCon.add(activeConnectionContent);

        // JCombobox part
        JPanel combo = new JPanel();
        combo.setSize(300,100);
        peopleSelect.setSize(200,30);
        peopleSelect.setModel(new DefaultComboBoxModel(new String[]{"<Empty>"}));
        combo.add(peopleSelect);

        // connection button panel
        JPanel conButton = new JPanel();
        openCon = new JButton("Open Connection");
        closeCon = new JButton("Close Connection");
        openCon.addActionListener(new OpenConnectionListener());
        closeCon.addActionListener(new CloseConnectionListener());
        conButton.add(openCon);
        conButton.add(closeCon);


        //send query data button panel
        JPanel dataHandle = new JPanel();
        sendData = new JButton("Send Data");
        queryData = new JButton("Query DB Data");
        sendData.addActionListener(new SendButtonListener());
        queryData.addActionListener(new QueryButtonListener());
        dataHandle.add(sendData);
        dataHandle.add(queryData);

        this.clientPanel.add(adb);
        this.clientPanel.add(aCon);
        this.clientPanel.add(combo);
        this.clientPanel.add(conButton);
        this.clientPanel.add(dataHandle);
    }

    /**
     * exit function in menu bar of the client interface
     */
    private JMenuItem createFileExitItem(){
        JMenuItem item = new JMenuItem("Exit");
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        return item;
    }

    /**
     * create top menu for client interface
     * */
    public JMenu createFileMenu()
    {
        JMenu menu = new JMenu("File");
        menu.add(createFileOpenItem());
        menu.add(createFileExitItem());
        return menu;
    }

    private void fillComboBox() throws SQLException {
        List<ComboBoxItem> l = getNames();
        peopleSelect.setModel(new DefaultComboBoxModel(l.toArray()));
    }

    private void clearComboBox() throws SQLException{
        List<ComboBoxItem> list = new ArrayList<>();
        peopleSelect.setModel(new DefaultComboBoxModel(list.toArray()));
    }


    /**
     * open file function in menu bar of the client interface
     */
    private JMenuItem createFileOpenItem(){
        JMenuItem item = new JMenuItem("Open DB");
        class OpenDbListener implements ActionListener
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                int returnVal = jFileChooser.showOpenDialog(getParent());
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    System.out.println("You chose to open this file: " + jFileChooser.getSelectedFile().getAbsolutePath());
                    String dbFileName = jFileChooser.getSelectedFile().getAbsolutePath();
                    try {
                        connectToDB(dbFileName);
                        activeDbContent.setText(dbFileName.substring(dbFileName.lastIndexOf("/")+1));
                        clearComboBox();
                        fillComboBox();
                    } catch (Exception e ) {
                        System.err.println("error connection to db: "+ e.getMessage());
                        e.printStackTrace();
                        activeDbContent.setText("<None>");
                        try{
                            clearComboBox();
                        }catch (SQLException e1){
                            e1.printStackTrace();
                        }
                    }
                }
            }
        }
        item.addActionListener(new OpenDbListener());
        return item;
    }

    /**
     * connectToDB: diver to connect to client.db
     */
    private void connectToDB(String dbName){
        try{
            dbInterface = new DBInterface();
            dbInterface.setConnection("jdbc:sqlite:" + dbName);
//            dbInterface.setConnection("jdbc:sqlite:FinalProject/" + dbName.substring(dbName.lastIndexOf("/")+1));
            con = dbInterface.getConn();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    class SendButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                ComboBoxItem personEntry = (ComboBoxItem)peopleSelect.getSelectedItem();

                String lastName = null;
                String firstName = null;
                if(personEntry != null){
                    firstName = personEntry.name.trim().split(" ")[0];
                    lastName = personEntry.name.trim().split(" ")[1];
                }

                String sql = "select * from People where first =" + "\'" + firstName + "\'" +
                        " and " + "last=" + "\'" + lastName + "\'";
                preparedStatement = con.prepareStatement(sql);
                ResultSet resultSet = preparedStatement.executeQuery();

                int age = Integer.parseInt(resultSet.getString("age"));
                String city = resultSet.getString("city");
                int id = Integer.parseInt(resultSet.getString("id"));
                Person p = new Person(firstName, lastName, age, city, id);
                os = socket.getOutputStream();
                bos = new BufferedOutputStream(os);
                oos = new ObjectOutputStream(bos);
                oos.writeObject(p);
                os.flush();
                bos.flush();
                oos.flush();
                String response = br.readLine();
                if (response.contains("Success")) {
                    String updateSql = "update People set sent = true where id = " + id;
                    preparedStatement = con.prepareStatement(updateSql);
                    preparedStatement.executeUpdate();
                    fillComboBox();
                    resultArea.append("\nserver response: Success");
                } else {
                    throw new IOException("server response: Failed");
                }

            } catch (IOException | SQLException e1) {
                resultArea.append("\n" + e1.getMessage());
                System.out.println(e1.getMessage());
            }
        }
    }

    private List<ComboBoxItem> getNames() {
        // establish connection with database and do query on data
        List<ComboBoxItem> ans = new ArrayList<>();
        try{
            String sql = "select first,last, id from People where sent=false";
            preparedStatement = con.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            while(resultSet.next()){
                String name = resultSet.getString(1) + " " + resultSet.getString(2);
                int id = Integer.parseInt(resultSet.getString("id"));
                ComboBoxItem comboBoxItem = new ComboBoxItem(id, name);
                ans.add(comboBoxItem);
            }
            return ans;
        }catch(SQLException e){
            System.out.println("combobox getName exception:" + e.getMessage());
        }
        return null;
    }

    /**
     * query database data
     * */
    class QueryButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent event){
            try{
                String sql = "select * from People";
                preparedStatement = con.prepareStatement(sql);
                ResultSet resultSet = preparedStatement.executeQuery();
                ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
                int columnNum = resultSetMetaData.getColumnCount();
                Set<String> columns = new HashSet<>();
                resultArea.append("\n");
                for(int i = 1; i <= columnNum; i++){
                    resultArea.append(resultSetMetaData.getColumnLabel(i) + "\t");
                    columns.add(resultSetMetaData.getColumnName(i));
                }
                resultArea.append("\n");
                for(int i = 1; i <= columnNum; i++){
                    resultArea.append("-----" + "\t");
                }
                resultArea.append("\n");

                while(resultSet.next()){
                    StringBuilder result = new StringBuilder();
                    for(int i = 1; i <= columnNum; i++){
                        result.append(resultSet.getString(i));
                        result.append("\t");
                    }
                    resultArea.append(result.toString() + "\n");
                }
            }catch (SQLException ex){
                ex.printStackTrace();
            }
        }
    }

    class OpenConnectionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            try{
                socket = new Socket("localhost",8001);
                System.out.println("connection established");
                activeConnectionContent.setText(socket.getInetAddress().getHostName() + ":" + socket.getPort());
            }catch (IOException e1){
                e1.printStackTrace();
                resultArea.append("\n" + e1.getMessage());
                System.out.println("connection fail");
            }
        }
    }

    class CloseConnectionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            try{
                if(socket.isClosed()){
                    System.out.println("socket already closed");
                    resultArea.append("\nsocket already closed");
                }else{
                    socket.close();
                    resultArea.append("\nthe socket closed");
                    System.out.println("the socket closed");
                }
            }catch (IOException ex){
                ex.printStackTrace();
            }finally{
                try{
                    if(os != null){
                        os.close();
                    }
                    if(bos != null){
                        bos.close();
                    }

                    if(oos != null){
                        oos.close();
                    }
                }catch (IOException ex){
                    System.out.println(ex.getMessage());
                }
            }
        }
    }

    class ComboBoxItem {
        private int id;
        private String name;

        public ComboBoxItem(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return this.id;
        }

        public String getName() {
            return this.name;
        }

        @Override
        public String toString() {
            return this.name;
        }
    }

    public static void main(String[] args) {
        ClientInterface ci = new ClientInterface();
        ci.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ci.setSize(500, 400);
        ci.setVisible(true);
    }
}

