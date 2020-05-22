import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
* @author Barry
* */
public class WordFinder extends JFrame {

    JMenuBar menuBar;
    JMenu menu;
    JMenuItem menuItem;
    JLabel find;
    JButton clear;

    JFileChooser jFileChooser;
    private JPanel topPanel;
    WordList wordList;
    JTextField wordField;
    JTextArea res;

    public WordFinder() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // set the size correctly
        setSize(400, 400);

        jFileChooser = new JFileChooser(".");
        wordList = new WordList();

        find = new JLabel("Find:");
        find.setLocation(0,10);

        wordField = new JTextField(10);
        wordField.setLocation(30,40);
        wordField.setSize(100, 30);

        clear = new JButton("Clear");
        clear.setSize(100, 30);
        clear.setLocation(30, 150);
        clear.addActionListener((e) -> wordField.setText(""));

        createMenus();
        // there should be objects in the top panel
        topPanel = new JPanel();
        topPanel.add(find);
        topPanel.add(wordField);
        topPanel.add(clear);
        wordField.addCaretListener(new SearchListener());
        add(topPanel, BorderLayout.NORTH);

        // need to add scroll pane to output the result
        // There should probably be something passed into the JScrollPane
        JScrollPane listScroller = new JScrollPane();
        listScroller.setPreferredSize(new Dimension(250, 150));
        res = new JTextArea();
        listScroller.add(res);
        listScroller.setViewportView(res);
        add(listScroller, BorderLayout.CENTER);
        // and of course you will want them to be properly aligned in the frame
    }

    private void createMenus() {
        menuBar = new JMenuBar();
        menu = new JMenu("File");
        JMenuItem open = new JMenuItem("Open");
        JMenuItem exit = new JMenuItem("Exit");
        open.addActionListener(new OpenFileListener());
        exit.addActionListener((event) -> System.exit(0));
        menu.add(open);
        menu.add(exit);
        menuBar.add(menu);
        this.setJMenuBar(menuBar);
    }

    class SearchListener implements CaretListener {
        @Override
        public void caretUpdate(CaretEvent e) {
            res.setText("");
            List searchResult = wordList.find(wordField.getText()); // figure out from WordList how to get this
            for (Object s : searchResult) {
                res.append(s + "\n");
                res.setCaretPosition(0);
            }
        }
    }

    class OpenFileListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int returnVal = jFileChooser.showOpenDialog(getParent());
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                System.out.println("You chose to open this file: " + jFileChooser.getSelectedFile().getAbsolutePath());
                try{
                    InputStream in = new FileInputStream(jFileChooser.getSelectedFile().getAbsolutePath());
                    wordList.load(in);
                }catch (IOException err){
                    err.printStackTrace();
                }
                List searchResult = wordList.find(wordField.getText()); // figure out from WordList how to get this
                for (Object s : searchResult) {
                    res.append(s + "\n");
                    res.setCaretPosition(0);
                }
            }
        }
    }

    public static void main(String[] args) {
        WordFinder wordFinder = new WordFinder();
        wordFinder.setVisible(true);
    }
}
