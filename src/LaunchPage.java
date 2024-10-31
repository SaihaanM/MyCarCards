import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LaunchPage extends JFrame implements ActionListener {
  
  JFrame launchFrame = new JFrame("MyCarCards");
  JButton sortCardButton = new JButton("Sort Card");
  JButton editCardButton = new JButton("Edit Card");
  JButton addCardButton = new JButton("Add Card");
  JButton removeCardButton = new JButton("Remove Card");
  JButton searchCardButton = new JButton("Search Card");
  GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
 
  public int frameSizeWidth = gd.getDisplayMode().getWidth();
  public int frameSizeHeight = gd.getDisplayMode().getHeight();

  SQLiteConnection sqlObj = new SQLiteConnection();

  ArrayList<String> scrollData = sqlObj.searchAllCards();
  
  LaunchPage()
    {
        GridLayout gridForOption = new GridLayout(5, 1);
        JPanel optionButtonPanel = new JPanel(gridForOption);

        GridLayout gridForScroll = new GridLayout(0, 1);
        JPanel buttonsPanel = new JPanel(gridForScroll);

        BorderLayout borderLayout = new BorderLayout();
        launchFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        launchFrame.setLayout(borderLayout);
        launchFrame.setSize(frameSizeWidth,frameSizeHeight);

        optionButtonPanel.add(editCardButton);
        editCardButton.addActionListener(this);
        
        optionButtonPanel.add(addCardButton);
        addCardButton.addActionListener(this);

        optionButtonPanel.add(removeCardButton);
        removeCardButton.addActionListener(this);

        optionButtonPanel.add(searchCardButton);
        searchCardButton.addActionListener(this);

        optionButtonPanel.add(sortCardButton);
        sortCardButton.addActionListener(this);

        if(scrollData != null)
        {
          for(int indexer = 0; indexer < scrollData.size(); indexer++)
          {
            JButton newButton = new JButton(scrollData.get(indexer));
            newButton.setName(scrollData.get(indexer));
            newButton.addActionListener(this);
            buttonsPanel.add(newButton);
          }
  
          JScrollPane scrollablePane = new JScrollPane(buttonsPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
          
          launchFrame.add(BorderLayout.CENTER, scrollablePane);
        }
        launchFrame.add(BorderLayout.WEST, optionButtonPanel);
        launchFrame.setVisible(true);
    }

    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        JButton source = (JButton) e.getSource();
        if(source == editCardButton)
        {
          final String editCardName = JOptionPane.showInputDialog("Enter the card name that you wish to edit", null);
          try
          {
              if(editCardName.equals(""))
              {
                throw new NoSuchFieldException();
              }
              else
              {
                ArrayList<String> dataForResults = sqlObj.searchCardKeyLike(editCardName);
                if(!(dataForResults == null))
                {
                  launchFrame.dispose();
                  new ResultsForWindow(dataForResults);
                }
                else
                {
                  throw new NoSuchFieldException();
                }
              }
          }
          catch(NoSuchFieldException noFieldInput)
          {
            JOptionPane.showMessageDialog(source, "Card Not Found", null, 0);
          }
          catch(NullPointerException error)
          {
            System.out.println("Expected Error");
          }
        }
        else if(source == addCardButton)
        {
          launchFrame.dispose();
          new AddCardWindow(frameSizeWidth, frameSizeHeight);
        }

        else if(source == removeCardButton)
        {
          final String removeCardName = JOptionPane.showInputDialog("Enter the card name that you wish to remove", null);
          try
          {
              if(removeCardName.equals(""))
              {
                throw new NoSuchFieldException();
              }
              else
              {
                ArrayList<String> dataForResults = sqlObj.searchCardKeyLike(removeCardName);
                if(!(dataForResults == null))
                {
                  launchFrame.dispose();
                  new RemoveCardWindow(dataForResults);
                }
                else
                {
                  throw new NoSuchFieldException();
                }
              }
          }
          catch(NoSuchFieldException noFieldInput)
          {
            JOptionPane.showMessageDialog(source, "Card Not Found", null, 0);
          }
          catch(NullPointerException error)
          {
            System.out.println("Expected Error");
          }
        }

        else if(source == searchCardButton)
        {
          final String searchCardName = JOptionPane.showInputDialog("Enter the card name that you wish to search", null);
          try
          {
              if(searchCardName.equals(""))
              {
                throw new NoSuchFieldException();
              }
              else
              {
                ArrayList<String> dataForResults = sqlObj.searchCardKeyLike(searchCardName);
                if(!(dataForResults == null))
                {
                  new SearchCardWindow(dataForResults);
                }
                else
                {
                  throw new NoSuchFieldException();
                }
              }
          }
          catch(NoSuchFieldException noFieldInput)
          {
            JOptionPane.showMessageDialog(source, "Card Not Found", null, 0);
          }
          catch(NullPointerException error)
          {
            System.out.println("Expected Error");
          }
        }
        else if(source == sortCardButton)
        {
          new SortingWindow();
        }
        else
        {
          int keyValue = sqlObj.findCardKeyValue(source.getName());
          new DisplayCardWindow(keyValue);
        }

    }   
}