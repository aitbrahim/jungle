package dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Joueur;
import model.Partie;
import model.Piece;

public class PartieDAOImpl extends PartieDAO {

    @Override
    public Partie find( long id ) {
        Partie partie = new Partie();

        try {
            ResultSet result = this.connect.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE
                    ).executeQuery( "SELECT * FROM  partie , partie_piece ,piece "
                            + "WHERE partie.id = partie_piece.partie_id "
                            + "and partie_piece.piece_id = piece.id "
                            + "and partie.id = " + id );

            if ( result.first() ) {
                DAO<Piece> pieceDAO = new DAOFactory().getPieceDAO();
                List<Piece> pieces = new ArrayList<Piece>();
                result.beforeFirst();
                while ( result.next() ) {
                    pieces.add( pieceDAO.find( result.getLong( "piece.id" ) ) );
                }
                result.first();
                partie = new Partie( id,
                        (Joueur) new DAOFactory().getJoueurDAO().find( result.getLong( "joueur_id" ) ),
                        result.getInt( "scorePlayer1" ),
                        result.getInt( "scorePlayer2" ),
                        result.getDate( "datePartie" ),
                        result.getInt( "tourPlayer" ),
                        result.getBoolean( "fin_partie" ),
                        pieces );
            }
        } catch ( SQLException e ) {
            e.printStackTrace();
        }
        return partie;
    }

    @Override
    public Partie create( Partie partie ) {
        try {

            if ( partie.getPlayer1().getId() == 0 ) {
                DAO<Joueur> userDAO = new DAOFactory().getJoueurDAO();

                partie.setPlayer1( userDAO.create( partie.getPlayer1() ) );
            }

            PreparedStatement prepare = this.connect
                    .prepareStatement(

                    "INSERT INTO partie (tourPlayer, datePartie , scorePlayer1 , scorePlayer2 , joueur_id , fin_partie) VALUES(?, ? , ? , ? ,?,?)"
                    );

            prepare.setInt( 1, partie.getTourPlayer() );
            prepare.setDate( 2, (Date) partie.getDatePartie() );
            prepare.setInt( 3, partie.getScorePlayer1() );
            prepare.setInt( 4, partie.getScorePlayer2() );
            prepare.setLong( 5, partie.getPlayer1().getId() );
            prepare.setBoolean( 6, partie.isFinPartie() );
            prepare.executeUpdate();

            ResultSet result = this.connect.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE
                    ).executeQuery(
                            "SELECT * FROM partie"
                    );

            result.last();
            long id = result.getLong( "id" );
            partie.setId( id );

            for ( int i = 0; i < partie.getPieces().size(); i++ ) {

                if ( partie.getPieces().get( i ).getId() == 0 ) {
                    DAO<Piece> pieceDAO = new DAOFactory().getPieceDAO();
                    Piece piece = pieceDAO.create( partie.getPieces().get( i ) );
                    partie.getPieces().set( i, piece );
                }
                PreparedStatement prepare2 = this.connect
                        .prepareStatement(
                        "INSERT INTO partie_piece (partie_id, piece_id) " +
                                " VALUES(? , ? ) "
                        );
                prepare2.setLong( 1, id );
                prepare2.setLong( 2, partie.getPieces().get( i ).getId() );
                prepare2.executeUpdate();

            }
        } catch ( SQLException e ) {
            e.printStackTrace();
        }
        return partie;
    }

    @Override
    public Partie update( Partie obj ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void delete( Partie obj ) {
        // TODO Auto-generated method stub

    }

    @Override
    public List<Partie> findAll() {
        Partie partie = null;
        List<Partie> parties = new ArrayList<Partie>();
        try {
            ResultSet result = this.connect.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE
                    ).executeQuery(
                            "SELECT * FROM partie"
                    );
            result.beforeFirst();
            while ( result.next() ) {

                partie = new Partie(
                        result.getLong( "id" ),
                        result.getInt( "scorePlayer1" ),
                        result.getInt( "scorePlayer2" ),
                        result.getDate( "datePartie" ),
                        result.getInt( "tourPlayer" ),
                        result.getBoolean( "fin_partie" ),
                        (Joueur) new DAOFactory().getJoueurDAO().find( result.getLong( "joueur_id" ) )
                        );
                parties.add( partie );
            }
        } catch ( SQLException e ) {
            e.printStackTrace();
        }
        return parties;
    }

    @Override
    public List<Partie> findByJoueur( Long id ) {
        Partie partie = null;
        List<Partie> parties = new ArrayList<Partie>();
        try {
            ResultSet result = this.connect.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE
                    ).executeQuery(
                            "SELECT * FROM partie WHERE joueur_id = " + id
                    );
            result.beforeFirst();
            while ( result.next() ) {

                partie = new Partie(
                        result.getLong( "id" ),
                        result.getInt( "scorePlayer1" ),
                        result.getInt( "scorePlayer2" ),
                        result.getDate( "datePartie" ),
                        result.getInt( "tourPlayer" ),
                        result.getBoolean( "fin_partie" ),
                        (Joueur) new DAOFactory().getJoueurDAO().find( id )
                        );
                parties.add( partie );
            }
        } catch ( SQLException e ) {
            e.printStackTrace();
        }
        return parties;

    }
}
