package finalproject.server;

import finalproject.db.DBInterface;
import finalproject.entities.Person;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Barry
 * pay attention to directory of db file when you run the code
 * the client.db and server.db file need to be placed under the directory of FinalProject
 * because the driver function is: "jdbc:sqlite:FinalProject/server.db"
 */
public class Server extends JFrame implements Runnable {

    private static final int DEFAULT_PORT = 8001;
    private static final int FRAME_WIDTH = 1000;
    private static final int FRAME_HEIGHT = 800;
    private final int AREA_ROWS = 10;
    private final int AREA_COLUMNS = 40;

    DBInterface dbInterface;

    private JMenuBar jMenuBar;
    JPanel headPanel;
    JTextArea resultArea = new JTextArea(AREA_ROWS, AREA_COLUMNS);;
    JButton queryDb;


    ObjectInputStream inputFromClient = null;
    DataOutputStream outputToClient = null;

    private int clientNum = 0;

    public Server() throws IOException, SQLException {
        this(DEFAULT_PORT, "server.db");
    }

    private Server(int port, String dbFile) throws IOException, SQLException {
        this.setSize(Server.FRAME_WIDTH, Server.FRAME_HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Thread t = new Thread(this);
        t.start();
    }

    /**
     * initialize the UI of server
     * */
    public Server(String dbFile) throws IOException, SQLException {
        this(DEFAULT_PORT, dbFile);

        dbInterface = new DBInterface();
        dbInterface.setConnection("jdbc:sqlite:FinalProject/server.db");

        this.setLayout(new GridLayout(2, 1));

        // menu bar
        JMenu menu = new JMenu("File");
        menu.add(createExitItem());
        jMenuBar = new JMenuBar();
        jMenuBar.add(menu);
        this.setJMenuBar(jMenuBar);

        // db label
        headPanel = new JPanel(new GridLayout(2,1));
        headPanel.setSize(400,200);
        Label db = new Label("DB:");
        Label dbName = new Label("server.db");
        JPanel labelPanel = new JPanel();
        labelPanel.add(db);
        labelPanel.add(dbName);
        headPanel.add(labelPanel);

        // query button
        JPanel queryButton = new JPanel();
        queryDb = new JButton("Query DB");
        queryDb.addActionListener(new QueryDbListener());
        queryButton.add(queryDb);
        headPanel.add(queryButton);
        this.add(headPanel);

        // result area (log area)
        resultArea.setEditable(false);
        resultArea.setText("Listening on port " + DEFAULT_PORT + '\n');
        resultArea.setSize(1000,1000);
        JScrollPane scrollPane = new JScrollPane(resultArea);
        scrollPane.setSize(1000,1000);
        JPanel textPanel = new JPanel();
        textPanel.setSize(1000,1000);
        textPanel.add(scrollPane);
        this.add(textPanel);
    }

    /**
     * exit item in top menu
     * */
    private JMenuItem createExitItem(){
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
     * get result from People table in server.db
     * */
    class QueryDbListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Connection con = null;
            PreparedStatement preparedStatement;
            try{
                con = dbInterface.getConn();
                String sql = "select * from People";
                preparedStatement = con.prepareStatement(sql);
                ResultSet resultSet = preparedStatement.executeQuery();
                ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
                int columnNum = resultSetMetaData.getColumnCount();
                Set<String> columns = new HashSet<>();
                resultArea.append('\n' + "DB Results:" + '\n');
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

    @Override
    public void run() {
        ServerSocket serverSocket = null;
        try{
            serverSocket = new ServerSocket(DEFAULT_PORT);
            // add current time
            resultArea.append("connection starts at " + new Date() + "\n");

            while(true){
                // always listen to the port
                Socket socket = serverSocket.accept();
                    // update client num
                clientNum++;
                resultArea.append("\nstart thread for client " + clientNum + " at " + new Date() + "\n");
                InetAddress inetAddress = socket.getInetAddress();
                resultArea.append("Client " + clientNum + "'s host name is "
                        + inetAddress.getHostName() + "\n");
                resultArea.append("Client " + clientNum + "'s IP Address is "
                        + inetAddress.getHostAddress() + "\n");
                resultArea.append("Listening to the input from client " + clientNum + "\n");
                // create new thread for ClientHandler
                new Thread(new ClientHandler(socket, clientNum)).start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    class ClientHandler implements Runnable{
        private Socket socket;
        private int clientNum;

        public ClientHandler(Socket socket, int clientNum) {
            this.socket = socket;
            this.clientNum = clientNum;
        }

        @Override
        public void run() {
            while(true){
                try {
                    // get the data out of the port
                    inputFromClient = new ObjectInputStream(socket.getInputStream());
                    outputToClient = new DataOutputStream(socket.getOutputStream());

                    Object obj = inputFromClient.readObject();
                    Person person = (Person) obj;
                    if(person == null){
                        throw new ClassNotFoundException("the server does not get valid person object");
                    }
                    // try to print the person out
                    resultArea.append("\n" + "got person Person " + person.toString() + " inserting into db");
                    outputToClient.writeBytes("Success" + "\n");
                    outputToClient.flush();
                    try{
                        Connection con = dbInterface.getConn();
                        String sql = "insert into People (first, last, age, sent, city, id)" +
                                "values(?, ?, ?, 1, ?, ?)";
                        PreparedStatement insertStatement = con.prepareStatement(sql);
                        insertStatement.setString(1, person.getFirstName());
                        insertStatement.setString(2, person.getLastName());
                        insertStatement.setInt(3, person.getAge());
                        insertStatement.setString(4, person.getCity());
                        insertStatement.setInt(5, person.getID());
                        insertStatement.execute();
                        resultArea.append("\ninserted successfully" + "\n");
                    }catch (SQLException ex){
                        outputToClient.writeBytes("Insert Fail" + "\n");
                        outputToClient.flush();
                        ex.printStackTrace();
                    }
                    Thread.sleep(1);
                }catch (IOException | ClassNotFoundException | InterruptedException e){

                    resultArea.append("IO Error:" + e.getMessage() + " ending connection\n");
                    try{
                        if(outputToClient != null){
                            outputToClient.writeBytes("Fail" + "\n");
                            outputToClient.flush();
                        }else{
                            throw new IOException("outputToClient is null");
                        }
                    }catch (IOException ex){
                        ex.printStackTrace();
                    }
                    e.printStackTrace();
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        Server sv;
        try {
            sv = new Server("server.db");
            sv.setSize(500, 400);
            sv.setVisible(true);
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }
}
