package metier;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import model.Partie;
import model.Piece;
import model.PieceChat;
import model.PieceChient;
import model.PieceElephant;
import model.PieceLion;
import model.PieceLoup;
import model.PiecePanthere;
import model.PieceRat;
import model.PieceTigre;
import model.Point;
import vue.PartiePanel;

public class Echiquier {

    private List<Piece> pieces;
    private Partie      partie;

    public Echiquier( Partie partie, int type ) {
        if ( type == PartiePanel.NEW_PARTIE ) {
            this.partie = partie;
            pieces = partie.getPieces();
            initEchiquier();
        }
        else if ( type == PartiePanel.CHARGER_PARTIE ) {
            this.partie = partie;
            pieces = partie.getPieces();
            initPieces();
        }
    }

    private void initPieces() {
        for ( int i = 0; i < pieces.size(); i++ ) {
            pieces.get( i ).setEchiquier( this );
        }
    }

    private void initEchiquier() {

        pieces.add( new PieceLion( new Point( 0, 0 ), 1, this ) );
        pieces.add( new PieceTigre( new Point( 6, 0 ), 1, this ) );
        pieces.add( new PieceChient( new Point( 1, 1 ), 1, this ) );
        pieces.add( new PieceChat( new Point( 5, 1 ), 1, this ) );
        pieces.add( new PieceRat( new Point( 0, 2 ), 1, this ) );
        pieces.add( new PiecePanthere( new Point( 2, 2 ), 1, this ) );
        pieces.add( new PieceLoup( new Point( 4, 2 ), 1, this ) );
        pieces.add( new PieceElephant( new Point( 6, 2 ), 1, this ) );

        pieces.add( new PieceLion( new Point( 6, 8 ), 2, this ) );
        pieces.add( new PieceTigre( new Point( 0, 8 ), 2, this ) );
        pieces.add( new PieceChient( new Point( 5, 7 ), 2, this ) );
        pieces.add( new PieceChat( new Point( 1, 7 ), 2, this ) );
        pieces.add( new PieceRat( new Point( 6, 6 ), 2, this ) );
        pieces.add( new PiecePanthere( new Point( 4, 6 ), 2, this ) );
        pieces.add( new PieceLoup( new Point( 2, 6 ), 2, this ) );
        pieces.add( new PieceElephant( new Point( 0, 6 ), 2, this ) );

    }

    public List<Point> getAllPossibleMoves( Point position, int player ) throws OutOfRangeException {

        int posX = position.getX();
        int posY = position.getY();
        List<Point> allPossibleMoves = new ArrayList<Point>();

        // cas Move Right
        try {
            Point p = rightMove( position );
            System.out.println( p );
            allPossibleMoves.add( p );
        } catch ( OutOfRangeException e ) {
            System.out.println( e.getMessage() );
        }
        // cas Move Left
        try {
            Point p = leftMove( position );
            System.out.println( p );
            allPossibleMoves.add( p );
        } catch ( OutOfRangeException e ) {
            System.out.println( e.getMessage() );
        }
        // cas Move Up
        try {
            Point p = upMove( position, player );
            System.out.println( p );
            allPossibleMoves.add( p );
        } catch ( OutOfRangeException e ) {
            System.out.println( e.getMessage() );
        }
        // cas Move Down
        try {
            Point p = downMove( position, player );
            System.out.println( p );
            allPossibleMoves.add( p );
        } catch ( OutOfRangeException e ) {
            System.out.println( e.getMessage() );
        }

        return allPossibleMoves;

    }

    public Point rightMove( Point position ) throws OutOfRangeException {

        Point p;
        int posX = position.getX();
        int posY = position.getY();

        if ( posX + 1 < 7 )
            return p = new Point( posX + 1, posY );
        else
            throw new OutOfRangeException( "impossible deplacement \" right \" n'appartient pas au range possible" );
    }

    public Point leftMove( Point position ) throws OutOfRangeException {

        Point p;
        int posX = position.getX();
        int posY = position.getY();

        if ( posX - 1 >= 0 )
            return p = new Point( posX - 1, posY );
        else
            throw new OutOfRangeException( "impossible deplacement \" left \" n'appartient pas au range possible" );
    }

    public Point upMove( Point position, int player ) throws OutOfRangeException {

        Point p;
        int posX = position.getX();
        int posY = position.getY();

        if ( player == 1 && posY + 1 < 9 )
            return p = new Point( posX, posY + 1 );
        else if ( player == 2 && posY - 1 >= 0 )
            return p = new Point( posX, posY - 1 );
        else
            throw new OutOfRangeException( "impossible deplacement \" up \" n'appartient pas au range possible" );
    }

    public Point downMove( Point position, int player ) throws OutOfRangeException {

        Point p;
        int posX = position.getX();
        int posY = position.getY();

        if ( player == 1 && posY - 1 >= 0 )
            return p = new Point( posX, posY - 1 );
        else if ( player == 2 && posY + 1 < 9 )
            return p = new Point( posX, posY + 1 );
        else
            throw new OutOfRangeException( "impossible deplacement \" down \" n'appartient pas au range possible" );
    }

    public List<Piece> getPieces() {
        return pieces;
    }

    public void setPieces( List<Piece> pieces ) {
        this.pieces = pieces;
    }

    public Piece getPieceAtt( Point position ) {

        for ( Piece piece : pieces ) {
            if ( piece.getPosition().equals( position ) )
                return piece;
        }

        return null;
    }

    public void setRandomMove( Piece p ) {

        List<Point> points = p.getPossibleMoves();

        int index = new Random().nextInt( points.size() );
        Point point = points.get( index );

        if ( getPieceAtt( point ) != null )
            pieces.remove( getPieceAtt( point ) );
        p.setPosition( point );
    }

    public void showEchiquier() {

        for ( int i = 0; i < 9; i++ ) {
            for ( int j = 0; j < 7; j++ ) {
                Piece p = getPieceAtt( new Point( j, i ) );
                if ( p != null )
                    System.out.print( "|       " + p.getAnimal().toString() );
                else
                    System.out.print( "|            " );
            }
            System.out.println( "|" );
            System.out.println( "--------------------------------------------------------------------------" );

        }
        System.out.println( "******************************************************************" );

    }

    public int nbrPiecesPlayer1() {
        int compteur = 0;
        for ( Piece piece : pieces ) {
            if ( piece.getPlayer() == 1 )
                compteur++;
        }
        return compteur;
    }

    public int nbrPiecesPlayer2() {
        int compteur = 0;
        for ( Piece piece : pieces ) {
            if ( piece.getPlayer() == 2 )
                compteur++;
        }
        return compteur;
    }

}
