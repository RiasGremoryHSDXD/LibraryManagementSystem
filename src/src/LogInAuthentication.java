import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class LogInAuthentication {

    private String userInputEmail;
    private String userInputPassword;
    private boolean correctCredentials = false;
    private String librarian_user_name;

    // Constructor
    public LogInAuthentication(String userInputEmail, String userInputPassword)
    {
        this.userInputEmail = userInputEmail;
        this.userInputPassword = userInputPassword;
    }

    // Validate credentials against the database
    public void validateCredentials() {
        String url = "jdbc:mysql://127.0.0.1:3306/library_management_system";
        String user = "root";
        String password = "";
        try {
             Connection connection = DriverManager.getConnection(url, user, password);
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
                            "SELECT CONCAT(last_name, ', ', first_name, ' ', middle_name) AS staff_name FROM staff WHERE log_in_email = '" + staffEmail + "'");
                    if (user_name.next())
                    {
                        librarian_user_name = user_name.getString("staff_name");
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
}
