import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class LogInAuthentication {

    DataBaseConnection db_connect = new DataBaseConnection();
    private String userInputEmail;
    private String userInputPassword;
    private boolean correctCredentials = false;
    private String librarian_user_name;
    private int librarian_staff_id;

    // Constructor
    public LogInAuthentication(String userInputEmail, String userInputPassword)
    {
        this.userInputEmail = userInputEmail;
        this.userInputPassword = userInputPassword;
    }

    // Validate credentials against the database
    public void validateCredentials() {
        try {
            Connection connection = DriverManager.getConnection(db_connect.get_connect()[0], db_connect.get_connect()[1], db_connect.get_connect()[2]);
             Statement log_in_query = connection.createStatement();
             ResultSet resultSet = log_in_query.executeQuery("SELECT log_in_email, log_in_password FROM staff");

            while (resultSet.next())
            {
                String staffEmail = resultSet.getString("log_in_email");
                String staffPassword = resultSet.getString("log_in_password");
                if (staffEmail.equals(userInputEmail) && staffPassword.equals(userInputPassword))
                {
                    Statement user_name_query = connection.createStatement();
                    ResultSet user_name = user_name_query.executeQuery(
                            "SELECT staff_id, CONCAT(last_name, ', ', first_name, ' ', middle_name) AS staff_name FROM staff WHERE log_in_email = '" + staffEmail + "'");
                    if (user_name.next())
                    {
                        librarian_user_name = user_name.getString("staff_name");
                        librarian_staff_id = user_name.getInt("staff_id");
                    }
                    correctCredentials = true;
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setUserInputEmail(String userInputEmail)
    {
        this.userInputEmail = userInputEmail;
    }

    public  void  setUserInputPassword(String userInputPassword)
    {
        this.userInputPassword = userInputPassword;
    }
    public boolean isCorrectCredentials()
    {
        return correctCredentials;
    }

    public String getLibrarianUserName()
    {
        return librarian_user_name;
    }

    public int getLibrarianUserID()
    {
        return librarian_staff_id;
    }
}
