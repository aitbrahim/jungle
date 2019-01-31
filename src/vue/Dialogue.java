package vue;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class Dialogue extends JDialog implements ActionListener {

    private JButton    chargerBtn = new JButton( "Charger Partie" );
    private JButton    cancelBtn  = new JButton( "Quitter" );
    private JTextField idPartie   = new JTextField( 10 );
    private Long       id         = Long.parseLong( "-1" );

    public Long getId() {
        return id;
    }

    public void setId( Long id ) {
        this.id = id;
    }

    public Dialogue( JFrame frame, Long idJoueur ) {
        super( frame, "Listes Des Parteis", true );

        JPanel panelEnter = new JPanel();
        idPartie.setText( "Id Partie ici :" );
        idPartie.addMouseListener( new MouseAdapter() {
            public void mouseClicked( MouseEvent arg0 ) {
                idPartie.setText( "" );
            }
        } );
        panelEnter.add( idPartie );
        panelEnter.add( chargerBtn );
        panelEnter.add( cancelBtn );

        cancelBtn.setPreferredSize( chargerBtn.getPreferredSize() );
        cancelBtn.addActionListener( this );
        chargerBtn.addActionListener( this );

        JPanel panel = new JPanel();
        panel.setLayout( new GridBagLayout() );

        GridBagConstraints g = new GridBagConstraints();
        g.gridx = 0;
        g.gridy = 0;
        g.gridwidth = GridBagConstraints.REMAINDER;
        g.gridheight = 1;
        panel.add( panelEnter, g );

        JTable tableau = new JTable( new ModelJTable( idJoueur ) );
        g.gridx = 0;
        g.gridy = 1;
        g.gridwidth = GridBagConstraints.REMAINDER;
        g.gridheight = GridBagConstraints.REMAINDER;
        g.fill = GridBagConstraints.BOTH;
        panel.add( new JScrollPane( tableau ), g );

        setLayout( new BorderLayout() );
        add( panel, BorderLayout.CENTER );

        pack();
    }

    public Long lanceDial() {
        setVisible( true );
        if ( ok ) {
            if ( !( idPartie.getText().equals( "" ) ) ) {
                id = Long.parseLong( idPartie.getText() );
                return id;
            }
        }
        return (long) -1;
    }

    public void actionPerformed( ActionEvent e )
    {
        if ( e.getSource() == chargerBtn ) {
            ok = true;
            setVisible( false );
        }
        if ( e.getSource() == cancelBtn )
            setVisible( false );
    }

    private boolean ok = false;
}
