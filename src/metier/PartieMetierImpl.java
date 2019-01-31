package metier;

import java.util.List;

import model.Partie;
import dao.DAOFactory;
import dao.PartieDAO;

public class PartieMetierImpl {
    PartieDAO partieDao = (PartieDAO) new DAOFactory().getPartieDAO();

    public Partie createPartie( Partie partie ) {

        return partieDao.create( partie );
    }

    public Partie find( long id ) {

        return partieDao.find( id );
    }

    public List<Partie> findAll() {
        return partieDao.findAll();
    }

    public List<Partie> findByJoueur( Long id ) {
        return partieDao.findByJoueur( id );
    }

}
