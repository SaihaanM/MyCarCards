import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AuthenticationPage implements ActionListener
{
    JFrame authenticationPage = new JFrame ("MyCarCards");
    JButton exitButton = new JButton("Exit");
    JButton enterButton = new JButton("Enter");

    public JTextField username = new JTextField(30);
    public JPasswordField password = new JPasswordField(30);

    AuthenticationPage()
    {
        authenticationPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        authenticationPage.setLayout(new GridBagLayout());
        authenticationPage.setVisible(true);

        password.setEchoChar('*');

        JLabel usernameLabel = new JLabel("Username");
        JLabel passwordLabel = new JLabel("Password");

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(10, 10, 10, 10);

        exitButton.addActionListener(this);
        enterButton.addActionListener(this);

        constraints.gridx = 0;
        constraints.gridy = 0;
        authenticationPage.add(usernameLabel, constraints);
        constraints.gridx = 1;
        constraints.gridy = 0;
        authenticationPage.add(username, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        authenticationPage.add(passwordLabel, constraints);
        constraints.gridx = 1;
        constraints.gridy = 1;
        authenticationPage.add(password, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        authenticationPage.add(exitButton, constraints);
        constraints.gridx = 1;
        constraints.gridy = 2;
        authenticationPage.add(enterButton, constraints);

        authenticationPage.pack();
    }

    @SuppressWarnings("deprecation")
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        JButton source = (JButton) e.getSource();

        if(source == enterButton)
        {
            if(username.getText().equalsIgnoreCase("ABC"))
            {
                if(password.getText().equals("ABC123"))
                {
                    authenticationPage.dispose();
                    new LaunchPage();
                }
                else
                {
                    JOptionPane.showMessageDialog(source, "Incorrect Username Or Password", null, 0);
                }
            }
            else
            {
                JOptionPane.showMessageDialog(source, "Incorrect Username Or Password", null, 0);
            }
        }
        else
        {
            authenticationPage.dispose();
        }
        
    }

}
