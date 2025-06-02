package vue;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import metier.JoueurMetierImpl;
import metier.PartieMetierImpl;
import model.Joueur;
import model.Partie;

public class MFrame extends JFrame {

    private PartiePanel      panelCenter;
    private Partie           partie;

    private JTabbedPane      onglet;
    private HomePanel        panelHome;
    private InscriptionPanel inscriptionPanel;
    private Joueur           joueur;
    private JoueurMetierImpl metierJoueur = new JoueurMetierImpl();
    private PartieMetierImpl metierPartie = new PartieMetierImpl();

    private InfoToolBar      infoToolBar;

    Dimension                dimension    = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
    int                      height       = (int) dimension.getHeight();
    int                      width        = (int) dimension.getWidth();

    final MFrame             frame        = this;
    protected MenuBar        menuBar;
    private MenuListener     menuListener;

    public MFrame() {
        onglet = new JTabbedPane();
        panelHome = new HomePanel();
        inscriptionPanel = new InscriptionPanel();

        onglet.add( "Login", panelHome );
        onglet.add( "Inscription", inscriptionPanel );

        add( onglet );
        setSize( width / 2 - width / 8, height / 3 );
        // login
        panelHome.getBtnSubmit().addActionListener( new ActionListener() {

            public void actionPerformed( ActionEvent e ) {

                String userName = panelHome.getPseudoField().getText();
                String password = panelHome.getPasswordField().getText();

                joueur = metierJoueur.checkUserLogin( userName, password );
                if ( joueur != null ) {
                    frame.setSize( width / 2 - width / 12, height - height / 7 );
                    infoToolBar = new InfoToolBar();
                    infoToolBar.getLabelPseudo().setText( joueur.getPseudo() );
                    remove( onglet );
                    add( infoToolBar, "North" );
                    setJMenuBar( menuBar );
                    partie = new Partie();
                    partie.setPlayer1( joueur );
                    add( panelCenter = new PartiePanel( partie, PartiePanel.NEW_PARTIE, frame ) );
                    infoToolBar.getLabelScoreP1().setText( "Score " +
                            joueur.getPseudo() + ": " );
                    revalidate();
                }
                else {
                    JOptionPane.showMessageDialog( null, "information incorrects", "error", JOptionPane.ERROR_MESSAGE );
                }
            }
        } );

        // inscription
        inscriptionPanel.getBtnSubmit().addActionListener( new ActionListener() {

            public void actionPerformed( ActionEvent e ) {

                String userName = inscriptionPanel.getPseudoField().getText();
                String password = inscriptionPanel.getPasswordField().getText();

                joueur = metierJoueur.findByName( userName );
                if ( joueur == null ) {
                    Joueur newJoueur = new Joueur( userName, password );
                    if ( ( joueur = metierJoueur.createUser( newJoueur ) ) != null ) {
                        JOptionPane.showMessageDialog( null, "vous �tes enregistr� avec succ�es", "Information",
                                JOptionPane.INFORMATION_MESSAGE );

                        frame.setSize( width / 2 - width / 12, height - height / 7 );
                        infoToolBar = new InfoToolBar();
                        infoToolBar.getLabelPseudo().setText( joueur.getPseudo() );
                        remove( onglet );
                        add( infoToolBar, "North" );
                        setJMenuBar( menuBar );
                        partie = new Partie();
                        partie.setPlayer1( joueur );
                        add( panelCenter = new PartiePanel( partie, PartiePanel.NEW_PARTIE, frame ) );
                        revalidate();

                    }
                }
            }
        } );

        menuBar = new MenuBar();
        menuListener = new MenuListener();
        menuBar.getQuitter().addActionListener( menuListener );
        menuBar.getNouveauPartie().addActionListener( menuListener );
        menuBar.getSavePartie().addActionListener( menuListener );
        menuBar.getChargerPartie().addActionListener( menuListener );

        menuBar.getMesScores().addActionListener( menuListener );
        menuBar.getMeilleurScore().addActionListener( menuListener );

        setUpLook( "Nimbus" );

        setVisible( true );
    }

    public class MenuListener implements ActionListener {

        private Dialogue dialogue = null;

        @Override
        public void actionPerformed( ActionEvent e ) {
            // action fermer la fenetre
            if ( e.getSource() == menuBar.getQuitter() ) {
                System.exit( 0 );
            }
            // action Nouveau Partie
            if ( e.getSource() == menuBar.getNouveauPartie() ) {
                remove( panelCenter );
                partie = new Partie();
                partie.setPlayer1( joueur );
                panelCenter = new PartiePanel( partie, PartiePanel.NEW_PARTIE, frame );
                remove( infoToolBar );
                infoToolBar = new InfoToolBar();
                add( infoToolBar, "North" );
                add( panelCenter );
                revalidate();
            }
            // action savegarger une partie
            if ( e.getSource() == menuBar.getSavePartie() ) {
                partie = metierPartie.createPartie( partie );
                int choix = JOptionPane.showConfirmDialog( null,
                        "partie sauvegarder avec succées voulez vous continuer ?",
                        "INCIDENT MAJEUR", JOptionPane.YES_NO_OPTION );
                if ( choix == 0 ) {
                    remove( panelCenter );
                    panelCenter = new PartiePanel( partie, PartiePanel.CHARGER_PARTIE, frame );
                    add( panelCenter );
                    revalidate();

                } else {
                    System.exit( 0 );
                }

            }

            // action charger partie
            if ( e.getSource() == menuBar.getChargerPartie() ) {
                dialogue = new Dialogue( frame, joueur.getId() );
                Long id = dialogue.lanceDial();
                if ( id != -1 ) {
                    partie = metierPartie.find( id );
                    remove( panelCenter );
                    panelCenter = new PartiePanel( partie, PartiePanel.CHARGER_PARTIE, frame );
                    infoToolBar.getLabelScoreP2Value().setText( "" +
                            partie.getScorePlayer2() );
                    infoToolBar.getLabelScoreP1Value().setText( "" +
                            partie.getScorePlayer1() );
                    add( panelCenter );
                    revalidate();
                }
            }

            // recuper les score du joueur
            if ( e.getSource() == menuBar.getMesScores() ) {

                new ScoreDialogue( frame, joueur.getId() );
            }

            // recuperer le meilleur score du jeu
            if ( e.getSource() == menuBar.getMeilleurScore() ) {
                List<Partie> parties = metierPartie.findAll();
                int max = 0;
                if ( parties.size() == 0 )
                    JOptionPane.showMessageDialog( frame, "Pas de score pour l'instant ", "Meilleur Score",
                            JOptionPane.INFORMATION_MESSAGE );

                if ( parties.get( 0 ).getScorePlayer1() > parties.get( 0 ).getScorePlayer2() )
                    max = parties.get( 0 ).getScorePlayer1();
                else
                    max = parties.get( 0 ).getScorePlayer2();

                for ( int i = 1; i < parties.size(); i++ ) {

                    if ( parties.get( i ).getScorePlayer1() >= parties.get( i ).getScorePlayer2()
                            && parties.get( i ).getScorePlayer1() >= max ) {

                        max = parties.get( i ).getScorePlayer1();
                    }

                    else if ( parties.get( i ).getScorePlayer2() > parties.get( i ).getScorePlayer1()
                            && parties.get( i ).getScorePlayer2() >= max ) {

                        max = parties.get( i ).getScorePlayer2();
                    }
                }

                JOptionPane.showMessageDialog( frame, "Meilleur Score : " + max, "Meilleur Score",
                        JOptionPane.INFORMATION_MESSAGE );
            }
        }
    }

    public void setUpLook( String look ) {
        try {
            for ( LookAndFeelInfo info : UIManager.getInstalledLookAndFeels() ) {
                if ( look.equals( info.getName() ) ) {
                    UIManager.setLookAndFeel( info.getClassName() );
                    SwingUtilities.updateComponentTreeUI( this );
                    break;
                }
            }
        } catch ( Exception e ) {
            System.err.println( "Look & Feel intouvable" );
        }
    }

    public static void main( String[] args ) {
        new MFrame();
    }

    public PartiePanel getPanelCenter() {
        return panelCenter;
    }

    public void setPanelCenter( PartiePanel panelCenter ) {
        this.panelCenter = panelCenter;
    }

    public Partie getPartie() {
        return partie;
    }

    public void setPartie( Partie partie ) {
        this.partie = partie;
    }

    public InfoToolBar getInfoToolBar() {
        return infoToolBar;
    }

    public void setInfoToolBar( InfoToolBar infoToolBar ) {
        this.infoToolBar = infoToolBar;
    }
}
