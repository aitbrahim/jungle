package dao;

import java.util.List;

import model.Partie;

public abstract class PartieDAO extends DAO<Partie> {
    public abstract List<Partie> findByJoueur( Long id );
}
