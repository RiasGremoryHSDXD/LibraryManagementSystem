public class InputChecker
{
    public boolean isLetterChecker(String letterChecker)
    {
        return letterChecker.matches("[a-zA-Z\\s]+");
    }

    public boolean isNumberChecker(String numberChecker)
    {
        return numberChecker.matches("^\\d+$");
    }
}
