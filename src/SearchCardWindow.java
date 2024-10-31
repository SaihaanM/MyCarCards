import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SearchCardWindow implements ActionListener
{   
    JFrame searchWindow = new JFrame("MyCarCards");

    JButton closeWindowButton = new JButton("Close");

    GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();

    public int frameSizeWidth = gd.getDisplayMode().getWidth() / 2;
    public int frameSizeHeight = gd.getDisplayMode().getHeight() / 2;

    String exactCardName;

    SQLiteConnection connObj = new SQLiteConnection();

    public SearchCardWindow(ArrayList<String> tableData)
    {
        GridLayout grid = new GridLayout(tableData.size(), 0, 30, 20);
        searchWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        searchWindow.setSize(frameSizeWidth,frameSizeHeight);
        searchWindow.setVisible(true);
        
        closeWindowButton.setSize(70, 20);
        searchWindow.add(BorderLayout.SOUTH, closeWindowButton);
        closeWindowButton.addActionListener(this);

        JPanel buttonPanel = new JPanel(grid);
        searchWindow.add(buttonPanel);
        
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
            searchWindow.add(scrollPane);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        JButton source = (JButton) e.getSource();
        if(source == closeWindowButton)
        {
            new LaunchPage();
            searchWindow.dispose();
        }
        else
        {
            int keyValue = connObj.findCardKeyValue(source.getName());
            searchWindow.dispose();
            new DisplayCardWindow(keyValue);
        }
    }   
}
