package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SdzConnection {

    // Remplacez « db » par le nom du service MySQL dans docker-compose (ici "db")
    // et ajoutez les paramètres recommandés :
    //   - useSSL=false        : désactiver SSL si vous n’en avez pas besoin
    //   - allowPublicKeyRetrieval=true : utile pour conteneurs Docker
    //   - serverTimezone=UTC  : éviter les avertissements de fuseau horaire
    private static final String URL    = "jdbc:mysql://db:3306/testdb"
                                       + "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USER   = "appuser";
    private static final String PASSWD = "apppass";

    private static Connection connect;

    public static Connection getInstance() {
        if (connect == null) {
            try {
                // (Optionnel) Charger explicitement le nouveau driver :
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                System.err.println("Driver MySQL non trouvé : " + e.getMessage());
            }

            try {
                connect = DriverManager.getConnection(URL, USER, PASSWD);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException("Impossible d'établir la connexion JDBC", e);
            }
        }
        return connect;
    }
}
