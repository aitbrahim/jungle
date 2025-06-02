package model;

import java.util.ArrayList;
import java.util.List;

import metier.Echiquier;
import metier.OutOfRangeException;

public class PieceTigre extends Piece {

    public PieceTigre( Point position, int player, Echiquier echiquier ) {
        super( position, player, Animal.Tigre, echiquier, false );
    }

    public PieceTigre( Long id, Point point, int player, boolean trapped ) {
        super( id, point, player, Animal.Tigre, trapped );
    }

    public List<Point> getPossibleMoves() {

        List<Point> possibleMoves = new ArrayList<Point>();
        try {
            // recuperer list des allposition possible
            possibleMoves = echiquier.getAllPossibleMoves( position, player );
            System.out.println( "avant filtre = " + possibleMoves.size() );

            int compteur = 0;
            while ( compteur < possibleMoves.size() ) {
                int posX = possibleMoves.get( compteur ).getX();
                int posY = possibleMoves.get( compteur ).getY();

                if ( ( posY >= 3 && posY <= 5 ) && ( ( posX >= 1 && posX <= 2 ) || ( posX >= 4 && posX <= 5 ) ) ) {
                    // cas dimunition de Y
                    if ( position.getX() == posX && position.getY() > posY ) {
                        if ( echiquier.getPieceAtt( new Point( posX, posY ) ) == null &&
                                echiquier.getPieceAtt( new Point( posX, posY - 1 ) ) == null &&
                                echiquier.getPieceAtt( new Point( posX, posY - 2 ) ) == null ) {
                            possibleMoves.get( compteur ).setY( position.getY() - 4 );
                        }
                        else {
                            possibleMoves.remove( possibleMoves.get( compteur ) );
                            continue;
                        }

                    }
                    // cas augmentation de Y
                    else if ( position.getX() == posX && position.getY() < posY ) {
                        if ( echiquier.getPieceAtt( new Point( posX, posY ) ) == null &&
                                echiquier.getPieceAtt( new Point( posX, posY + 1 ) ) == null &&
                                echiquier.getPieceAtt( new Point( posX, posY + 2 ) ) == null ) {
                            possibleMoves.get( compteur ).setY( position.getY() + 4 );
                        }
                        else {
                            possibleMoves.remove( possibleMoves.get( compteur ) );
                            continue;
                        }
                    }

                    // cas dimunition de X
                    else if ( position.getY() == posY && position.getX() > posX ) {
                        if ( echiquier.getPieceAtt( new Point( posX, posY ) ) == null &&
                                echiquier.getPieceAtt( new Point( posX - 1, posY ) ) == null ) {
                            possibleMoves.get( compteur ).setX( position.getX() - 3 );
                        }
                        else {
                            possibleMoves.remove( possibleMoves.get( compteur ) );
                            continue;
                        }
                    }
                    // cas augmentation de X
                    else if ( position.getY() == posY && position.getX() < posX ) {
                        if ( echiquier.getPieceAtt( new Point( posX, posY ) ) == null &&
                                echiquier.getPieceAtt( new Point( posX + 1, posY ) ) == null ) {
                            possibleMoves.get( compteur ).setX( position.getX() + 3 );
                        }
                        else {
                            possibleMoves.remove( possibleMoves.get( compteur ) );
                            continue;
                        }
                    }
                }

                Piece piece = echiquier.getPieceAtt( possibleMoves.get( compteur ) );

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
