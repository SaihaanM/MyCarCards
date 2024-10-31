import java.io.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;

public class EditCardWindow implements ActionListener
{
    JFrame editCardFrame = new JFrame("MyCarCards");
    JButton saveButton = new JButton("Save");
    JButton cancelButton = new JButton("Cancel");
    JButton editCardFile;

    File userFile;
    String filePath;
    int keyValueOut;

    public JTextField editCarBrand = new JTextField(30);
    public JTextField editCarName = new JTextField(30);
    public JTextField editDriveType = new JTextField(30);
    public JTextField editTopSpeed = new JTextField(30);
    public JTextField editHandling = new JTextField(30);
    public JTextField editZeroToSixty = new JTextField(30);
    public JTextField editClass = new JTextField(30);

    String carBrand, carName, driveType, handling, topSpeed, zeroToSixty, carClass;
    String originalFilePath;

    GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();

    SQLiteConnection connObj = new SQLiteConnection();

    public int frameWidth = gd.getDisplayMode().getWidth();
    public int frameHeight = gd.getDisplayMode().getHeight();

    EditCardWindow(int keyValueIn, String oCarBrand, String oCarName, String oDriveType, 
                   double oTopSpeed, double oHandling, double oZeroToSixty,
                   String oClass, String oFileName)
    {
        editCardFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        editCardFrame.setLayout(new GridBagLayout());
        editCardFrame.setSize(frameWidth / 2, frameHeight / 2);
        editCardFrame.setLocationRelativeTo(null);
        editCardFrame.setVisible(true);

        JLabel carBrandLabel = new JLabel("Car Brand: ");
        JLabel carNameLabel = new JLabel("Car Name: ");
        JLabel driveTypeLabel = new JLabel("Drive Type: ");
        JLabel topSpeedLabel = new JLabel("Top Speed In MPH: ");
        JLabel handlingLabel = new JLabel("Handling: ");
        JLabel zeroToSixtyLabel = new JLabel("0-60 In Seconds: ");
        JLabel classLabel = new JLabel("Class: ");
        JLabel fileLabel = new JLabel("Image: ");

        originalFilePath = oFileName;
        editCardFile = new JButton(oFileName);
        
        editCarBrand.setText(oCarBrand);
        editCarName.setText(oCarName);
        editDriveType.setText(oDriveType);
        editTopSpeed.setText(Double.toString(oTopSpeed));
        editHandling.setText(Double.toString(oHandling));
        editZeroToSixty.setText(Double.toString(oZeroToSixty));
        editClass.setText(oClass);

        editCardFile.setSize(150, 50);
        editCardFile.addActionListener(this);

        saveButton.setSize(150, 50);
        saveButton.addActionListener(this);

        cancelButton.setSize(150, 50);
        cancelButton.addActionListener(this);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(10, 10, 10, 10);

        constraints.gridx = 0;
        constraints.gridy = 0;
        editCardFrame.add(carBrandLabel, constraints);
        constraints.gridx = 1;
        constraints.gridy = 0;
        editCardFrame.add(editCarBrand, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        editCardFrame.add(carNameLabel, constraints);
        constraints.gridx = 1;
        constraints.gridy = 1;
        editCardFrame.add(editCarName, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        editCardFrame.add(driveTypeLabel, constraints);
        constraints.gridx = 1;
        constraints.gridy = 2;
        editCardFrame.add(editDriveType, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        editCardFrame.add(topSpeedLabel, constraints);
        constraints.gridx = 1;
        constraints.gridy = 3;
        editCardFrame.add(editTopSpeed, constraints);

        constraints.gridx = 0;
        constraints.gridy = 4;
        editCardFrame.add(handlingLabel, constraints);
        constraints.gridx = 1;
        constraints.gridy = 4;
        editCardFrame.add(editHandling, constraints);

        constraints.gridx = 0;
        constraints.gridy = 5;
        editCardFrame.add(zeroToSixtyLabel, constraints);
        constraints.gridx = 1;
        constraints.gridy = 5;
        editCardFrame.add(editZeroToSixty, constraints);

        constraints.gridx = 0;
        constraints.gridy = 6;
        editCardFrame.add(classLabel, constraints);
        constraints.gridx = 1;
        constraints.gridy = 6;
        editCardFrame.add(editClass, constraints);

        constraints.gridx = 0;
        constraints.gridy = 7;
        editCardFrame.add(fileLabel, constraints);
        constraints.gridx = 1;
        constraints.gridy = 7;
        editCardFrame.add(editCardFile, constraints);

        constraints.gridx = 2;
        constraints.gridy = 10;
        editCardFrame.add(saveButton, constraints);

        constraints.gridx = 3;
        constraints.gridy = 10;
        editCardFrame.add(cancelButton, constraints);

        keyValueOut = keyValueIn;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        JButton source = (JButton) e.getSource();
        try{
          if(source == editCardFile)
          {
              JFileChooser fileFinder = new JFileChooser();
              FileNameExtensionFilter filter = new FileNameExtensionFilter("PNG Images Only", "png");
              fileFinder.setFileFilter(filter);            
              int response = fileFinder.showOpenDialog(null);
              if(response == JFileChooser.APPROVE_OPTION)
              {try
                {
                    userFile = new File(fileFinder.getSelectedFile().getAbsolutePath());
                    filePath = userFile.getPath();
                    if(!filePath.contains(".png"))
                    {
                        throw new FileNotFoundException();
                    }
                }
                catch(FileNotFoundException invalidFile)
                {
                    JOptionPane.showMessageDialog(source, "No Image Was Selected! Must be a .png!", null, 0);
                }
              }
          }
          else if(source == saveButton)
          {
              Double.parseDouble(editHandling.getText());
              Double.parseDouble(editTopSpeed.getText());
              Double.parseDouble(editZeroToSixty.getText());
  
              carBrand = editCarBrand.getText();
              carName = editCarName.getText();
              driveType = editDriveType.getText();
              topSpeed = editTopSpeed.getText();
              handling = editHandling.getText();
              zeroToSixty = editZeroToSixty.getText();
              carClass = editClass.getText();
  
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
              else
              {
                if(filePath == null)
                {
                    filePath = originalFilePath;
                    connObj.editCardDetails(keyValueOut, carBrand, carName, driveType, handling, topSpeed, zeroToSixty, carClass, filePath);
                }
                else
                {
                    connObj.editCardDetails(keyValueOut, carBrand, carName, driveType, handling, topSpeed, zeroToSixty, carClass, filePath);
                }
                new LaunchPage();
                editCardFrame.dispose();
              }
          }
          else if(source == cancelButton)
          {
            new LaunchPage();
              editCardFrame.dispose(); 
          }
          } 
          catch(NullPointerException nullFields)
          {
              JOptionPane.showMessageDialog(source, "One Or More Fields Are Empty", null, 0);
          }
          catch(NumberFormatException invalidFormat)
          {
              JOptionPane.showMessageDialog(source, "One Or More Fields Contain Invalid Inputs", null, 0);
          }
      }
}
