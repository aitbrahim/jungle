package model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Partie {

    private Long        id           = new Long( 0 );
    private Joueur      player1;
    private int         scorePlayer1 = 0;
    private int         scorePlayer2 = 0;

    private Date        datePartie   = new Date( 0 );
    private int         tourPlayer   = 1;
    private boolean     finPartie    = false;

    private List<Piece> pieces       = new ArrayList<Piece>();

    public Partie() {
    }

    public Partie( Joueur player1, int scorePlayer1, int scorePlayer2, Date datePartie, int tourPlayer,
            boolean finPartie, List<Piece> pieces ) {
        this.player1 = player1;
        this.scorePlayer1 = scorePlayer1;
        this.scorePlayer2 = scorePlayer2;
        this.datePartie = datePartie;
        this.tourPlayer = tourPlayer;
        this.finPartie = finPartie;
        this.pieces = pieces;
    }

    public Partie( Long id, Joueur player1, int scorePlayer1, int scorePlayer2, Date datePartie, int tourPlayer,
            boolean finPartie, List<Piece> pieces ) {
        super();
        this.id = id;
        this.player1 = player1;
        this.scorePlayer1 = scorePlayer1;
        this.scorePlayer2 = scorePlayer2;
        this.datePartie = datePartie;
        this.tourPlayer = tourPlayer;
        this.finPartie = finPartie;
        this.pieces = pieces;
    }

    public Partie( Long id, int scorePlayer1, int scorePlayer2, Date datePartie, int tourPlayer,
            boolean finPartie, Joueur player1 ) {
        super();
        this.id = id;
        this.scorePlayer1 = scorePlayer1;
        this.scorePlayer2 = scorePlayer2;
        this.datePartie = datePartie;
        this.tourPlayer = tourPlayer;
        this.finPartie = finPartie;
        this.player1 = player1;
    }

    public Long getId() {
        return id;
    }

    public void setId( Long id ) {
        this.id = id;
    }

    public Joueur getPlayer1() {
        return player1;
    }

    public void setPlayer1( Joueur player1 ) {
        this.player1 = player1;
    }

    public int getScorePlayer1() {
        return scorePlayer1;
    }

    public void setScorePlayer1( int scorePlayer1 ) {
        this.scorePlayer1 = scorePlayer1;
    }

    public int getScorePlayer2() {
        return scorePlayer2;
    }

    public void setScorePlayer2( int scorePlayer2 ) {
        this.scorePlayer2 = scorePlayer2;
    }

    public Date getDatePartie() {
        return datePartie;
    }

    public void setDatePartie( Date datePartie ) {
        this.datePartie = datePartie;
    }

    public int getTourPlayer() {
        return tourPlayer;
    }

    public void setTourPlayer( int tourPlayer ) {
        this.tourPlayer = tourPlayer;
    }

    public boolean isFinPartie() {
        return finPartie;
    }

    public void setFinPartie( boolean finPartie ) {
        this.finPartie = finPartie;
    }

    public List<Piece> getPieces() {
        return pieces;
    }

    public void setPieces( List<Piece> pieces ) {
        this.pieces = pieces;
    }
}
