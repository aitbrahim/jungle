package model;

import java.util.ArrayList;
import java.util.List;

import metier.Echiquier;
import metier.OutOfRangeException;

public class PieceRat extends Piece {

    public PieceRat( Point position, int player, Echiquier echiquier ) {
        super( position, player, Animal.Rat, echiquier, false );
    }

    public PieceRat( Long id, Point point, int player, boolean trapped ) {
        super( id, point, player, Animal.Rat, trapped );
    }

    public List<Point> getPossibleMoves() {

        List<Point> possibleMoves = new ArrayList<Point>();
        try {
            possibleMoves = echiquier.getAllPossibleMoves( position, player );
            System.out.println( "avant filtre = " + possibleMoves.size() );

            int compteur = 0;

            while ( compteur < possibleMoves.size() ) {

                int posX = possibleMoves.get( compteur ).getX();
                int posY = possibleMoves.get( compteur ).getY();

                int x = position.getX();
                int y = position.getY();
                Piece piece = echiquier.getPieceAtt( new Point( posX, posY ) );

                if ( piece != null ) {

                    if ( piece.getPlayer() == player ) {
                        System.out.println( "piece Amis" );
                        possibleMoves.remove( possibleMoves.get( compteur ) );
                    }
                    // traped ne mange jamais
                    else if ( piece.getPlayer() != player && this.isTrapped() == true ) {
                        System.out.println( "etat piege ne peut pas manger" );
                        possibleMoves.remove( possibleMoves.get( compteur ) );
                    }

                    // cas Rat ne peut pas manger sortant du riviere
                    else if ( ( ( y >= 3 && y <= 5 ) && ( ( x >= 1 && x <= 2 ) || ( x >= 4 && x <= 5 ) ) )
                            && !( ( posY >= 3 && posY <= 5 ) && ( ( posX >= 1 && posX <= 2 ) || ( posX >= 4 && posX <= 5 ) ) ) ) {
                        System.out.println( "rat ne peut pas manger sortant du riviere" );
                        possibleMoves.remove( possibleMoves.get( compteur ) );
                    }
                    else if ( piece.getPlayer() != player
                            && piece.getAnimal().toString().equals( Animal.Elephant.toString() ) ) {
                        System.out.println( "Rat mange un Elephant" );
                        compteur++;
                    }

                    else if ( piece.getPlayer() != player && piece.getAnimal().getRank() < animal.getRank()
                            && piece.isTrapped() == false ) {
                        System.out.println( "piece ennemi mais de range superieure" );
                        possibleMoves.remove( possibleMoves.get( compteur ) );
                    }
                    else
                        compteur++;
                }
                else
                    compteur++;

            }
            System.out.println( "apres filtre = " + possibleMoves.size() );

        } catch ( OutOfRangeException e ) {
            System.out.println( e.getMessage() );
        }
        return possibleMoves;
    }
}
