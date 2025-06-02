package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import model.Piece;
import model.PieceChat;
import model.PieceChient;
import model.PieceElephant;
import model.PieceLion;
import model.PieceLoup;
import model.PiecePanthere;
import model.PieceRat;
import model.PieceTigre;

public class PieceDAOImpl extends DAO<Piece> {

    @Override
    public Piece find( long id ) {
        Piece piece = null;

        try {
            ResultSet result = this.connect.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
                    ).executeQuery(
                            "SELECT * FROM piece WHERE id = " + id
                    );

            if ( result.first() ) {
                switch ( result.getString( "animal" ) ) {
                case "Lion":
                    piece = new PieceLion(
                            id,
                            new model.Point( result.getInt( "posX" ), result.getInt( "posY" ) ),
                            result.getInt( "player" ),
                            result.getBoolean( "trapped" )
                            );

                    break;

                case "Tigre":
                    piece = new PieceTigre(
                            id,
                            new model.Point( result.getInt( "posX" ), result.getInt( "posY" ) ),
                            result.getInt( "player" ),
                            result.getBoolean( "trapped" )
                            );
                    break;
                case "Chien":
                    piece = new PieceChient(
                            id,
                            new model.Point( result.getInt( "posX" ), result.getInt( "posY" ) ),
                            result.getInt( "player" ),
                            result.getBoolean( "trapped" )
                            );

                    break;
                case "Chat":
                    piece = new PieceChat(
                            id,
                            new model.Point( result.getInt( "posX" ), result.getInt( "posY" ) ),
                            result.getInt( "player" ),
                            result.getBoolean( "trapped" )
                            );
                    break;
                case "Rat":
                    piece = new PieceRat(
                            id,
                            new model.Point( result.getInt( "posX" ), result.getInt( "posY" ) ),
                            result.getInt( "player" ),
                            result.getBoolean( "trapped" )
                            );

                    break;
                case "Panthere":
                    piece = new PiecePanthere(
                            id,
                            new model.Point( result.getInt( "posX" ), result.getInt( "posY" ) ),
                            result.getInt( "player" ),
                            result.getBoolean( "trapped" )
                            );

                    break;
                case "Loup":
                    piece = new PieceLoup(
                            id,
                            new model.Point( result.getInt( "posX" ), result.getInt( "posY" ) ),
                            result.getInt( "player" ),
                            result.getBoolean( "trapped" )
                            );

                    break;
                case "Elephant":
                    piece = new PieceElephant(
                            id,
                            new model.Point( result.getInt( "posX" ), result.getInt( "posY" ) ),
                            result.getInt( "player" ),
                            result.getBoolean( "trapped" )
                            );

                    break;

                }
            }
        } catch ( SQLException e ) {
            e.printStackTrace();
        }
        return piece;
    }

    @Override
    public Piece create( Piece piece ) {
        try {

            PreparedStatement prepare = this.connect
                    .prepareStatement(
                    "INSERT INTO piece (posX , posY , player , trapped , animal) " +
                            "VALUES( ?, ?, ? ,? ,? ) "
                    );
            prepare.setInt( 1, piece.getPosition().getX() );
            prepare.setInt( 2, piece.getPosition().getY() );
            prepare.setInt( 3, piece.getPlayer() );
            prepare.setBoolean( 4, piece.isTrapped() );
            prepare.setString( 5, piece.getAnimal().toString() );
            prepare.executeUpdate();

            ResultSet result = this.connect.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE
                    ).executeQuery(
                            "SELECT * FROM piece"
                    );
            result.last();
            piece = this.find( result.getLong( "id" ) );

        } catch ( SQLException e ) {
            e.printStackTrace();
        }
        return piece;
    }

    @Override
    public Piece update( Piece obj ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void delete( Piece obj ) {
        // TODO Auto-generated method stub

    }

    @Override
    public List<Piece> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

}
