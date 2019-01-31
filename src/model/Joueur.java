package model;

public class Joueur {

    private Long   id = new Long( 0 );
    private String pseudo;
    private String password;

    public Joueur( String pseudo, String password ) {
        this.pseudo = pseudo;
        this.password = password;
    }

    public Joueur( Long id, String pseudo, String password ) {
        this.id = id;
        this.pseudo = pseudo;
        this.password = password;
    }

    public Joueur() {
    }

    public Long getId() {
        return id;
    }

    public void setId( Long id ) {
        this.id = id;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo( String pseudo ) {
        this.pseudo = pseudo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword( String password ) {
        this.password = password;
    }

}
