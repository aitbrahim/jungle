package vue;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import model.Piece;

public class MButton extends JButton {

    private int   posX;
    private int   posY;
    private Piece piece;

    public MButton( int posX, int posY ) {
        super();
        this.posX = posX;
        this.posY = posY;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece( Piece piece ) {
        this.piece = piece;
        if ( piece != null ) {
            setIcon( new ImageIcon( "img/p" + piece.getPlayer() + "/" + piece.getAnimal().getImg() ) );
        }
        else
            setIcon( null );
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX( int posX ) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY( int posY ) {
        this.posY = posY;
    }

}
