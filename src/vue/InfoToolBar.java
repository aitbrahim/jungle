package vue;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

public class InfoToolBar extends JToolBar{

	private JLabel labelScoreP1;
	private JLabel labelScoreP1Value;
	private JLabel labelScoreP2;
	private JLabel labelScoreP2Value;
	private JLabel labelPseudo;
	private JLabel labelDeconnexion;

	public InfoToolBar() {

		labelPseudo = new JLabel() ;		
		labelScoreP1 = new JLabel("Score "+labelPseudo.getText()+": ") ;
		labelScoreP1Value = new JLabel("0") ;

		labelScoreP2 = new JLabel("Score player 2:  ") ;
		labelScoreP2Value = new JLabel("0") ;
		
		labelDeconnexion = new JLabel(" (Deconnexion)") ;
		
		addSeparator(new Dimension(100, 0));		
		add(labelScoreP1) ;
		add(labelScoreP1Value) ;
		addSeparator(new Dimension(100, 0));
		add(labelScoreP2) ;
		add(labelScoreP2Value) ;
		addSeparator(new Dimension(230, 0));		
		add(labelPseudo) ;
		add(labelDeconnexion) ;		
		
		setUpLook("Nimbus");
	}
	
	 public JLabel getLabelPseudo() {
		return labelPseudo;
	}

	public void setLabelPseudo(JLabel labelPseudo) {
		this.labelPseudo = labelPseudo;
	}

	public JLabel getLabelScoreP1Value() {
		return labelScoreP1Value;
	}

	public void setLabelScoreP1Value(JLabel labelScoreP1Value) {
		this.labelScoreP1Value = labelScoreP1Value;
	}

	public JLabel getLabelScoreP2Value() {
		return labelScoreP2Value;
	}

	public void setLabelScoreP2Value(JLabel labelScoreP2Value) {
		this.labelScoreP2Value = labelScoreP2Value;
	}

	public JLabel getLabelScoreP1() {
		return labelScoreP1;
	}
	
	private void setUpLook(String look) {
		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if (look.equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					SwingUtilities.updateComponentTreeUI(this);
					break;
				}
			}
		} catch (Exception e) {
			System.err.println("Look & Feel intouvable");
		}
	}

	
}
