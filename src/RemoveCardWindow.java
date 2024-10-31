import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RemoveCardWindow implements ActionListener
{   
    JFrame removeWindow = new JFrame("MyCarCards");

    JButton closeWindowButton = new JButton("Close");

    GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();

    public int frameSizeWidth = gd.getDisplayMode().getWidth() / 2;
    public int frameSizeHeight = gd.getDisplayMode().getHeight() / 2;

    String exactCardName;

    SQLiteConnection connObj = new SQLiteConnection();

    public RemoveCardWindow(ArrayList<String> tableData)
    {
        GridLayout grid = new GridLayout(tableData.size(), 0, 30, 20);
        removeWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        removeWindow.setSize(frameSizeWidth,frameSizeHeight);
        removeWindow.setVisible(true);
        
        closeWindowButton.setSize(70, 20);
        removeWindow.add(BorderLayout.SOUTH, closeWindowButton);
        closeWindowButton.addActionListener(this);

        JPanel buttonPanel = new JPanel(grid);
        removeWindow.add(buttonPanel);
        

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
            removeWindow.add(scrollPane);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        JButton source = (JButton) e.getSource();
        if(source == closeWindowButton)
        {
            new LaunchPage();
            removeWindow.dispose();
        }
        else
        {
            int keyValue = connObj.findCardKeyValue(source.getName());
            connObj.removeCard(keyValue);
            new LaunchPage();
            removeWindow.dispose();
        }
    }   
}
