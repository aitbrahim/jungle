package vue;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import metier.PartieMetierImpl;
import model.Partie;

public class ModelScoreTable extends AbstractTableModel {

    private List<Partie>     parties;
    private PartieMetierImpl metierPartie = new PartieMetierImpl();
    private final String[]   entetes      = { "Mes Scores", "date partie" };

    public ModelScoreTable( Long idJoueur ) {
        super();
        this.parties = metierPartie.findByJoueur( idJoueur );
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
            return parties.get( rowIndex ).getScorePlayer1();
        case 1:
            return parties.get( rowIndex ).getDatePartie();

        default:
            return null; // Ne devrait jamais arriver
        }
    }

}
