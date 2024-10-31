import java.io.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.*;
import java.awt.event.*;

public class AddCardWindow implements ActionListener 
{
    JFrame addCardFrame = new JFrame("MyCarCards");
    JButton inputCardFile = new JButton("Add Card Image");
    JButton saveButton = new JButton("Save");
    JButton cancelButton = new JButton("Cancel");

    File userFile;
    String filePath;

    public JTextField inputCarBrand = new JTextField(30);
    public JTextField inputCarName = new JTextField(30);
    public JTextField inputDriveType = new JTextField(30);
    public JTextField inputTopSpeed = new JTextField(30);
    public JTextField inputHandling = new JTextField(30);
    public JTextField inputZeroToSixty = new JTextField(30);
    public JTextField inputClass = new JTextField(30);

    SQLiteConnection sqlConnObj = new SQLiteConnection();

    String carBrand, carName, driveType, handling, topSpeed, zeroToSixty, carClass;

    AddCardWindow(int frameWidth, int frameHeight)
    {
        addCardFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addCardFrame.setLayout(new GridBagLayout());
        addCardFrame.setSize(frameWidth / 2, frameHeight / 2);
        addCardFrame.setLocationRelativeTo(null);
        addCardFrame.setVisible(true);

       
        JLabel carBrandLabel = new JLabel("Car Brand: ");
        JLabel carNameLabel = new JLabel("Car Name: ");
        JLabel driveTypeLabel = new JLabel("Drive Type: ");
        JLabel topSpeedLabel = new JLabel("Top Speed In MPH: ");
        JLabel handlingLabel = new JLabel("Handling: ");
        JLabel zeroToSixtyLabel = new JLabel("0-60 MPH In Seconds: ");
        JLabel classLabel = new JLabel("Car Class: ");
       
        inputCardFile.setSize(150, 50);
        inputCardFile.addActionListener(this);

        saveButton.setSize(150, 50);
        saveButton.addActionListener(this);

        cancelButton.setSize(150, 150);
        cancelButton.addActionListener(this);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(10, 10, 10, 10);

        constraints.gridx = 0;
        constraints.gridy = 0;
        addCardFrame.add(carBrandLabel, constraints);
        constraints.gridx = 1;
        constraints.gridy = 0;
        addCardFrame.add(inputCarBrand, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        addCardFrame.add(carNameLabel, constraints);
        constraints.gridx = 1;
        constraints.gridy = 1;
        addCardFrame.add(inputCarName, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        addCardFrame.add(driveTypeLabel, constraints);
        constraints.gridx = 1;
        constraints.gridy = 2;
        addCardFrame.add(inputDriveType, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        addCardFrame.add(topSpeedLabel, constraints);
        constraints.gridx = 1;
        constraints.gridy = 3;
        addCardFrame.add(inputTopSpeed, constraints);

        constraints.gridx = 0;
        constraints.gridy = 4;
        addCardFrame.add(handlingLabel, constraints);
        constraints.gridx = 1;
        constraints.gridy = 4;
        addCardFrame.add(inputHandling, constraints);

        constraints.gridx = 0;
        constraints.gridy = 5;
        addCardFrame.add(zeroToSixtyLabel, constraints);
        constraints.gridx = 1;
        constraints.gridy = 5;
        addCardFrame.add(inputZeroToSixty, constraints);

        constraints.gridx = 0;
        constraints.gridy = 6;
        addCardFrame.add(classLabel, constraints);
        constraints.gridx = 1;
        constraints.gridy = 6;
        addCardFrame.add(inputClass, constraints);


        constraints.gridx = 1;
        constraints.gridy = 7;
        addCardFrame.add(inputCardFile, constraints);

        constraints.gridx = 2;
        constraints.gridy = 10;
        addCardFrame.add(saveButton, constraints);

        constraints.gridx = 3;
        constraints.gridy = 10;
        addCardFrame.add(cancelButton, constraints);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
      JButton source = (JButton) e.getSource();
      
      try{
        if(source == inputCardFile)
        {
            JFileChooser fileFinder = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("PNG Images Only", "png");
            fileFinder.setFileFilter(filter);
            int response = fileFinder.showOpenDialog(null);
            if(response == JFileChooser.APPROVE_OPTION)
            {
                userFile = new File(fileFinder.getSelectedFile().getAbsolutePath());
                filePath = userFile.getPath();
                if(!filePath.contains(".png"))
                {
                    throw new FileNotFoundException();
                }
            }

        }
        else if(source == saveButton)
        {
            Double.parseDouble(inputHandling.getText());  //If these fail, a NumberFormatException will be thrown
            Double.parseDouble(inputTopSpeed.getText());
            Double.parseDouble(inputZeroToSixty.getText());

            carBrand = inputCarBrand.getText();
            carName = inputCarName.getText();
            driveType = inputDriveType.getText();
            topSpeed = inputTopSpeed.getText();
            handling = inputHandling.getText();
            zeroToSixty = inputZeroToSixty.getText();
            carClass = inputClass.getText();

            if(carBrand.equals("") ||
               carName.equals("")||
               driveType.equals("")||
               handling.equals("")||
               topSpeed.equals("")||
               zeroToSixty.equals("")||
               carClass.equals(""))
            {
                throw new NullPointerException();
            }
            else if(userFile == null)
            {
                throw new FileNotFoundException();
            }
            else
            {
                int keyValue = sqlConnObj.getCurrentKeyValue() + 1;
                sqlConnObj.addCarDetails(keyValue, carBrand, carName, driveType, handling, topSpeed, zeroToSixty, carClass, filePath);
                addCardFrame.dispose();
                new LaunchPage();
            }
        }
        else if(source == cancelButton)
        {
            addCardFrame.dispose(); 
            new LaunchPage();
        }
        } 
        catch(NullPointerException nullFields)
        {
            JOptionPane.showMessageDialog(source, "One Or More Fields Are Empty", null, 0);
        }
        catch(FileNotFoundException noFile)
        {
            JOptionPane.showMessageDialog(source, "No Image Was Selected! Must be a .png!", null, 0);
        }
        catch(NumberFormatException invalidFormat)
        {
            JOptionPane.showMessageDialog(source, "One Or More Fields Contain Invalid Inputs", null, 0);
        }
    }
}
