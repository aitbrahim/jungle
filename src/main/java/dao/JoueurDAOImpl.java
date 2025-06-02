package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import model.Joueur;

public class JoueurDAOImpl extends JoueurDAO {

    @Override
    public Joueur findByName( String name ) {
        Joueur joueur = null;

        try {
            ResultSet result = this.connect.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
                    ).executeQuery( "SELECT * FROM joueur WHERE pseudo = '" + name + "'" );
            if ( result.first() )
                joueur = new Joueur( result.getLong( "id" ), result.getString( "pseudo" ),
                        result.getString( "password" ) );
        } catch ( SQLException e ) {
            e.printStackTrace();
        }
        return joueur;
    }

    @Override
    public Joueur find( long id ) {
        Joueur joueur = new Joueur();
        try {
            ResultSet result = this.connect.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE
                    ).executeQuery(
                            "SELECT * FROM joueur WHERE id = " + id
                    );

            if ( result.first() )

                joueur = new Joueur(
                        result.getLong( "id" ),
                        result.getString( "pseudo" ),
                        result.getString( "password" )
                        );
        } catch ( SQLException e ) {
            e.printStackTrace();
        }
        return joueur;
    }

    @Override
    public Joueur create( Joueur joueur ) {
        try {

            PreparedStatement prepare = this.connect.prepareStatement(
                    "INSERT INTO joueur (pseudo, password) VALUES(?, ?) "
                    );
            prepare.setString( 1, joueur.getPseudo() );
            prepare.setString( 2, joueur.getPassword() );

            prepare.executeUpdate();
            ResultSet result = this.connect.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE
                    ).executeQuery(
                            "SELECT * FROM joueur"
                    );
            result.last();

            joueur = this.find( result.getLong( "id" ) );
        } catch ( SQLException e ) {
            e.printStackTrace();
        }

        return joueur;
    }

    @Override
    public Joueur update( Joueur obj ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void delete( Joueur obj ) {
        // TODO Auto-generated method stub

    }

    @Override
    public List<Joueur> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

}
