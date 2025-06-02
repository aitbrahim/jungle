package vue;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import metier.Echiquier;
import metier.PartieMetierImpl;
import model.Partie;
import model.Piece;
import model.Point;

public class PartiePanel extends JPanel {

    private int[][]          piege          = { { 0, 2 }, { 0, 3 }, { 0, 4 }, { 1, 3 },
                                            { 7, 3 }, { 8, 2 }, { 8, 3 }, { 8, 4 } };
    private int[][]          sanctuaire     = { { 0, 3 }, { 8, 3 } };
    private int[][]          riviere        = { { 3, 1 }, { 3, 2 }, { 4, 1 }, { 4, 2 },
                                            { 5, 1 }, { 5, 2 }, { 3, 4 }, { 3, 5 }, { 4, 4 }, { 4, 5 },
                                            { 5, 4 }, { 5, 5 } };

    private Partie           partie;
    private MButton[][]      mButtons       = new MButton[9][7];
    private Echiquier        echiquier;
    private BtnsListener     btnsListener   = new BtnsListener();
    private PartieMetierImpl metierPartie   = new PartieMetierImpl();
    private MFrame           frame;

    public final static int  NEW_PARTIE     = 1;
    public final static int  CHARGER_PARTIE = 2;

    public PartiePanel( Partie partie, int type, MFrame frame ) {
        setLayout( new GridLayout( 9, 7 ) );
        this.partie = partie;
        this.frame = frame;
        if ( type == PartiePanel.NEW_PARTIE ) {
            echiquier = new Echiquier( partie, PartiePanel.NEW_PARTIE );
        }
        else {
            echiquier = new Echiquier( partie, PartiePanel.CHARGER_PARTIE );
        }

        initBtns();
        colorBoard();
        iniBoard();

    }

    private void iniBoard() {

        for ( int i = 0; i < 9; i++ ) {
            for ( int j = 0; j < 7; j++ ) {
                mButtons[i][j].setPiece( echiquier.getPieceAtt( new Point( j, i ) ) );
            }
        }
    }

    public void initBtns() {
        for ( int i = 0; i < 9; i++ ) {
            for ( int j = 0; j < 7; j++ ) {
                mButtons[i][j] = new MButton( j, i );
            }
        }
    }

    public void colorBoard() {

        for ( int i = 0; i < 9; i++ ) {
            for ( int j = 0; j < 7; j++ ) {
                mButtons[i][j].setBackground( Color.WHITE );
                mButtons[i][j].addActionListener( btnsListener );
                add( mButtons[i][j] );
            }
        }

        // couleur echiquier
        for ( int i = 3; i < 6; i++ ) {
            for ( int j = 1; j < 6; j++ ) {
                if ( j == 3 )
                    continue;
                mButtons[i][j].setBackground( Color.CYAN );
            }
        }

        // couleur piege
        mButtons[0][2].setBackground( Color.GREEN );
        mButtons[0][4].setBackground( Color.GREEN );
        mButtons[1][3].setBackground( Color.GREEN );
        mButtons[0][3].setBackground( Color.ORANGE );

        mButtons[8][2].setBackground( Color.GREEN );
        mButtons[8][4].setBackground( Color.GREEN );
        mButtons[7][3].setBackground( Color.GREEN );
        mButtons[8][3].setBackground( Color.ORANGE );
    }

    public class BtnsListener implements ActionListener {

        private MButton     btnClicked, btnFirstClick, btnSecondClick;
        private boolean     click;
        private List<Point> possibleMoves;

        public void actionPerformed( ActionEvent e ) {

            btnClicked = (MButton) e.getSource();

            if ( !partie.isFinPartie() ) {
                if ( !click ) {

                    btnFirstClick = btnClicked;
                    if ( btnFirstClick.getPiece() != null
                            && btnFirstClick.getPiece().getPlayer() == partie.getTourPlayer() ) {
                        Point position = btnFirstClick.getPiece().getPosition();
                        possibleMoves = btnFirstClick.getPiece().getPossibleMoves();
                        setColor( possibleMoves, Color.GRAY );
                        click = true;
                    }
                }
                else {// click 2
                    btnSecondClick = btnClicked;
                    if ( btnSecondClick == btnFirstClick ) {
                        setColor( possibleMoves, Color.WHITE );
                        setColor( piege, Color.GREEN );
                        setColor( sanctuaire, Color.ORANGE );
                        setColor( riviere, Color.CYAN );
                        click = false;
                    }
                    else {
                        Point position = new Point( btnSecondClick.getPosX(), btnSecondClick.getPosY() );
                        for ( Point point : possibleMoves ) {

                            if ( point.equals( position ) ) {
                                // mise a jour du score
                                if ( btnSecondClick.getPiece() != null && partie.getTourPlayer() == 1 ) {
                                    partie.setScorePlayer1( partie.getScorePlayer1()
                                            + btnSecondClick.getPiece().getAnimal().getRank() );
                                    frame.getInfoToolBar().getLabelScoreP1Value()
                                            .setText( "" + partie.getScorePlayer1() );
                                }
                                else if ( btnSecondClick.getPiece() != null && partie.getTourPlayer() == 2 ) {
                                    partie.setScorePlayer2( partie.getScorePlayer2()
                                            + btnSecondClick.getPiece().getAnimal().getRank() );
                                    frame.getInfoToolBar().getLabelScoreP2Value()
                                            .setText( "" + partie.getScorePlayer2() );
                                }

                                btnFirstClick.getPiece().deplacer( position );
                                btnFirstClick.setPiece( null );
                                Piece p = echiquier.getPieceAtt( position );
                                btnSecondClick.setPiece( echiquier.getPieceAtt( position ) );

                                setColor( possibleMoves, Color.WHITE );
                                setColor( piege, Color.GREEN );
                                setColor( sanctuaire, Color.ORANGE );
                                setColor( riviere, Color.CYAN );
                                click = false;

                                // cas fin du jeu
                                if ( isTerminated( position ) == 1 || echiquier.nbrPiecesPlayer1() == 0 ) {
                                    partie.setFinPartie( true );
                                    int n = JOptionPane.showConfirmDialog( null,
                                            "Joueur 1 gagn� \n voulez vous sauvegarder la partie ?",
                                            "INCIDENT MAJEUR", JOptionPane.YES_NO_OPTION );
                                    if ( n == 0 )
                                        metierPartie.createPartie( partie );
                                }
                                else if ( isTerminated( position ) == 2 || echiquier.nbrPiecesPlayer2() == 0 ) {
                                    partie.setFinPartie( true );
                                    int n = JOptionPane.showConfirmDialog( null,
                                            "Joueur 2 gagn� \n voulez vous sauvegarder la partie ?",
                                            "INCIDENT MAJEUR", JOptionPane.YES_NO_OPTION );
                                    if ( n == 0 )
                                        metierPartie.createPartie( partie );

                                }

                                // changer le tour de jeu
                                if ( partie.getTourPlayer() == 1 )
                                    partie.setTourPlayer( 2 );
                                else
                                    partie.setTourPlayer( 1 );

                            }
                        }
                    }
                }
            }

        }

        private int isTerminated( Point position ) {
            if ( partie.getTourPlayer() == 1 && position.getX() == 3 && position.getY() == 8 ) {
                return 1;
            }
            else if ( partie.getTourPlayer() == 2 && position.getX() == 3 && position.getY() == 0 ) {
                return 2;
            }
            else
                return -1;
        }

        public void setColor( List<Point> possibleMoves, Color couleur ) {
            for ( Point point : possibleMoves ) {
                mButtons[point.getY()][point.getX()].setBackground( couleur );
            }
        }

        public void setColor( int[][] tab, Color color ) {
            for ( int i = 0; i < tab.length; i++ ) {
                mButtons[tab[i][0]][tab[i][1]].setBackground( color );
            }
        }

    }
}
