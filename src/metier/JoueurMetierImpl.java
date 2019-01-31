package metier;

import model.Joueur;
import dao.DAOFactory;
import dao.JoueurDAO;

public class JoueurMetierImpl {
    JoueurDAO daoJoueur = (JoueurDAO) new DAOFactory().getJoueurDAO();

    public Joueur checkUserLogin( String userName, String password ) {
        Joueur joueur = daoJoueur.findByName( userName );

        if ( joueur != null ) {
            if ( joueur.getPassword().equals( password ) ) {
                return joueur;
            }
        }

        return null;

    }

    public Joueur createUser( Joueur joueur ) {
        return daoJoueur.create( joueur );
    }

    public Joueur findByName( String userName ) {
        return daoJoueur.findByName( userName );
    }

}
