public class CarInfo 
{
    protected int keyValue = 0;
    SQLiteConnection connObj = new SQLiteConnection();

    public CarInfo(int keyValueIn)
    {
        keyValue = keyValueIn;
    }

    public String getCarBrand()
    {
        return connObj.findExactCarBrand(keyValue);
    }
    
    public String getCarName()
    {
        return connObj.findExactCarName(keyValue);
    }

    public String getDriveType()
    {
        return connObj.findExactDriveType(keyValue);
    }

    public String getCarClass()
    {
        return connObj.findExactCarClass(keyValue);
    }

    public double getSpeed()
    {
        return connObj.findExactSpeed(keyValue);
    }

    public double getHandling()
    {
        return connObj.findExactHandling(keyValue);
    }


    public double getZeroToSixty()
    {
        return connObj.findExactZeroToSixty(keyValue);
    }

    public String getFileName() 
    {
       return connObj.findExactFileName(keyValue);
    }
}
