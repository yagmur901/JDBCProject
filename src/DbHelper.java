import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//ilk deneme yaptığımızda maine yazdığımız metotların toplanmış halini içeren class.

public class DbHelper {

    static String username = "root";
    static String password = "**********"; //mysql sifrem
    static String dbUrl = "jdbc:mysql://localhost:3306/world"; //3306 -> port (server statusta)


    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(dbUrl,username,password); //Drivermanager.getconnection -> db connection sağlar
    } // daha önce mainde yaptığımız işlemi ayrı classa taşıdık, farklı bir şey yok.

    public void showErrorMessage(SQLException exception) {
        System.out.println("Error: " + exception.getMessage());
        System.out.println("Error Code : " + exception.getErrorCode());

    }



}
