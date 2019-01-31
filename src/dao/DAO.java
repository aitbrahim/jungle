package dao;

import java.sql.Connection;
import java.util.List;

public abstract class DAO<T> {

    public Connection connect = SdzConnection.getInstance();

    public abstract T find( long id );

    public abstract T create( T obj );

    public abstract T update( T obj );

    public abstract void delete( T obj );

    public abstract List<T> findAll();

}
