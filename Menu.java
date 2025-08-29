import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Date;

class Menu extends JFrame {
    Menu(String userName) {
        setTitle("Library System - Welcome " + userName);
        setSize(500,300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(3,2,10,10));

        JButton btnAddBook = new JButton("Add Book");
        JButton btnAddMember = new JButton("Add Member");
        JButton btnViewBooks = new JButton("View Books");
        JButton btnViewMembers = new JButton("View Members");
        JButton btnSearchBook = new JButton("Search Book");
        JButton btnIssue = new JButton("Issue Book");
        JButton btnReturn = new JButton("Return Book");
        JButton btnOverdue = new JButton("Overdue Books");
        JButton btnLogout = new JButton("Logout");

        add(btnAddBook);
        add(btnAddMember);
        add(btnViewBooks);
        add(btnViewMembers);
        add(btnSearchBook);
        add(btnIssue);
        add(btnReturn);
        add(btnOverdue);
        add(btnLogout);

        btnAddBook.addActionListener(e -> new AddBook().setVisible(true));
        btnAddMember.addActionListener(e -> new AddMember().setVisible(true));
        btnViewBooks.addActionListener(e -> new ViewBooks().setVisible(true));
        btnViewMembers.addActionListener(e -> new ViewMembers().setVisible(true));
        btnSearchBook.addActionListener(e -> new SearchBook().setVisible(true));
        btnIssue.addActionListener(e -> new IssueBook().setVisible(true));
        btnReturn.addActionListener(e -> new ReturnBook().setVisible(true));
        btnOverdue.addActionListener(e -> new Overdue().setVisible(true));
        btnLogout.addActionListener(e -> {
            dispose();
            new Login().setVisible(true);
        });
        
        setLocationRelativeTo(null);
        setVisible(true);
    }
}