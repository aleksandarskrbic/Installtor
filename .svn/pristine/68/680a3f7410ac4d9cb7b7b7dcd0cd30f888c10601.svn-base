package configurator.gui;

import java.awt.Component;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;

public class TreeCellRenderer extends DefaultTreeCellRenderer {
	
	private static final long serialVersionUID = -3410098970055352090L;

	public static enum RenderType { SEARCH, EDIT };
	
	private static final String SEARCH_HIGHLIGHT_FORMAT = "<font color=red>%s</font>";
	
	private static final String EDITED_HIGHLIGHT_FORMAT = "<font color=blue>%s</font>";
	
	private static final String HTML_FORMAT = "<html>%s</html>";
	
	private String regex;
	
	private RenderType type;
	
	public TreeCellRenderer(RenderType type) {
		super();
		this.type = type;
	}

	@Override
	public Component getTreeCellRendererComponent(JTree tree, Object o, boolean sel, boolean expanded, boolean leaf, int row,
			boolean hasFocus) {
		if (regex == null)
			return super.getTreeCellRendererComponent(tree, o, sel, expanded, leaf, row, hasFocus);

		String value = o.toString();
		
		Iterator<String> nonMatchedIt = Arrays.asList(value.split(regex)).iterator();
		Iterator<String> matchedIt = getAll(value, regex).iterator();
		StringBuilder sb = new StringBuilder();
		
		// nonMatched + matched + nonMatched + matched + ..... + nonMatched
		while(nonMatchedIt.hasNext() || matchedIt.hasNext()) {
			if(nonMatchedIt.hasNext())
				sb.append(nonMatchedIt.next());
			if(matchedIt.hasNext())
				if(type == RenderType.SEARCH)
					sb.append(String.format(SEARCH_HIGHLIGHT_FORMAT, matchedIt.next()));
				else if(type == RenderType.EDIT)
					sb.append(String.format(EDITED_HIGHLIGHT_FORMAT, matchedIt.next()));
		}
		
		return super.getTreeCellRendererComponent(tree, String.format(HTML_FORMAT, sb), sel, expanded, leaf, row, hasFocus);
	}

	/**
	 * @return Vraca sve substringove iz stringa
	 * koji odgovaraju regex-u
	 */
	private List<String> getAll(String string, String regex) {
		List<String> matched = new ArrayList<String>();
		
		Matcher m = Pattern.compile(regex).matcher(string);
		while(m.find())
			matched.add(m.group());
		
		return matched;
	}
	
	public void highlight(String regex) {
		this.regex = regex;
	}

	public void clearHighlights() {
		regex = null;
	}

}
