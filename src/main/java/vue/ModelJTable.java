package vue;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import metier.PartieMetierImpl;
import model.Partie;

public class ModelJTable extends AbstractTableModel {
    private List<Partie>     parties      = new ArrayList<Partie>();

    private PartieMetierImpl metierPartie = new PartieMetierImpl();

    private final String[]   entetes      = { "id", "datePartie", "joueur", "completed", "scorePlayer1",
                                          "scorePlayer2" };

    public ModelJTable( Long idJoueur ) {
        super();
        parties = metierPartie.findByJoueur( idJoueur );
    }

    public String getColumnName( int columnIndex ) {
        return entetes[columnIndex];
    }

    public int getColumnCount() {
        return entetes.length;
    }

    public int getRowCount() {
        return parties.size();
    }

    public Object getValueAt( int rowIndex, int columnIndex ) {
        switch ( columnIndex ) {
        case 0:
            return parties.get( rowIndex ).getId();
        case 1:
            return parties.get( rowIndex ).getDatePartie();
        case 2:
            return parties.get( rowIndex ).getPlayer1().getPseudo();
        case 3:
            return parties.get( rowIndex ).isFinPartie();
        case 4:
            return parties.get( rowIndex ).getScorePlayer1();
        case 5:
            return parties.get( rowIndex ).getScorePlayer2();

        default:
            return null; // Ne devrait jamais arriver
        }
    }
}
