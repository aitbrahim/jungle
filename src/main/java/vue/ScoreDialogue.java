package vue;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ScoreDialogue extends JDialog implements ActionListener {

    private JButton cancelBtn = new JButton( "Quitter" );

    public ScoreDialogue( JFrame frame, Long idJoueur ) {
        super( frame, "Listes Des Scores", true );
        setLayout( new BorderLayout() );
        cancelBtn.addActionListener( this );
        add( cancelBtn, "North" );
        JTable tableau = new JTable( new ModelScoreTable( idJoueur ) );

        add( new JScrollPane( tableau ) );
        setSize( new Dimension( 200, 300 ) );
        setVisible( true );
    }

    public void actionPerformed( ActionEvent e )
    {
        if ( e.getSource() == cancelBtn )
            setVisible( false );
    }
}
