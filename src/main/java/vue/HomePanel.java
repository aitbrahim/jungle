package vue;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class HomePanel extends JPanel {

    private JLabel         pseudoLabel;
    private JLabel         passwordLabel;
    private JTextField     pseudoField;
    private JPasswordField passwordField;
    private JButton        btnSubmit;

    public HomePanel() {
        pseudoLabel = new JLabel( "Pseudo :" );
        passwordLabel = new JLabel( "Mot De Pass :" );
        pseudoField = new JTextField( 10 );
        passwordField = new JPasswordField( 10 );
        btnSubmit = new JButton( "Connecter" );

        add( pseudoLabel );
        add( pseudoField );
        add( passwordLabel );
        add( passwordField );
        add( btnSubmit );
    }

    public JTextField getPseudoField() {
        return pseudoField;
    }

    public JPasswordField getPasswordField() {
        return passwordField;
    }

    public JButton getBtnSubmit() {
        return btnSubmit;
    }
}