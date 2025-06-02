package dao;

import model.Joueur;

public abstract class JoueurDAO extends DAO<Joueur> {
    public abstract Joueur findByName( String name );
}
