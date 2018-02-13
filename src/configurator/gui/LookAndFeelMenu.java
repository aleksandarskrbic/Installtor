package configurator.gui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JMenu;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.LookAndFeel;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import configurator.controller.ChangeLookAndFeelAction;

public class LookAndFeelMenu extends JMenu {

	private List<LookAndFeelInfo> infos = new ArrayList<LookAndFeelInfo>();
	private ButtonGroup themeGroup = new ButtonGroup();
	private JRadioButtonMenuItem miTheme = null;

	public LookAndFeelMenu() {
		infos.addAll(Arrays.asList(UIManager.getInstalledLookAndFeels()));
		installExternalLookAndFeels();

		LookAndFeel def = UIManager.getLookAndFeel();
		
		for (LookAndFeelInfo info : infos) {
			if (!info.getName().equals("Nimbus") && !info.getName().equals("CDE/Motif")) {
				miTheme = new JRadioButtonMenuItem(info.getName());
				themeGroup.add(miTheme);
				if(def.getClass().getName().equals(info.getClassName())) {
					miTheme.setSelected(true);
				}
				
				miTheme.addActionListener(new ChangeLookAndFeelAction(info));
				
				add(miTheme);
			}

		}

	}

	private void installExternalLookAndFeels() {
		infos.add(new LookAndFeelInfo("Synthetica BlackEye", "de.javasoft.plaf.synthetica.SyntheticaBlackEyeLookAndFeel"));
		infos.add(new LookAndFeelInfo("Synthetica AluOxide", "de.javasoft.plaf.synthetica.SyntheticaAluOxideLookAndFeel"));
		infos.add(new LookAndFeelInfo("Synthetica BlackMoon", "de.javasoft.plaf.synthetica.SyntheticaBlackMoonLookAndFeel"));
		infos.add(new LookAndFeelInfo("Synthetica BlackStar", "de.javasoft.plaf.synthetica.SyntheticaBlackStarLookAndFeel"));
		infos.add(new LookAndFeelInfo("Synthetica BlueIce", "de.javasoft.plaf.synthetica.SyntheticaBlueIceLookAndFeel"));
		infos.add(new LookAndFeelInfo("Synthetica BlueLight", "de.javasoft.plaf.synthetica.SyntheticaBlueLightLookAndFeel"));
		infos.add(new LookAndFeelInfo("Synthetica Classy", "de.javasoft.plaf.synthetica.SyntheticaClassyLookAndFeel"));
		infos.add(new LookAndFeelInfo("Synthetica Plain", "de.javasoft.plaf.synthetica.SyntheticaPlainLookAndFeel"));
		infos.add(new LookAndFeelInfo("Synthetica SkyMetallic", "de.javasoft.plaf.synthetica.SyntheticaSkyMetallicLookAndFeel"));
		infos.add(new LookAndFeelInfo("Synthetica WhiteVision", "de.javasoft.plaf.synthetica.SyntheticaWhiteVisionLookAndFeel"));
	}

}
