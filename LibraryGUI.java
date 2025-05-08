import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LibraryGUI extends JFrame {
    private LibraryService libraryService;

    private JTextField bookIdField, bookTitleField, bookAuthorField;
    private JTextField userIdField, userNameField;
    private JTextField issueBookIdField, issueUserIdField;
    private JTextField returnBookIdField;

    private JTextArea outputArea;

    public LibraryGUI() {
        libraryService = new LibraryService();

        // Set up the JFrame
        setTitle("Library Management System");
        setSize(900, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        
        // Set application icon
        try {
            ImageIcon icon = new ImageIcon(getClass().getResource("/library_icon.png"));
            setIconImage(icon.getImage());
        } catch (Exception e) {
            // If icon not found, continue without it
        }

        // Create a tabbed pane with better styling
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setFont(new Font("Segoe UI", Font.BOLD, 14));
        tabbedPane.setBackground(new Color(240, 240, 240));

        // Panel for adding books and users
        JPanel addPanel = createAddPanel();
        tabbedPane.addTab("Add Items", addPanel);

        // Panel for issuing and returning books
        JPanel managePanel = createManagePanel();
        tabbedPane.addTab("Manage Books", managePanel);

        // Panel for displaying books and users
        JPanel displayPanel = createDisplayPanel();
        tabbedPane.addTab("View Records", displayPanel);

        // Output area for feedback with better styling
        outputArea = new JTextArea(5, 5);
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Consolas", Font.PLAIN, 12));
        outputArea.setBackground(new Color(250, 250, 250));
        outputArea.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200)),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        JScrollPane scrollPane = new JScrollPane(outputArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("System Messages"));

        // Header panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(70, 130, 180));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JLabel titleLabel = new JLabel("Library Management System");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        headerPanel.add(titleLabel);

        // Add components to the JFrame
        add(headerPanel, BorderLayout.NORTH);
        add(tabbedPane, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);

        // Center the window
        setLocationRelativeTo(null);
    }

    private JPanel createAddPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        panel.setBackground(new Color(240, 248, 255));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        // Add Book Section
        JPanel bookPanel = new JPanel(new GridBagLayout());
        bookPanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(70, 130, 180), 10),
            "Add New Book", 
            javax.swing.border.TitledBorder.LEFT,
            javax.swing.border.TitledBorder.DEFAULT_POSITION,
            new Font("Segoe UI", Font.BOLD, 14),
            new Color(70, 130, 180)));
        bookPanel.setBackground(new Color(240, 248, 255));
        bookPanel.add(new JLabel("Book ID:"), gbc);
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        bookIdField = createStyledTextField();
        bookPanel.add(bookIdField, gbc);
        bookPanel.add(new JLabel("Book Title:"), gbc);
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        bookTitleField = createStyledTextField();
        bookPanel.add(bookTitleField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        bookPanel.add(new JLabel("Book Author:"), gbc);
        gbc.gridx = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        bookAuthorField = createStyledTextField();
        bookPanel.add(bookAuthorField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.CENTER;
        JButton addBookButton = createStyledButton("Add Book", new Color(241, 239, 250));
        addBookButton.setBackground(new Color(70, 70, 70));
        addBookButton.setForeground(Color.BLACK);
        addBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = bookIdField.getText().trim();
                String title = bookTitleField.getText().trim();
                String author = bookAuthorField.getText().trim();

                if (id.isEmpty() || title.isEmpty() || author.isEmpty()) {
                    showErrorMessage("Please fill all fields for adding a book.");
                } else {
                    libraryService.addBook(id, title, author);
                    outputArea.append("[" + getCurrentTime() + "] Book added: " + title + " by " + author + "\n");
                    clearFields(bookIdField, bookTitleField, bookAuthorField);
                }
            }
        });
        bookPanel.add(addBookButton, gbc);

        // Add User Section
        JPanel userPanel = new JPanel(new GridBagLayout());
        userPanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(70, 130, 180), 10),
            "Add New User", 
            javax.swing.border.TitledBorder.LEFT,
            javax.swing.border.TitledBorder.DEFAULT_POSITION,
            new Font("Segoe UI", Font.BOLD, 14),
            new Color(70, 130, 180)));
        userPanel.setBackground(new Color(240, 248, 255));

        //gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        userPanel.add(new JLabel("User ID:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        //gbc.gridwidth = 1;
        userIdField = createStyledTextField();
        userPanel.add(userIdField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        userPanel.add(new JLabel("User Name:"), gbc);
        gbc.gridx = 1;
        userNameField = createStyledTextField();
        userPanel.add(userNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.CENTER;
        JButton addUserButton = createStyledButton("Add User", new Color(70, 139, 139));
        addUserButton.setBackground(new Color(70, 70, 70));
        addUserButton.setForeground(Color.BLACK);
        addUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = userIdField.getText().trim();
                String name = userNameField.getText().trim();

                if (id.isEmpty() || name.isEmpty()) {
                    showErrorMessage("Please fill all fields for adding a user.");
                } else {
                    libraryService.addUser(id, name);
                    outputArea.append("[" + getCurrentTime() + "] User added: " + name + "\n");
                    clearFields(userIdField, userNameField);
                }
            }
        });
        userPanel.add(addUserButton, gbc);

        // Add both panels to main panel
        panel.setLayout(new GridLayout(2, 1, 10, 10));
        panel.add(bookPanel);
        panel.add(userPanel);

        return panel;
    }

    private JPanel createManagePanel() {
        JPanel panel = new JPanel(new GridLayout(2, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        panel.setBackground(new Color(240, 248, 255));

        // Issue Book Section
        JPanel issuePanel = new JPanel(new GridBagLayout());
        issuePanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(70, 130, 180), 10),
            "Issue Book", 
            javax.swing.border.TitledBorder.LEFT,
            javax.swing.border.TitledBorder.DEFAULT_POSITION,
            new Font("Segoe UI", Font.BOLD, 14),
            new Color(70, 130, 180)));
        issuePanel.setBackground(new Color(240, 248, 255));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0;
        gbc.gridy = 0;
        issuePanel.add(new JLabel("Book ID:"), gbc);
        gbc.gridx = 1;
        issueBookIdField = createStyledTextField();
        issuePanel.add(issueBookIdField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        issuePanel.add(new JLabel("User ID:"), gbc);
        gbc.gridx = 1;
        issueUserIdField = createStyledTextField();
        issuePanel.add(issueUserIdField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.CENTER;
        JButton issueBookButton = createStyledButton("Issue Book", new Color(65, 105, 225));
        issueBookButton.setBackground(new Color(70, 70, 70));
        issueBookButton.setForeground(Color.BLACK);
        issueBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String bookId = issueBookIdField.getText().trim();
                String userId = issueUserIdField.getText().trim();

                if (bookId.isEmpty() || userId.isEmpty()) {
                    showErrorMessage("Please fill all fields for issuing a book.");
                } else {
                    libraryService.issueBook(bookId, userId);
                    outputArea.append("[" + getCurrentTime() + "] Book issued: Book ID " + bookId + " to User ID " + userId + "\n");
                    clearFields(issueBookIdField, issueUserIdField);
                }
            }
        });
        issuePanel.add(issueBookButton, gbc);

        // Return Book Section
        JPanel returnPanel = new JPanel(new GridBagLayout());
        returnPanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(70, 130, 180), 10),
            "Return Book", 
            javax.swing.border.TitledBorder.LEFT,
            javax.swing.border.TitledBorder.DEFAULT_POSITION,
            new Font("Segoe UI", Font.BOLD, 14),
            new Color(70, 130, 180)));
        returnPanel.setBackground(new Color(240, 248, 255));

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        returnPanel.add(new JLabel("Book ID:"), gbc);
        gbc.gridx = 1;
        returnBookIdField = createStyledTextField();
        returnPanel.add(returnBookIdField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.CENTER;
        JButton returnBookButton = createStyledButton("Return Book", new Color(220, 20, 60));
        returnBookButton.setBackground(new Color(70, 70, 70));
        returnBookButton.setForeground(Color.BLACK);
        returnBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String bookId = returnBookIdField.getText().trim();

                if (bookId.isEmpty()) {
                    showErrorMessage("Please enter a book ID for returning a book.");
                } else {
                    libraryService.returnBook(bookId);
                    outputArea.append("[" + getCurrentTime() + "] Book returned: Book ID " + bookId + "\n");
                    clearFields(returnBookIdField);
                }
            }
        });
        returnPanel.add(returnBookButton, gbc);

        panel.add(issuePanel);
        panel.add(returnPanel);

        return panel;
    }

    private JPanel createDisplayPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        panel.setBackground(new Color(240, 248, 255));

        JTextArea displayArea = new JTextArea(15, 40);
        displayArea.setEditable(false);
        displayArea.setFont(new Font("Consolas", Font.PLAIN, 12));
        displayArea.setBackground(new Color(250, 250, 250));
        displayArea.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200)),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        JScrollPane scrollPane = new JScrollPane(displayArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Records View"));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        buttonPanel.setBackground(new Color(240, 248, 255));

        JButton displayBooksButton = createStyledButton("Display All Books", new Color(70, 130, 180));
        displayBooksButton.setBackground(new Color(70, 70, 70));
        displayBooksButton.setForeground(Color.BLACK);
        displayBooksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayArea.setText("=== Books in Library ===\n\n");
                // This should be modified to return a String that can be displayed
                // libraryService.displayBooks() should return String instead of void
                displayArea.append("Book ID\tTitle\t\tAuthor\tStatus\n");
                displayArea.append("--------------------------------------------\n");
                // Example data - replace with actual data from libraryService
                displayArea.append("B001\tJava Programming\tJohn Doe\tAvailable\n");
                displayArea.append("B002\tPython Basics\tJane Smith\tChecked Out\n");
            }
        });

        JButton displayUsersButton = createStyledButton("Display All Users", new Color(70, 130, 180));
        displayUsersButton.setBackground(new Color(70, 70, 70));
        displayUsersButton.setForeground(Color.BLACK);
        displayUsersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayArea.setText("=== Users in Library ===\n\n");
                // This should be modified to return a String that can be displayed
                // libraryService.displayUsers() should return String instead of void
                displayArea.append("User ID\tName\t\tBooks Borrowed\n");
                displayArea.append("--------------------------------------------\n");
                // Example data - replace with actual data from libraryService
                displayArea.append("U001\tAlice Johnson\t1\n");
                displayArea.append("U002\tBob Williams\t0\n");
            }
        });

        JButton clearButton = createStyledButton("Clear Display", new Color(169, 169, 169));
        clearButton.setBackground(new Color(70, 70, 70));
        clearButton.setForeground(Color.BLACK);
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayArea.setText("");
            }
        });

        buttonPanel.add(displayBooksButton);
        buttonPanel.add(displayUsersButton);
        buttonPanel.add(clearButton);

        panel.add(buttonPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    private JTextField createStyledTextField() {
        JTextField textField = new JTextField(20);
        textField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        textField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(150, 150, 150)),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        return textField;
    }

    private JButton createStyledButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Add hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor.darker());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor);
            }
        });
        
        return button;
    }

    private void clearFields(JTextField... fields) {
        for (JTextField field : fields) {
            field.setText("");
        }
    }

    private void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(this, 
            message, 
            "Input Error", 
            JOptionPane.ERROR_MESSAGE);
    }

    private String getCurrentTime() {
        return new java.text.SimpleDateFormat("HH:mm:ss").format(new java.util.Date());
    }

    public static void main(String[] args) {
        // Set system look and feel for native appearance
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Run the GUI on the Event Dispatch Thread
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                LibraryGUI gui = new LibraryGUI();
                gui.setVisible(true);
                
                // Add window icon if available
                try {
                    gui.setIconImage(Toolkit.getDefaultToolkit().getImage(
                        LibraryGUI.class.getResource("/library_icon.png")));
                } catch (Exception e) {
                    // Icon not found, continue without it
                }
            }
        });
    }
}