package model;

import java.util.List;

import metier.Echiquier;

public abstract class Piece {

    private int[][]     trap       = { { 2, 0 }, { 3, 0 }, { 4, 0 }, { 3, 1 },
                                   { 3, 7 }, { 2, 8 }, { 3, 8 }, { 4, 8 } };
    private int[][]     sanctuaire = { { 3, 0 }, { 3, 8 } };

    private Long        id         = new Long( 0 );
    protected Point     position;
    protected int       player;
    protected Animal    animal;
    protected Echiquier echiquier;
    protected boolean   trapped;

    public Piece( Point position, int player, Animal animal, Echiquier echiquier, boolean trapped ) {
        this.position = position;
        this.player = player;
        this.animal = animal;
        this.echiquier = echiquier;
        this.trapped = trapped;
    }

    public Piece( Long id, Point position, int player, Animal animal, boolean trapped ) {
        this.position = position;
        this.player = player;
        this.animal = animal;
        this.trapped = trapped;
        this.id = id;
    }

    public abstract List<Point> getPossibleMoves();

    public void deplacer( Point position ) {
        Piece piece = echiquier.getPieceAtt( position );
        if ( piece != null ) {
            echiquier.getPieces().remove( piece );
        }
        this.position = position;
        if ( isTrapped( position.getX(), position.getY() ) )
            setTrapped( true );
        else
            setTrapped( false );
    }

    public boolean isTrapped( int x, int y ) {
        for ( int i = 0; i < trap.length; i++ ) {
            if ( trap[i][0] == x && trap[i][1] == y )
                return true;
        }
        if ( ( sanctuaire[0][0] == x && sanctuaire[0][1] == y )
                || ( sanctuaire[1][0] == x && sanctuaire[1][1] == y ) )
            return true;

        return false;
    }

    public Long getId() {
        return id;
    }

    public void setId( Long id ) {
        this.id = id;
    }

    public boolean isTrapped() {
        return trapped;
    }

    public void setTrapped( boolean trapped ) {
        this.trapped = trapped;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition( Point position ) {
        this.position = position;
    }

    public int getPlayer() {
        return player;
    }

    public void setPlayer( int player ) {
        this.player = player;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal( Animal animal ) {
        this.animal = animal;
    }

    public Echiquier getEchiquier() {
        return echiquier;
    }

    public void setEchiquier( Echiquier echiquier ) {
        this.echiquier = echiquier;
    }
}
