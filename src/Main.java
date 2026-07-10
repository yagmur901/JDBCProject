
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;


import java.sql.*;
import java.util.*;

public class Main {

    //static String username = "root";
    //static String password = "**********"; //sifremi girince oluyor
    //static String dbUrl = "jdbc:mysql://localhost:3306/world"; //3306 -> port (server statusta)

    public static void main(String[] args) throws SQLException{

        Connection connection = null;
        DbHelper helper = new DbHelper();

        PreparedStatement statement = null; //sql hazırla
        ResultSet resultSet;

        try {

            connection = helper.getConnection();
            String sql = "delete from city where id = ?"; // delete işlemlerinde parametre olarak id yeterlidir

            statement = connection.prepareStatement(sql);
            statement.setInt(1,4080); // id'si 4080 olan row silindi

            int result = statement.executeUpdate();
            System.out.println("record deleted: " + result + " row(s) affected");





            //1. basit bir connection aç
            //2. bir statement oluştur
            //3. onu execute et

        } catch (SQLException exception) {
            //System.out.println(exception.getMessage());
            helper.showErrorMessage(exception);
        }
        finally {
            statement.close();
            connection.close();
        }


    }

    public static void selectDemo () throws SQLException {
        Connection connection = null;
        DbHelper helper = new DbHelper();

        Statement statement = null;
        ResultSet resultSet;
        try {

            //connection = DriverManager.getConnection(dbUrl,username,password); //Drivermanager.getconnection -> db connection sağlar
            connection = helper.getConnection();
            // System.out.println("Connected to db");

            statement = connection.createStatement();
            resultSet = statement.executeQuery("select code, name, continent, region from country");
            ///!!! sql e yazdığımız satırı execute etmeye yarıyor.
            ///SQL SELECT ifadelerinin çalıştırılması

            ArrayList<Country> countries = new ArrayList<Country>();

            while(resultSet.next()) { // tüm columnları iterateliyor
                //System.out.println(resultSet.getString("Name")); //name sütununu yazdırıyor.
                countries.add(new Country (resultSet.getString("Code"), resultSet.getString("Name"), resultSet.getString("Continent"), resultSet.getString("Region")));
                ///resultSet'in nesnelere aktarılması!!!
            }
            System.out.println(countries.size()); // output: 239 -> tüm countryler

        } catch (SQLException exception) {
            //System.out.println(exception.getMessage());
            helper.showErrorMessage(exception);
        }
        finally {
            connection.close();
        }
    }

    public static void insertDemo() throws SQLException {

        Connection connection = null;
        DbHelper helper = new DbHelper();

        PreparedStatement statement = null; //sql hazırla
        ResultSet resultSet;
        try {

            connection = helper.getConnection();
            //statement = connection.prepareStatement("insert into city (Name, CountryCode, District, Population) values ('Duzce', 'TUR', 'Duzce', 50000)");
            ///INSERT işlemleri böyle yapılır!!!
            // int result = statement.executeUpdate(); // kaç row(s) affected?
            // System.out.println("Connection added : " + result + " row(s) affected."); // insert ile 1 row u etkiliyoruz

            String sql = "insert into city (Name, CountryCode, District, Population) values (?, ?, ?, ?)"; //kullanıcıdan sonradan almak için
            statement = connection.prepareStatement(sql);
            statement.setString(1, "Duzce 2");
            statement.setString(2, "TUR");
            statement.setString(3, "Turkey");
            statement.setInt(4, 70000);
            int result = statement.executeUpdate();





            //1. basit bir connection aç
            //2. bir statement oluştur
            //3. onu execute et

        } catch (SQLException exception) {
            //System.out.println(exception.getMessage());
            helper.showErrorMessage(exception);
        }
        finally {
            statement.close();
            connection.close();
        }
    }


    public static void updateDemo() throws SQLException{

        Connection connection = null;
        DbHelper helper = new DbHelper();

        PreparedStatement statement = null; //sql hazırla
        ResultSet resultSet;

        try {

            connection = helper.getConnection();

            //String sql = "update city set population = 80000, district = 'Duzce2' where id = 4084"; //birden fazla veriyi updatelemek için virgulle ayırıp yazarız.
            String sql = "update city set population = 80000, district = 'turkey' where id = ?"; // ? -> sonradan veri girmek (set etmek) için

            statement = connection.prepareStatement(sql);
            statement.setInt(1,4084); // MySQL workbenchden kontrol edince de değişiklikler oraya yansıyor.

            int result = statement.executeUpdate();
            System.out.println("record updated");


            //1. basit bir connection aç
            //2. bir statement oluştur
            //3. onu execute et

        } catch (SQLException exception) {
            //System.out.println(exception.getMessage());
            helper.showErrorMessage(exception);
        }
        finally {
            statement.close();
            connection.close();
        }


    }




}