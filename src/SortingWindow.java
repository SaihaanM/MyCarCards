import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class SortingWindow implements ActionListener 
{
    JFrame sortingWindow = new JFrame("MyCarCards");

    JButton filterByName = new JButton("Filter By Name");
    JButton filterByDriveType = new JButton("Filter By Drive Type");
    JButton filterByBrand = new JButton("Filter By Brand");
    JButton filterByClass = new JButton("Filter By Class");
    JButton filterByTopSpeed = new JButton("Filter By Top Speed");
    JButton filterByZeroToSixty = new JButton("Filter By Zero To Sixty");
    JButton filterByHandling = new JButton("Filter By Handling");
    JButton cancelButton = new JButton("Cancel");

    JCheckBox ascendingBox = new JCheckBox();
    JCheckBox descendingBox = new JCheckBox();

    GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();

    SQLiteConnection connObj = new SQLiteConnection();

    public int frameWidth = gd.getDisplayMode().getWidth();
    public int frameHeight = gd.getDisplayMode().getHeight();

    ArrayList<String> sortedInfo = new ArrayList<String>();

    SortingWindow ()
    {
        sortingWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        sortingWindow.setLayout(new GridBagLayout());
        sortingWindow.setSize(frameWidth / 2, frameHeight / 2);
        sortingWindow.setLocationRelativeTo(null);
        sortingWindow.setVisible(true);

        JLabel filterNameLabel = new JLabel("Car Name: ");
        JLabel filterDriveTypeLabel = new JLabel("Drive Type: ");
        JLabel filterBrandLabel = new JLabel("Brand: ");
        JLabel filterClassLabel = new JLabel("Class: ");
        JLabel filterTopSpeedLabel = new JLabel("Top Speed: ");
        JLabel filterZeroToSixtyLabel = new JLabel("0-60 In Seconds: ");
        JLabel filterHandlingLabel = new JLabel("Handling: ");
        JLabel ascendingLabel = new JLabel("Ascending Order: ");
        JLabel descendingLabel = new JLabel("Descending Order: ");

        filterByName.setSize(150,50);
        filterByName.addActionListener(this);

        filterByBrand.setSize(150,50);
        filterByBrand.addActionListener(this);

        filterByDriveType.setSize(150,50);
        filterByDriveType.addActionListener(this);
        
        filterByClass.setSize(150,50);
        filterByClass.addActionListener(this);

        filterByHandling.setSize(150,50);
        filterByHandling.addActionListener(this);
        
        filterByTopSpeed.setSize(150,50);
        filterByTopSpeed.addActionListener(this);

        filterByZeroToSixty.setSize(150,50);
        filterByZeroToSixty.addActionListener(this);

        cancelButton.setSize(150, 50);
        cancelButton.addActionListener(this);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(10, 10, 10, 10);

        constraints.gridx = 0;
        constraints.gridy = 0;
        sortingWindow.add(filterBrandLabel, constraints);
        constraints.gridx = 1;
        constraints.gridy = 0;
        sortingWindow.add(filterByBrand, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        sortingWindow.add(filterNameLabel, constraints);
        constraints.gridx = 1;
        constraints.gridy = 1;
        sortingWindow.add(filterByName, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        sortingWindow.add(filterDriveTypeLabel, constraints);
        constraints.gridx = 1;
        constraints.gridy = 2;
        sortingWindow.add(filterByDriveType, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        sortingWindow.add(filterTopSpeedLabel, constraints);
        constraints.gridx = 1;
        constraints.gridy = 3;
        sortingWindow.add(filterByTopSpeed, constraints);

        constraints.gridx = 0;
        constraints.gridy = 4;
        sortingWindow.add(filterHandlingLabel, constraints);
        constraints.gridx = 1;
        constraints.gridy = 4;
        sortingWindow.add(filterByHandling, constraints);

        constraints.gridx = 0;
        constraints.gridy = 5;
        sortingWindow.add(filterZeroToSixtyLabel, constraints);
        constraints.gridx = 1;
        constraints.gridy = 5;
        sortingWindow.add(filterByZeroToSixty, constraints);

        constraints.gridx = 0;
        constraints.gridy = 6;
        sortingWindow.add(filterClassLabel, constraints);
        constraints.gridx = 1;
        constraints.gridy = 6;
        sortingWindow.add(filterByClass, constraints);

        constraints.gridx = 0;
        constraints.gridy = 7;
        sortingWindow.add(ascendingLabel, constraints);
        constraints.gridx = 1;
        constraints.gridy = 7;
        sortingWindow.add(ascendingBox, constraints);
        
        constraints.gridx = 0;
        constraints.gridy = 8;
        sortingWindow.add(descendingLabel, constraints);
        constraints.gridx = 1;
        constraints.gridy = 8;
        sortingWindow.add(descendingBox, constraints);

        constraints.gridx = 2;
        constraints.gridy = 10;
        sortingWindow.add(cancelButton, constraints);
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        JButton source = (JButton) e.getSource();

        if(source == cancelButton)
        {
            new LaunchPage();
            sortingWindow.dispose();
        }
        else
        {
            try 
            {
                
                if(ascendingBox.isSelected() && !descendingBox.isSelected())
                {
                    if(source == filterByName )
                    {
                        sortedInfo = connObj.sortNameAsc();
                        sortingWindow.dispose();
    
                        new SortedCardWindow(sortedInfo);  
                    }
                    else if(source == filterByDriveType)
                    {
                        sortedInfo = connObj.sortDriveTypeAsc();
                        sortingWindow.dispose();
    
                        new SortedCardWindow(sortedInfo);  
                    }
    
                    else if(source == filterByBrand)
                    {
                        sortedInfo = connObj.sortBrandAsc();
                        sortingWindow.dispose();
    
                        new SortedCardWindow(sortedInfo);  
                    }    
                    else if(source == filterByClass)
                    {
                        sortedInfo = connObj.sortClassAsc();
                        sortingWindow.dispose();
    
                        new SortedCardWindow(sortedInfo);  
                    }
                    else if(source == filterByTopSpeed) 
                    {
                        sortedInfo = connObj.sortTopSpeedAsc();
                        sortingWindow.dispose();
    
                        new SortedCardWindow(sortedInfo);  
                    }    
                    else if(source == filterByZeroToSixty)
                    {
                        sortedInfo = connObj.sortZeroToSixtyAsc();
                        sortingWindow.dispose();
    
                        new SortedCardWindow(sortedInfo);  
                    }
                    else
                    {
                        sortedInfo = connObj.sortHandlingAsc();
                        sortingWindow.dispose();
    
                        new SortedCardWindow(sortedInfo);  
                    }       
                
                } 
                
                else if(descendingBox.isSelected() && !ascendingBox.isSelected())
                {
                    if(source == filterByName )
                    {
                        sortedInfo = connObj.sortNameDesc();
                        sortingWindow.dispose();
    
                        new SortedCardWindow(sortedInfo);  
                    }
                    else if(source == filterByDriveType)
                    {
                        sortedInfo = connObj.sortDriveTypeDesc();
                        sortingWindow.dispose();
    
                        new SortedCardWindow(sortedInfo);  
                    }
    
                    else if(source == filterByBrand)
                    {
                        sortedInfo = connObj.sortBrandDesc();
                        sortingWindow.dispose();
    
                        new SortedCardWindow(sortedInfo);  
                    }    
                    else if(source == filterByClass)
                    {
                        sortedInfo = connObj.sortClassDesc();
                        sortingWindow.dispose();
    
                        new SortedCardWindow(sortedInfo);  
                    }
                    else if(source == filterByTopSpeed) 
                    {
                        sortedInfo = connObj.sortTopSpeedDesc();
                        sortingWindow.dispose();
    
                        new SortedCardWindow(sortedInfo);  
                    }    
                    else if(source == filterByZeroToSixty)
                    {
                        sortedInfo = connObj.sortZeroToSixtyDesc();
                        sortingWindow.dispose();
    
                        new SortedCardWindow(sortedInfo);  
                    }
                    else
                    {
                        sortedInfo = connObj.sortHandlingDesc();
                        sortingWindow.dispose();
    
                        new SortedCardWindow(sortedInfo);  
                    }       
                } 

                else
                {
                    throw new InputMismatchException();
                }
            }
            catch (InputMismatchException checkBoxError) 
            {
                JOptionPane.showMessageDialog(source, "Please Select 1 Check Box!", null, 0);
            }
        }
    }
}
