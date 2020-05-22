import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.sql.rowset.FilteredRowSet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;


public class JDBCInterface extends JFrame {

    private JPanel controlPanel;
    private JTextArea textQueryArea;
    private JTextField lastNameQuery;

    private JTextField lastNameInsert;
    private JTextField firstNameInsert;
    private JTextField ageInsert;
    private JTextField cityInsert;



    private JButton queryButton;
    private JButton insertButton;


    private Connection conn;
    private PreparedStatement queryStmtLastName;
    private PreparedStatement queryStmtWithNull;
    private PreparedStatement insertStmt;


    private static final int FRAME_WIDTH = 600;
    private static final int FRAME_HEIGHT = 400;
    final int AREA_ROWS = 20;
    final int AREA_COLUMNS = 40;

    public JDBCInterface() {


        try {

            conn = DriverManager.getConnection("jdbc:sqlite:assignment.db");
            queryStmtLastName = conn.prepareStatement("Select * from People WHERE Last = ?");
            queryStmtWithNull = conn.prepareStatement("Select * from People");
            insertStmt = conn.prepareStatement("insert into People (Last, First, age, city) VALUES (?,?,?,?)");

        } catch (SQLException e) {
            System.err.println("Connection error: " + e);
            System.exit(1);
        }

        createControlPanel();
        queryButton.addActionListener(new QueryButtonListener());
        insertButton.addActionListener(new InsertButtonListener());

        textQueryArea = new JTextArea(
                AREA_ROWS, AREA_COLUMNS);
        textQueryArea.setEditable(false);

        /* scrollPanel is optional */
        JScrollPane scrollPane = new JScrollPane(textQueryArea);
        JPanel textPanel = new JPanel();
        textPanel.add(scrollPane);
        this.add(textPanel, BorderLayout.CENTER);
        this.add(controlPanel, BorderLayout.NORTH);
    }

    private JPanel createControlPanel() {

        /* you are going to have to create a much more fully-featured layout */

        controlPanel = new JPanel();
        JPanel inputPanel = new JPanel();
        JPanel insertPanel = new JPanel();
//        JPanel insertInputPanel = new JPanel();

        insertPanel.setLayout(new GridLayout(3,2));

        JLabel lbLast = new JLabel("Last Name");
        JLabel lbFirst = new JLabel("First Name");
        JLabel lbAge = new JLabel("Age");
        JLabel lbCity = new JLabel("City");

        JPanel lastName = new JPanel();
        JPanel firstName = new JPanel();
        JPanel age = new JPanel();
        JPanel city = new JPanel();

        lastName.add(lbLast);
        lastNameInsert = new JTextField(10);
        lastName.add(lastNameInsert);
        insertPanel.add(lastName);

        firstName.add(lbFirst);
        firstNameInsert = new JTextField(10);
        firstName.add(firstNameInsert);
        insertPanel.add(firstName);

        age.add(lbAge);
        ageInsert = new JTextField(10);
        age.add(ageInsert);
        insertPanel.add(age);


        city.add(lbCity);
        cityInsert = new JTextField(10);
        city.add(cityInsert);
        insertPanel.add(city);

        JPanel insert = new JPanel();
        insertButton = new JButton("Insert");
        insertButton.setSize(20,20);
        insert.add(insertButton);
        insertPanel.add(insert);

        JLabel lbl = new JLabel("Last Name:");
        inputPanel.add(lbl);
        lastNameQuery = new JTextField(10);
        queryButton = new JButton("Execute Query");
        inputPanel.add(lastNameQuery);
        inputPanel.add(queryButton);

//        JPanel buttonPanel = new JPanel();
//        buttonPanel.add(queryButton);

        controlPanel.setLayout(new GridLayout(2,2));
        controlPanel.add(insertPanel);
        controlPanel.add(inputPanel);
//        controlPanel.add(buttonPanel);

        return controlPanel;


    }

    class InsertButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            /* You will have to implement this */
            try{

                PreparedStatement stmt;
                String lastName = lastNameInsert.getText();
                String age = ageInsert.getText();
                String firstName = firstNameInsert.getText();
                String city = cityInsert.getText();
                if("".equals(lastName) || "".equals(age) || "".equals(firstName) || "".equals(city)
                || lastName == null || age == null || firstName == null || city == null){
                    JOptionPane.showMessageDialog(null, "All Fields must be filled", "Message", JOptionPane.INFORMATION_MESSAGE);
                    lastNameInsert.setText("");
                    ageInsert.setText("");
                    firstNameInsert.setText("");
                    cityInsert.setText("");
                }else{
                    stmt = insertStmt;
                    stmt.setString(1, lastName);
                    stmt.setString(2, firstName);
                    stmt.setString(3, age);
                    stmt.setString(4, city);
                    lastNameInsert.setText("");
                    ageInsert.setText("");
                    firstNameInsert.setText("");
                    cityInsert.setText("");
                    stmt.executeUpdate();
                    JOptionPane.showMessageDialog(null, "insert successfully", "Insert Message", JOptionPane.INFORMATION_MESSAGE);
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    class QueryButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event)
        {
            /* as far as the columns, it is totally acceptable to
             * get all of the column data ahead of time, so you only
             * have to do it once, and just reprint the string
             * in the text area.
             */

            /* you want to change things here so that if the text of the
             * last name query field is empty, it should query for all rows.
             *
             * For now, if the last name query field is blank, it will execute:
             * SELECT * FROM People WHERE Last=''
             * which will give no results
             */
            try {
                textQueryArea.setText("");
                PreparedStatement stmt;
                String lastNameText = lastNameQuery.getText();
                if("".equals(lastNameText) || lastNameText == null){
                     stmt = queryStmtWithNull;
                }else{
                     stmt = queryStmtLastName;
                     stmt.setString(1, lastNameText);
                }

                ResultSet rset = stmt.executeQuery();
                ResultSetMetaData rsmd = rset.getMetaData();
                int numColumns = rsmd.getColumnCount();

//                System.out.println(rsmd.getColumnLabel(1));

                System.out.println("numcolumns is "+ numColumns);

                String rowString = "";
                String rowLabel = "";
                for(int j = 1; j <= numColumns; j++){
                    rowLabel += rsmd.getColumnLabel(j) + "\t";
                }
                while (rset.next()) {
                    for (int i=1;i<=numColumns;i++) {
                        Object o = rset.getObject(i);
                        rowString += o.toString() + "\t";
                    }
                    rowString += "\n";
                }
                System.out.print("rowString  is  " + rowString);

                textQueryArea.setText(rowLabel + "\n" + rowString);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args)
    {
        JFrame frame = new JDBCInterface();
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
