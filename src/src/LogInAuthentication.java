import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class LogInAuthentication {

    private String userInputEmail;
    private String userInputPassword;
    private boolean correctCredentials = false;

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
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT log_in_email, log_in_password FROM staff");

            while (resultSet.next())
            {
                String staffEmail = resultSet.getString("log_in_email");
                String staffPassword = resultSet.getString("log_in_password");
                if (staffEmail.equals(userInputEmail) && staffPassword.equals(userInputPassword))
                {
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
}
