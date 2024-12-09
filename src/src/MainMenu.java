import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class MainMenu extends JFrame
{
    private final JFrame frame;
    private final int frame_width;
    private final int frame_height;
    private JPanel central_panel;
    private final String user_name;
    private final int staff_id;

    MainMenu(String user_name, int staff_id)
    {
        this.user_name = user_name;
        this.staff_id = staff_id;
        frame = new JFrame();
        frame_width = 1200;
        frame_height = 700;
        frame.setSize(frame_width, frame_height);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        ImageIcon image = new ImageIcon("src/ImageDirectory/library_logo.png");
        frame.setIconImage(image.getImage());
        frame.setLayout(new BorderLayout());
        this.navigation_bar_panel();
        this.information_panel();
        this.CRUD_panel();

        frame.setVisible(true);
    }

    private void navigation_bar_panel()
    {
        TextFieldComponent text_field_component = new TextFieldComponent();
        JPanel navigation_bar = new JPanel();
        navigation_bar.setLayout(new BoxLayout(navigation_bar, BoxLayout.X_AXIS));
        navigation_bar.setPreferredSize(new Dimension(0, (int)(frame_height * 0.1)));
        navigation_bar.setBackground(new Color(0xffde59));

        ImageIcon person_logo = new ImageIcon("src/ImageDirectory/person_logo.png");
        Image scaled_img = person_logo.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        ImageIcon final_logo_size = new ImageIcon(scaled_img);
        JLabel image = new JLabel(final_logo_size);
        image.setCursor(new Cursor(Cursor.HAND_CURSOR));

        image.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e)
            {
                int user_log_out = JOptionPane.showConfirmDialog
                        (
                                frame,
                                "You Wish to log out?",
                                "Log out",
                                JOptionPane.OK_CANCEL_OPTION
                        );

                if(user_log_out == JOptionPane.OK_OPTION)
                {
                    frame.dispose();
                    new LogInForm();
                }
            }
        });

        image.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));

        JPanel search_container = new JPanel();
        search_container.setLayout(new BorderLayout());
        search_container.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        search_container.setPreferredSize(new Dimension((int) (frame_width * 0.46), 45));
        search_container.setMaximumSize(new Dimension((int) (frame_width * 0.46), 45));
        search_container.setOpaque(false);

        JTextField search_bar = text_field_component.textFieldPanel("Search Bar");
        search_bar.setPreferredSize(new Dimension((int) (frame_width * 0.45), 45)); // Set width and height
        search_bar.setMaximumSize(new Dimension((int) (frame_width * 0.45), 45));
        search_bar.setFont(new Font("Arial", Font.PLAIN, 20));

        ImageIcon search_logo = new ImageIcon("src/ImageDirectory/search_logo.png");
        Image scaled_img_1 = search_logo.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon search_logo_size = new ImageIcon(scaled_img_1);

        JButton search_button = button_rounded_corner(search_logo_size, true);
        search_button.setPreferredSize(new Dimension((int) (frame_width * 0.05), 45));
        search_button.setMaximumSize(new Dimension((int) (frame_width * 0.05), 45));
        search_button.setFocusable(false);
        search_button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        search_button.setBackground(Color.WHITE);

        search_button.addActionListener(e ->
                {
                    System.out.println("Search Bar Text: " + search_bar.getText());
                    System.out.println("User name: " + user_name);
                }
        );

        search_button.addMouseListener(new MouseAdapter()
        {
            final Color default_color = Color.WHITE;
            final Color hover_color = new Color(0xe6e6e6);

            @Override
            public void mouseEntered(MouseEvent e)
            {
                search_button.setBackground(hover_color);
            }

            @Override
            public void mouseExited(MouseEvent e)
            {
                search_button.setBackground(default_color);
            }
        });

        search_container.add(search_bar, BorderLayout.WEST);
        navigation_bar.add(search_container);
        navigation_bar.add(search_button);
        navigation_bar.add(Box.createHorizontalGlue());
        navigation_bar.add(image);
        frame.add(navigation_bar, BorderLayout.NORTH);
    }

    private void information_panel()
    {
        TableModel tableModel = new TableModel();
        central_panel = new JPanel();
        central_panel.setBackground(new Color(0xffffff));
        central_panel.addComponentListener(new ComponentAdapter()
        {
            @Override
            public void componentResized(ComponentEvent e)
            {
                JScrollPane scroll_pane = tableModel.scroll_pane_table(central_panel.getWidth(), central_panel.getHeight() , 1);
                central_panel.add(scroll_pane);
            }
        });

        frame.add(central_panel);
    }

    private void CRUD_panel()
    {
        TableModel tableModel = new TableModel();
        TextFieldComponent text_field_component = new TextFieldComponent();

        JPanel west_panel = new JPanel();
        west_panel.setPreferredSize(new Dimension(300, 0));
        west_panel.setLayout(new BoxLayout(west_panel, BoxLayout.Y_AXIS));
        west_panel.setBackground(new Color(0xffffff));

        JButton add_button = button_rounded_corner(null, false);
        crud_button_attribute(add_button, "Add");

        JButton update_button = button_rounded_corner(null, false);
        crud_button_attribute(update_button, "Update");

        JButton all_transaction = button_rounded_corner(null, false);
        crud_button_attribute(all_transaction, "All Transaction");

        JButton today_transaction_button = button_rounded_corner(null, false);
        crud_button_attribute(today_transaction_button, "Today's Transaction");

        JButton pending_return = button_rounded_corner(null, false);
        crud_button_attribute(pending_return, "Pending Return");

        JButton return_book = button_rounded_corner(null, false);
        crud_button_attribute(return_book, "Returned");

        add_button.addActionListener(e ->
        {
            ComboBoxData combo_box_data = new ComboBoxData();
            InputChecker input_checker = new InputChecker();
            BookDetails book_details = new BookDetails();
            AddBorrowerData add_borrower_data = new AddBorrowerData();

            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

            JTextField first_name_tf = text_field_component.textFieldPanel("First name");
            JTextField middle_name_tf = text_field_component.textFieldPanel("Middle name");
            JTextField last_name_tf = text_field_component.textFieldPanel("Last name");
            JTextField student_id = text_field_component.textFieldPanel("Student ID");
            JTextField email_address = text_field_component.textFieldPanel("Email address");
            JTextField contact_number = text_field_component.textFieldPanel("Contact number");
            JTextField location = text_field_component.textFieldPanel("Location");
            JTextField quantity = text_field_component.textFieldPanel("Quantity");
            JComboBox<String> book_name = new JComboBox<>(combo_box_data.return_available_book());

            panel.add(first_name_tf);
            panel.add(Box.createRigidArea(new Dimension(0, 10)));
            panel.add(middle_name_tf);
            panel.add(Box.createRigidArea(new Dimension(0, 10)));
            panel.add(last_name_tf);
            panel.add(Box.createRigidArea(new Dimension(0, 10)));
            panel.add(student_id);
            panel.add(Box.createRigidArea(new Dimension(0, 10)));
            panel.add(email_address);
            panel.add(Box.createRigidArea(new Dimension(0, 10)));
            panel.add(contact_number);
            panel.add(Box.createRigidArea(new Dimension(0, 10)));
            panel.add(location);
            panel.add(Box.createRigidArea(new Dimension(0, 10)));
            panel.add(quantity);
            panel.add(Box.createRigidArea(new Dimension(0, 10)));
            panel.add(book_name);

            Object[] options = {"Add", "Back"};
            int input_data = JOptionPane.showOptionDialog
            (
                    frame,
                    panel,
                    "Enter borrower details",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]
            );

            if(input_data == JOptionPane.OK_OPTION)
            {
                if(
                        !input_checker.isLetterChecker(first_name_tf.getText()) ||
                        !input_checker.isLetterChecker(middle_name_tf.getText()) ||
                        !input_checker.isLetterChecker(last_name_tf.getText()) ||
                        !input_checker.isValidStudentID(student_id.getText()) ||
                        !input_checker.isValidEmail(email_address.getText()) ||
                        !input_checker.isNumberChecker(contact_number.getText()) ||
                        !input_checker.isValidContactNumber(contact_number.getText()) ||
                        !input_checker.isNumberChecker(quantity.getText())
                )
                {
                    JOptionPane.showMessageDialog
                            (
                                    frame,
                                    "Invalid student details input",
                                    "Invalid Borrowed Details",
                                    JOptionPane.ERROR_MESSAGE
                            );
                }
                else if(
                        first_name_tf.getText().equals("First name")||
                        middle_name_tf.getText().equals("Middle name") ||
                        last_name_tf.getText().equals("Last name") ||
                        location.getText().equals("Location")
                )
                {
                    JOptionPane.showMessageDialog
                            (
                                    frame,
                                    "Some student details is missing",
                                    "Invalid Borrowed Details",
                                    JOptionPane.ERROR_MESSAGE
                            );
                }
                else if(book_details.BookInfo((String) book_name.getSelectedItem())[0] <  Integer.parseInt(quantity.getText()))
                {
                    JOptionPane.showMessageDialog
                            (
                                    frame,
                                    "The current quantity of the book titled \"" + book_name.getSelectedItem() + "\" is " + book_details.BookInfo((String) book_name.getSelectedItem())[0],
                                    "Invalid Book Quantity",
                                    JOptionPane.ERROR_MESSAGE
                            );
                }
                else
                {
                    add_borrower_data.addStudentTable(
                            Long.parseLong(student_id.getText()),
                            first_name_tf.getText(),
                            middle_name_tf.getText(),
                            last_name_tf.getText(),
                            email_address.getText(),
                            contact_number.getText(),
                            location.getText()
                    );
                    add_borrower_data.addTransactionTable(
                            book_details.BookInfo((String) book_name.getSelectedItem())[1],
                            staff_id, add_borrower_data.getStudent_id(),
                            Integer.parseInt(quantity.getText())
                    );
                    JOptionPane.showMessageDialog
                            (
                                    frame,
                                    "Successfully added the borrowed details"
                            );
                }

            }
        });

        update_button.addActionListener(e ->
        {
            UpdateBorrowerData updateBorrowerData = new UpdateBorrowerData();
             String user_input = JOptionPane.showInputDialog
                                (
                                        frame,
                                        "Transaction #:",
                                        "Borrower Returned Book",
                                        JOptionPane.PLAIN_MESSAGE
                                );


             if(user_input == null)
             {
                 JOptionPane.showMessageDialog
                         (
                                 frame,
                                 "The Update is cancel",
                                 "Cancel Update",
                                 JOptionPane.PLAIN_MESSAGE
                         );
             }
             else if(!user_input.isEmpty())
             {
                 if(updateBorrowerData.validate_transaction_number(Integer.parseInt(user_input)))
                 {
                     Object[] get_date = updateBorrowerData.borrower_return_info(Integer.parseInt(user_input));
                     long student_id = (long) get_date[0];
                     String student_name = (String) get_date[1];
                     int quantity = (int) get_date[2];
                     Date borrower_date = (Date) get_date[3];

                     int user_choose = JOptionPane.showConfirmDialog
                             (
                                     frame,
                                     "Student ID: " + student_id + "\n" +
                                             "Student Name: " + student_name + "\n" +
                                             "Quantity: " + quantity + "\n" +
                                             "Borrower Date: " + borrower_date,
                                     "Borrower Details",
                                     JOptionPane.OK_CANCEL_OPTION
                             );

                     if(user_choose == JOptionPane.OK_OPTION)
                     {
                         updateBorrowerData.update_returned_date(Integer.parseInt(user_input));
                     }

                 }
                 else
                 {
                     JOptionPane.showMessageDialog
                             (
                                     frame,
                                     "Transaction # is not found or the borrower already return the book"
                            );
                 }
             }
             else
             {
                 JOptionPane.showMessageDialog
                         (
                                 frame,
                                 "Transaction # is empty",
                                 "Error",
                                 JOptionPane.ERROR_MESSAGE
                         );
             }

        });

        all_transaction.addActionListener(e ->
        {
            central_panel.removeAll();
            JScrollPane scroll_panel = tableModel.scroll_pane_table(central_panel.getWidth(), central_panel.getHeight(), 1);
            central_panel.add(scroll_panel);
            central_panel.repaint();
            central_panel.revalidate();
        }
        );
        today_transaction_button.addActionListener(e ->
        {
            central_panel.removeAll();
            JScrollPane scrollPane = tableModel.scroll_pane_table(central_panel.getWidth(), central_panel.getHeight(),4);
            central_panel.add(scrollPane);
            central_panel.repaint();
            central_panel.revalidate();
        });
        pending_return.addActionListener(e ->
        {
            central_panel.removeAll();
            JScrollPane scrollPane = tableModel.scroll_pane_table(central_panel.getWidth(), central_panel.getHeight(),2);
            central_panel.add(scrollPane);
            central_panel.revalidate();
            central_panel.repaint();
        });
        return_book.addActionListener(e ->
        {
            central_panel.removeAll();
            JScrollPane scrollPane = tableModel.scroll_pane_table(central_panel.getWidth(), central_panel.getHeight(),3);
            central_panel.add(scrollPane);
            central_panel.revalidate();
            central_panel.repaint();
        });

        west_panel.add(Box.createVerticalGlue());
        west_panel.add(add_button);
        west_panel.add(Box.createRigidArea(new Dimension(0, 10)));
        west_panel.add(update_button);
        west_panel.add(Box.createRigidArea(new Dimension(0, 10)));
        west_panel.add(all_transaction);
        west_panel.add(Box.createRigidArea(new Dimension(0, 10)));
        west_panel.add(today_transaction_button);
        west_panel.add(Box.createRigidArea(new Dimension(0, 10)));
        west_panel.add(pending_return);
        west_panel.add(Box.createRigidArea(new Dimension(0, 10)));
        west_panel.add(return_book);
        west_panel.add(Box.createVerticalGlue());

        frame.add(west_panel, BorderLayout.WEST);
    }

    private void crud_button_attribute(JButton button, String button_message)
    {
        button.setPreferredSize(new Dimension((int) (frame_width * 0.2), 45));
        button.setMaximumSize(new Dimension((int) (frame_width * 0.2), 45));
        button.setText(button_message);
        button.setBackground(new Color(0x38b6ff));
        button.setFont(new Font("Arial", Font.PLAIN, 20));
        button.setFocusable(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);

        button.addMouseListener(new MouseAdapter()
        {
            final Color default_back_ground_color = new Color(0x38b6ff);
            final Color hover_color = new Color(0xe6e6e6);

            @Override
            public void mouseEntered(MouseEvent e)
            {
                button.setBackground(hover_color);
            }

            @Override
            public void mouseExited(MouseEvent e)
            {
                button.setBackground(default_back_ground_color);
            }
        });
    }

    private JButton button_rounded_corner(ImageIcon image, boolean has_image)
    {
        JButton button_corner;
        if(has_image)
        {
             button_corner = new JButton(image)
             {
                 @Override protected void paintComponent(Graphics g) {
                     if (!isOpaque() && getBorder() instanceof LogInForm.RoundedCornerBorder) {
                         Graphics2D g2 = (Graphics2D) g.create();
                         g2.setPaint(getBackground());
                         g2.fill(((LogInForm.RoundedCornerBorder) getBorder()).getBorderShape(
                                 0, 0, getWidth() - 1, getHeight() - 1));
                     }
                     super.paintComponent(g);
                 }
                 @Override public void updateUI() {
                     super.updateUI();
                     setOpaque(false);
                     setBorder(new LogInForm.RoundedCornerBorder());
                 }
             };
        }
        else
        {
             button_corner = new JButton()
             {
                 @Override protected void paintComponent(Graphics g) {
                     if (!isOpaque() && getBorder() instanceof LogInForm.RoundedCornerBorder) {
                         Graphics2D g2 = (Graphics2D) g.create();
                         g2.setPaint(getBackground());
                         g2.fill(((LogInForm.RoundedCornerBorder) getBorder()).getBorderShape(
                                 0, 0, getWidth() - 1, getHeight() - 1));
                     }
                     super.paintComponent(g);
                 }
                 @Override public void updateUI() {
                     super.updateUI();
                     setOpaque(false);
                     setBorder(new LogInForm.RoundedCornerBorder());
                 }
             };
        }
        return button_corner;
    }

//    public static void main(String[] args) {
//        new MainMenu("WEw", 1);
//    }
}
