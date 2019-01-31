package vue;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MenuBar extends JMenuBar {

    private JMenu     partieMenu;
    private JMenu     scoreMenu;

    private JMenuItem nouveauPartie;
    private JMenuItem chargerPartie;
    private JMenuItem savePartie;
    private JMenuItem quitter;

    private JMenuItem mesScores;
    private JMenuItem meilleurScore;

    public MenuBar() {

        partieMenu = new JMenu( "Partie" );
        scoreMenu = new JMenu( "Scores" );

        nouveauPartie = new JMenuItem( "Nouvelle Partie" );
        chargerPartie = new JMenuItem( "Charger Partie" );
        savePartie = new JMenuItem( "Sauvegader Partie" );
        quitter = new JMenuItem( "Fermmer" );

        mesScores = new JMenuItem( "Mes scores" );
        meilleurScore = new JMenuItem( "Meilleur score" );

        partieMenu.add( nouveauPartie );
        partieMenu.add( chargerPartie );
        partieMenu.add( savePartie );
        partieMenu.add( quitter );

        scoreMenu.add( mesScores );
        scoreMenu.add( meilleurScore );

        add( partieMenu );
        add( scoreMenu );
    }

    public JMenuItem getNouveauPartie() {
        return nouveauPartie;
    }

    public JMenuItem getChargerPartie() {
        return chargerPartie;
    }

    public JMenuItem getSavePartie() {
        return savePartie;
    }

    public JMenuItem getQuitter() {
        return quitter;
    }

    public JMenuItem getMesScores() {
        return mesScores;
    }

    public JMenuItem getMeilleurScore() {
        return meilleurScore;
    }

}
