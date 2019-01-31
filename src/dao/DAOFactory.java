package dao;

public class DAOFactory {

    public DAO getJoueurDAO() {
        return new JoueurDAOImpl();
    }

    public DAO getPieceDAO() {
        return new PieceDAOImpl();
    }

    public DAO getPartieDAO() {
        return new PartieDAOImpl();
    }
}
