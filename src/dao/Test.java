package dao;

import model.Partie;

public class Test {

    public static void main( String[] args ) {
        // List<Piece> pieces = new ArrayList<Piece>();
        // for ( int i = 0; i < 5; i++ ) {
        // pieces.add( (Piece) new DAOFactory().getPieceDAO().create(
        // new PieceChat( new Point( 1, 1 ), 2, new Echiquier( new Partie() ) )
        // ) );
        // }
        //
        // Joueur player1 = (Joueur) new DAOFactory().getJoueurDAO().find( 1 );
        // new DAOFactory().getPartieDAO().create( new Partie( player1, 10, 10,
        // new java.sql.Date( 0 ), 1, true, pieces ) );

        Partie p = (Partie) new DAOFactory().getPartieDAO().find( 5 );
        System.out.println( p.getId() );
    }

}
