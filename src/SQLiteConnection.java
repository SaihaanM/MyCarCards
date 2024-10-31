import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;


public class SQLiteConnection {
    private Connection connect() 
    {
        Connection conn = null;
        try
        {
            Class.forName("org.sqlite.JDBC");
        }
        catch(ClassNotFoundException e)
        {
            e.printStackTrace();
        }

        try {
            String url = "jdbc:sqlite:C:\\Users\\saiha\\OneDrive\\Desktop\\Computer Science IA\\Computer Science IA Code\\Computer Science Internal Assesment\\src\\db.db";
            conn = DriverManager.getConnection(url);
        } 
        catch (SQLException SQLError) {
            SQLError.printStackTrace();
        } 
        return conn;
    }

    public Connection getConnection()
    {
        return this.connect();
    }


    
    public void addCarDetails(int keyValue,
                              String brand, String name, String driveType,
                              String handling, String topSpeed, String zeroToSixty, String carClass, String fileName)
    {
        String query = "INSERT INTO Card_Data_Table(Key_Value, Brand, Name, Drive_Type, Top_Speed, Handling, Zero_To_Sixty, Class, File_Name) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try(Connection conn = this.connect(); PreparedStatement prepState = conn.prepareStatement(query))
        {
            prepState.setInt(1, keyValue);
            prepState.setString(2, brand);
            prepState.setString(3, name);
            prepState.setString(4, driveType);
            prepState.setString(5, topSpeed);
            prepState.setString(6, handling);
            prepState.setString(7, zeroToSixty);
            prepState.setString(8, carClass);
            prepState.setString(9, fileName);
            prepState.executeUpdate();
        }
        catch(SQLException SQLError)
        {
            SQLError.printStackTrace();
        }
    }

    public ArrayList<String> searchCardKeyLike(String userCard)
    {
        ArrayList<String> nameResultsIn = new ArrayList<String>();
        String query = "SELECT Key_Value "
                        + "FROM Card_Data_Table WHERE Name LIKE ?";
        try(Connection conn = this.connect(); PreparedStatement prepState = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE))
        {
            prepState.setString(1, "%" + userCard + "%");
            ResultSet results = prepState.executeQuery();
            if(!results.isBeforeFirst())
            {
                nameResultsIn = null;
            }
            else
            {
                while(results.next() == true)
                {
                    int currentKey = results.getInt("Key_Value");
                    String currentName = findExactCarName(currentKey);
                    nameResultsIn.add(currentName);
                }
            }
        }
        catch(SQLException SQLError)
        {
            SQLError.printStackTrace();
        }
        return nameResultsIn;
    }

    public String findExactCarName(int keyValueInteger)
    {
        String carNameFound = null;
        String query = "Select Name "
                        + "FROM Card_Data_Table WHERE Key_Value = ?";
        try(Connection conn = this.connect(); PreparedStatement prepState = conn.prepareStatement(query))
        {
            prepState.setInt(1, keyValueInteger);
            ResultSet results = prepState.executeQuery();
            carNameFound = results.getString("Name");
        }
        catch(SQLException SQLError)
        {
            SQLError.printStackTrace();
        }
        return carNameFound;
    }

    public int findCardKeyValue(String cardName)
    {
        int cardKey = 0;
        String query = "Select Key_Value "
                        + "FROM Card_Data_Table WHERE Name = ?";
        try(Connection conn = this.connect(); PreparedStatement prepState = conn.prepareStatement(query))
        {
            prepState.setString(1, cardName);
            ResultSet results = prepState.executeQuery();
            cardKey = results.getInt("Key_Value");
        }
        catch(SQLException SQLError)
        {
            SQLError.printStackTrace();
        }
        return cardKey;
    }

    public int getCurrentKeyValue()
    {
        int currentKeyValue = 0;
        String query = "SELECT Key_Value " 
                        + "FROM Card_Data_Table ORDER BY Key_Value DESC LIMIT 1";
        try(Connection conn = this.connect(); PreparedStatement prepState = conn.prepareStatement(query))
        {
            ResultSet results = prepState.executeQuery();
            currentKeyValue = results.getInt("Key_Value");
        }
        catch(SQLException SQLError)
        {
            SQLError.printStackTrace();
        }
        return currentKeyValue;
    }

    public String findExactDriveType(int keyValueInteger)
    {
        String driveTypeFound = null;
        String query = "Select Drive_Type "
                        + "FROM Card_Data_Table WHERE Key_Value = ?";
        try(Connection conn = this.connect(); PreparedStatement prepState = conn.prepareStatement(query))
        {
            prepState.setInt(1, keyValueInteger);
            ResultSet results = prepState.executeQuery();
            driveTypeFound = results.getString("Drive_Type");
        }
        catch(SQLException SQLError)
        {
            SQLError.printStackTrace();
        }
        return driveTypeFound;
    }

    public String findExactCarBrand(int keyValueInteger)
    {
        String carBrandFound = null;
        String query = "Select Brand "
                        + "FROM Card_Data_Table WHERE Key_Value = ?";
        try(Connection conn = this.connect(); PreparedStatement prepState = conn.prepareStatement(query))
        {
            prepState.setInt(1, keyValueInteger);
            ResultSet results = prepState.executeQuery();
            carBrandFound = results.getString("Brand");
        }
        catch(SQLException SQLError)
        {
            SQLError.printStackTrace();
        }
        return carBrandFound;
    }

    public String findExactCarClass(int keyValueInteger)
    {
        String carClassFound = null;
        String query = "Select Class "
                        + "FROM Card_Data_Table WHERE Key_Value = ?";
        try(Connection conn = this.connect(); PreparedStatement prepState = conn.prepareStatement(query))
        {
            prepState.setInt(1, keyValueInteger);
            ResultSet results = prepState.executeQuery();
            carClassFound = results.getString("Class");
        }
        catch(SQLException SQLError)
        {
            SQLError.printStackTrace();
        }
        return carClassFound;
    }

    public double findExactSpeed(int keyValueInteger)
    {
        double carSpeedFound = 0;
        String query = "Select Top_Speed "
                        + "FROM Card_Data_Table WHERE Key_Value = ?";
        try(Connection conn = this.connect(); PreparedStatement prepState = conn.prepareStatement(query))
        {
            prepState.setInt(1, keyValueInteger);
            ResultSet results = prepState.executeQuery();
            carSpeedFound = results.getDouble("Top_Speed");
        }
        catch(SQLException SQLError)
        {
            SQLError.printStackTrace();
        }
        return carSpeedFound;
    }

    public double findExactZeroToSixty(int keyValueInteger)
    {
        double carZeroToSixtyFound = 0;
        String query = "Select Zero_To_Sixty "
                        + "FROM Card_Data_Table WHERE Key_Value = ?";
        try(Connection conn = this.connect(); PreparedStatement prepState = conn.prepareStatement(query))
        {
            prepState.setInt(1, keyValueInteger);
            ResultSet results = prepState.executeQuery();
            carZeroToSixtyFound = results.getDouble("Zero_To_Sixty");
        }
        catch(SQLException SQLError)
        {
            SQLError.printStackTrace();
        }
        return carZeroToSixtyFound;
    }

    public double findExactHandling(int keyValueInteger)
    {
        double carHandlingFound = 0;
        String query = "Select Handling "
                        + "FROM Card_Data_Table WHERE Key_Value = ?";
        try(Connection conn = this.connect(); PreparedStatement prepState = conn.prepareStatement(query))
        {
            prepState.setInt(1, keyValueInteger);
            ResultSet results = prepState.executeQuery();
            carHandlingFound = results.getDouble("Handling");
        }
        catch(SQLException SQLError)
        {
            SQLError.printStackTrace();
        }
        return carHandlingFound;
    }

    public String findExactFileName(int keyValueInteger)
    {
        String fileNameFound = null;
        String query = "Select File_Name "
                        + "FROM Card_Data_Table WHERE Key_Value = ?";
        try(Connection conn = this.connect(); PreparedStatement prepState = conn.prepareStatement(query))
        {
            prepState.setInt(1, keyValueInteger);
            ResultSet results = prepState.executeQuery();
            fileNameFound = results.getString("File_Name");
        }
        catch(SQLException SQLError)
        {
            SQLError.printStackTrace();
        }
        return fileNameFound;
    }

    public void editCardDetails(int keyValue,
                                String brand, String name, String driveType,
                                String handling, String topSpeed, String zeroToSixty, String carClass, String fileName)
    {
        String query = "UPDATE Card_Data_Table " +
                        "SET Brand = ?, Name = ?, Drive_Type = ?, Top_Speed = ?, Handling = ?, Zero_To_Sixty = ?, Class = ?, File_Name = ?" + 
                        "WHERE Key_Value = ?";

        try(Connection conn = this.connect(); PreparedStatement prepState = conn.prepareStatement(query))
        {
            prepState.setString(1, brand);
            prepState.setString(2, name);
            prepState.setString(3, driveType);
            prepState.setString(4, topSpeed);
            prepState.setString(5, handling);
            prepState.setString(6, zeroToSixty);
            prepState.setString(7, carClass);
            prepState.setString(8, fileName);
            prepState.setInt(9, keyValue);
            prepState.executeUpdate();
        }
        catch(SQLException SQLError)
        {
            SQLError.printStackTrace();
        }
    }

    public void removeCard(int keyValue)
    {
        String query = "DELETE FROM Card_Data_Table WHERE Key_Value = ?";

        try(Connection conn = this.connect(); PreparedStatement prepState = conn.prepareStatement(query))
        {
            prepState.setInt(1, keyValue);
            prepState.executeUpdate();
        }
        catch(SQLException SQLError)
        {
            SQLError.printStackTrace();
        }
    }

    public ArrayList<String> sortNameAsc()
    {
        String query = "SELECT Name FROM Card_Data_Table ORDER BY Name DESC";
        ArrayList<String> sortedCardsOrder = new ArrayList<>();

        try(Connection conn = this.connect(); PreparedStatement prepState = conn.prepareStatement(query))
        {
            ResultSet results = prepState.executeQuery();
            if(!results.isBeforeFirst())
            {
                sortedCardsOrder = null;
            }
            else
            {
                while(results.next() == true)
                {
                    String currentName = results.getString("Name");
                    sortedCardsOrder.add(currentName);
                }
            }
        }
        catch(SQLException SQLError)
        {
            SQLError.printStackTrace();
        }
        return sortedCardsOrder;
    }

    public ArrayList<String> sortDriveTypeAsc()
    {
        String query = "SELECT Name FROM Card_Data_Table ORDER BY Drive_Type DESC";
        ArrayList<String> sortedCardsOrder = new ArrayList<>();

        try(Connection conn = this.connect(); PreparedStatement prepState = conn.prepareStatement(query))
        {
            ResultSet results = prepState.executeQuery();
            if(!results.isBeforeFirst())
            {
                sortedCardsOrder = null;
            }
            else
            {
                while(results.next() == true)
                {
                    String currentName = results.getString("Name");
                    sortedCardsOrder.add(currentName);
                }
            }
        }
        catch(SQLException SQLError)
        {
            SQLError.printStackTrace();
        }
        return sortedCardsOrder;
    }

    public ArrayList<String> sortBrandAsc()
    {
        String query = "SELECT Name FROM Card_Data_Table ORDER BY Name DESC";
        ArrayList<String> sortedCardsOrder = new ArrayList<>();

        try(Connection conn = this.connect(); PreparedStatement prepState = conn.prepareStatement(query))
        {
            ResultSet results = prepState.executeQuery();
            if(!results.isBeforeFirst())
            {
                sortedCardsOrder = null;
            }
            else
            {
                while(results.next() == true)
                {
                    String currentName = results.getString("Name");
                    sortedCardsOrder.add(currentName);
                }
            }
        }
        catch(SQLException SQLError)
        {
            SQLError.printStackTrace();
        }
        return sortedCardsOrder;
    }

    public ArrayList<String> sortClassAsc()
    {
        String query = "SELECT Name FROM Card_Data_Table ORDER BY Class ASC";
        ArrayList<String> sortedCardsOrder = new ArrayList<>();

        try(Connection conn = this.connect(); PreparedStatement prepState = conn.prepareStatement(query))
        {
            ResultSet results = prepState.executeQuery();
            if(!results.isBeforeFirst())
            {
                sortedCardsOrder = null;
            }
            else
            {
                while(results.next() == true)
                {
                    String currentName = results.getString("Name");
                    sortedCardsOrder.add(currentName);
                }
            }
        }
        catch(SQLException SQLError)
        {
            SQLError.printStackTrace();
        }
        return sortedCardsOrder;
    }

    public ArrayList<String> sortTopSpeedAsc()
    {
        String query = "SELECT Name FROM Card_Data_Table ORDER BY Top_Speed ASC";
        ArrayList<String> sortedCardsOrder = new ArrayList<>();

        try(Connection conn = this.connect(); PreparedStatement prepState = conn.prepareStatement(query))
        {
            ResultSet results = prepState.executeQuery();
            if(!results.isBeforeFirst())
            {
                sortedCardsOrder = null;
            }
            else
            {
                while(results.next() == true)
                {
                    String currentName = results.getString("Name");
                    sortedCardsOrder.add(currentName);
                }
            }
        }
        catch(SQLException SQLError)
        {
            SQLError.printStackTrace();
        }
        return sortedCardsOrder;
    }

    public ArrayList<String> sortZeroToSixtyAsc()
    {
        String query = "SELECT Name FROM Card_Data_Table ORDER BY Zero_To_Sixty ASC";
        ArrayList<String> sortedCardsOrder = new ArrayList<>();

        try(Connection conn = this.connect(); PreparedStatement prepState = conn.prepareStatement(query))
        {
            ResultSet results = prepState.executeQuery();
            if(!results.isBeforeFirst())
            {
                sortedCardsOrder = null;
            }
            else
            {
                while(results.next() == true)
                {
                    String currentName = results.getString("Name");
                    sortedCardsOrder.add(currentName);
                }
            }
        }
        catch(SQLException SQLError)
        {
            SQLError.printStackTrace();
        }
        return sortedCardsOrder;
    }

    public ArrayList<String> sortHandlingAsc()
    {
        String query = "SELECT Name FROM Card_Data_Table ORDER BY Handling ASC";
        ArrayList<String> sortedCardsOrder = new ArrayList<>();

        try(Connection conn = this.connect(); PreparedStatement prepState = conn.prepareStatement(query))
        {
            ResultSet results = prepState.executeQuery();
            if(!results.isBeforeFirst())
            {
                sortedCardsOrder = null;
            }
            else
            {
                while(results.next() == true)
                {
                    String currentName = results.getString("Name");
                    sortedCardsOrder.add(currentName);
                }
            }
        }
        catch(SQLException SQLError)
        {
            SQLError.printStackTrace();
        }
        return sortedCardsOrder;
    }

    public ArrayList<String> sortNameDesc()
    {
        String query = "SELECT Name FROM Card_Data_Table ORDER BY Name ASC";
        ArrayList<String> sortedCardsOrder = new ArrayList<>();

        try(Connection conn = this.connect(); PreparedStatement prepState = conn.prepareStatement(query))
        {
            ResultSet results = prepState.executeQuery();
            if(!results.isBeforeFirst())
            {
                sortedCardsOrder = null;
            }
            else
            {
                while(results.next() == true)
                {
                    String currentName = results.getString("Name");
                    sortedCardsOrder.add(currentName);
                }
            }
        }
        catch(SQLException SQLError)
        {
            SQLError.printStackTrace();
        }
        return sortedCardsOrder;
    }

    public ArrayList<String> sortDriveTypeDesc()
    {
        String query = "SELECT Name FROM Card_Data_Table ORDER BY Drive_Type ASC";
        ArrayList<String> sortedCardsOrder = new ArrayList<>();

        try(Connection conn = this.connect(); PreparedStatement prepState = conn.prepareStatement(query))
        {
            ResultSet results = prepState.executeQuery();
            if(!results.isBeforeFirst())
            {
                sortedCardsOrder = null;
            }
            else
            {
                while(results.next() == true)
                {
                    String currentName = results.getString("Name");
                    sortedCardsOrder.add(currentName);
                }
            }
        }
        catch(SQLException SQLError)
        {
            SQLError.printStackTrace();
        }
        return sortedCardsOrder;
    }

    public ArrayList<String> sortBrandDesc()
    {
        String query = "SELECT Name FROM Card_Data_Table ORDER BY Name ASC";
        ArrayList<String> sortedCardsOrder = new ArrayList<>();

        try(Connection conn = this.connect(); PreparedStatement prepState = conn.prepareStatement(query))
        {
            ResultSet results = prepState.executeQuery();
            if(!results.isBeforeFirst())
            {
                sortedCardsOrder = null;
            }
            else
            {
                while(results.next() == true)
                {
                    String currentName = results.getString("Name");
                    sortedCardsOrder.add(currentName);
                }
            }
        }
        catch(SQLException SQLError)
        {
            SQLError.printStackTrace();
        }
        return sortedCardsOrder;
    }

    public ArrayList<String> sortClassDesc()
    {
        String query = "SELECT Name FROM Card_Data_Table ORDER BY Class DESC";
        ArrayList<String> sortedCardsOrder = new ArrayList<>();

        try(Connection conn = this.connect(); PreparedStatement prepState = conn.prepareStatement(query))
        {
            ResultSet results = prepState.executeQuery();
            if(!results.isBeforeFirst())
            {
                sortedCardsOrder = null;
            }
            else
            {
                while(results.next() == true)
                {
                    String currentName = results.getString("Name");
                    sortedCardsOrder.add(currentName);
                }
            }
        }
        catch(SQLException SQLError)
        {
            SQLError.printStackTrace();
        }
        return sortedCardsOrder;
    }

    public ArrayList<String> sortTopSpeedDesc()
    {
        String query = "SELECT Name FROM Card_Data_Table ORDER BY Top_Speed DESC";
        ArrayList<String> sortedCardsOrder = new ArrayList<>();

        try(Connection conn = this.connect(); PreparedStatement prepState = conn.prepareStatement(query))
        {
            ResultSet results = prepState.executeQuery();
            if(!results.isBeforeFirst())
            {
                sortedCardsOrder = null;
            }
            else
            {
                while(results.next() == true)
                {
                    String currentName = results.getString("Name");
                    sortedCardsOrder.add(currentName);
                }
            }
        }
        catch(SQLException SQLError)
        {
            SQLError.printStackTrace();
        }
        return sortedCardsOrder;
    }

    public ArrayList<String> sortZeroToSixtyDesc()
    {
        String query = "SELECT Name FROM Card_Data_Table ORDER BY Zero_To_Sixty DESC";
        ArrayList<String> sortedCardsOrder = new ArrayList<>();

        try(Connection conn = this.connect(); PreparedStatement prepState = conn.prepareStatement(query))
        {
            ResultSet results = prepState.executeQuery();
            if(!results.isBeforeFirst())
            {
                sortedCardsOrder = null;
            }
            else
            {
                while(results.next() == true)
                {
                    String currentName = results.getString("Name");
                    sortedCardsOrder.add(currentName);
                }
            }
        }
        catch(SQLException SQLError)
        {
            SQLError.printStackTrace();
        }
        return sortedCardsOrder;
    }

    public ArrayList<String> sortHandlingDesc()
    {
        String query = "SELECT Name FROM Card_Data_Table ORDER BY Handling DESC";
        ArrayList<String> sortedCardsOrder = new ArrayList<>();

        try(Connection conn = this.connect(); PreparedStatement prepState = conn.prepareStatement(query))
        {
            ResultSet results = prepState.executeQuery();
            if(!results.isBeforeFirst())
            {
                sortedCardsOrder = null;
            }
            else
            {
                while(results.next() == true)
                {
                    String currentName = results.getString("Name");
                    sortedCardsOrder.add(currentName);
                }
            }
        }
        catch(SQLException SQLError)
        {
            SQLError.printStackTrace();
        }
        return sortedCardsOrder;
    }

    public ArrayList<String> searchAllCards()
    {
        ArrayList<String> nameResultsIn = new ArrayList<String>();
        String query = "SELECT Name "
                        + "FROM Card_Data_Table";
        try(Connection conn = this.connect(); PreparedStatement prepState = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE))
        {
            ResultSet results = prepState.executeQuery();
            if(!results.isBeforeFirst())
            {
                nameResultsIn = null;
            }
            else
            {
                while(results.next() == true)
                {
                    String currentName = results.getString("Name");
                    nameResultsIn.add(currentName);
                }
            }
        }
        catch(SQLException SQLError)
        {
            SQLError.printStackTrace();
        }
        return nameResultsIn;
    }
}
