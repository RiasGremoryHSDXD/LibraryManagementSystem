public class InputChecker
{
    public boolean isLetterChecker(String letterChecker)
    {
        return letterChecker.matches("[a-zA-Z\\s]+");
    }

    public boolean isValidEmail(String email)
    {
        return email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{3,}");
    }

    public boolean isValidStudentID(String student_id)
    {
        return student_id.matches("[0-9]{10}");
    }

    public boolean isValidContactNumber(String contact_number)
    {
        return contact_number.matches("09[0-9]{9}");
    }
    public boolean isNumberChecker(String numberChecker)
    {
        return numberChecker.matches("^\\d+$");
    }
}
