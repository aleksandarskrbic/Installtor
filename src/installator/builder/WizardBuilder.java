package installator.builder;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

import org.apache.commons.io.FileUtils;

import configurator.model.Wizard;
import installator.gui.InstallatorMainFrame;
import installator.gui.ProjectInfoPanel;
import installator.gui.SelectProjectDialog;
import installator.model.Setup;
import localization.LanguageManager;

public class WizardBuilder implements ActionListener {

	private static WizardBuilder instance;

	private final List<JPanel> panels;

	private final Setup setup;

	public static WizardBuilder getInstance() {
		if (instance == null) {
			instance = new WizardBuilder();
		}
		return instance;
	}

	private WizardBuilder() {
		setup = new Setup(SelectProjectDialog.getProject());
		panels = new ArrayList<>();
		panels.add(new ProjectInfoPanel());
	}

	public void build() {
		for (Wizard w : setup.getActiveProject().getWizards()) {
			final ComponentGenerator loader = new ComponentGenerator(w);
			addPanel(generatePanel(loader.getComponents()));
		}
	}

	private JPanel generatePanel(List<JComponent> components) {
		final JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		for (JComponent c : components) {
			panel.add(c);
		}

		return panel;
	}
	
	public JPanel getPanel(int index) {
		return panels.get(index);
	}

	private void addPanel(JPanel p) {
		panels.add(p);
	}

	// next, back click
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton source = (JButton) e.getSource();

		if (source.getName().equals("nextBtn")) {
			if (setup.getWizardCount() > setup.getCurrentWizard()) {
				setup.setCurrentWizard(setup.getCurrentWizard() + 1);
				InstallatorMainFrame.getInstance().setContent(panels.get(setup.getCurrentWizard()));
			}
		} else if (source.getName().equals("backBtn")) {
			if (setup.getCurrentWizard() - 1 >= 0) {
				setup.setCurrentWizard(setup.getCurrentWizard() - 1);
				InstallatorMainFrame.getInstance().setContent(panels.get(setup.getCurrentWizard()));
			}
		} else if (source.getName().equals("finishBtn")) {
			if (setup.getWizardCount() == setup.getCurrentWizard()) {
				Path from = null;
				Path to = null;

				List<Wizard> wizs = setup.getActiveProject().getWizards();
				for (int i = 0; i < wizs.size(); ++i) {
					if (wizs.get(i).getName().equals("Location")) {
						from = (Path) wizs.get(i).getParameters().get(0).getValue();
						to = (Path) wizs.get(i).getParameters().get(1).getValue();
						try {
							FileUtils.copyDirectory(from.toFile(), to.toFile());
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						
						UIManager.put("OptionPane.yesButtonText", LanguageManager.getInstance().getRes().getString("yes"));
						UIManager.put("OptionPane.noButtonText", LanguageManager.getInstance().getRes().getString("no"));
						
						int code = JOptionPane.showConfirmDialog(null,
								LanguageManager.getInstance().getRes().getString("succ"),
								null, JOptionPane.YES_NO_OPTION);
						
						if (code == JOptionPane.YES_OPTION)
							InstallatorMainFrame.getInstance().dispose();
						
						break;
					}
				}

			} else {
			
			JOptionPane.showMessageDialog(null,
					LanguageManager.getInstance().getRes().getString("cant_finish"),
					LanguageManager.getInstance().getRes().getString("warning"),
			        JOptionPane.WARNING_MESSAGE);
			}
		}
	}
}
