import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ResultsForWindow implements ActionListener
{   
    JFrame resultsWindow = new JFrame("MyCarCards");

    JButton closeWindowButton = new JButton("Close");

    GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();

    public int frameSizeWidth = gd.getDisplayMode().getWidth() / 2;
    public int frameSizeHeight = gd.getDisplayMode().getHeight() / 2;

    String exactCardName;

    SQLiteConnection connObj = new SQLiteConnection();

    public ResultsForWindow(ArrayList<String> tableData)
    {
        GridLayout grid = new GridLayout(tableData.size(), 0, 30, 20);
        resultsWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        resultsWindow.setSize(frameSizeWidth,frameSizeHeight);
        resultsWindow.setVisible(true);
        
        closeWindowButton.setSize(70, 20);
        resultsWindow.add(BorderLayout.SOUTH, closeWindowButton);
        closeWindowButton.addActionListener(this);

        JPanel buttonPanel = new JPanel(grid);
        resultsWindow.add(buttonPanel);
        

        if(!(tableData == null))
        {
            JButton[] goToButtons = new JButton[tableData.size()];

            JScrollPane scrollPane = new JScrollPane(buttonPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

            for (int indexer = 0; indexer < tableData.size(); indexer++) 
            {
                goToButtons[indexer] = new JButton(tableData.get(indexer));
                goToButtons[indexer].setName(tableData.get(indexer));
                goToButtons[indexer].addActionListener(this);
                buttonPanel.add(goToButtons[indexer]);
            }
            resultsWindow.add(scrollPane);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        JButton source = (JButton) e.getSource();
        if(source == closeWindowButton)
        {
            new LaunchPage();
            resultsWindow.dispose();
        }
        else
        {
            SQLiteConnection connObj = new SQLiteConnection();
            int keyValue = connObj.findCardKeyValue(source.getName());

            CarInfo infoObj = new CarInfo(keyValue);
            new EditCardWindow(keyValue, infoObj.getCarBrand(), infoObj.getCarName(), infoObj.getDriveType(), infoObj.getSpeed(), infoObj.getHandling(), infoObj.getZeroToSixty(), infoObj.getCarClass(), infoObj.getFileName());
            resultsWindow.dispose();
        }
    }   
}
