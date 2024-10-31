public class Main {
    public static void main(String[] args) throws Exception {
        new AuthenticationPage();
        SQLiteConnection connection = new SQLiteConnection();
        connection.getConnection();
    }
}
