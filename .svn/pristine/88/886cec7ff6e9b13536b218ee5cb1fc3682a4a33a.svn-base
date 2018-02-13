package configurator.gui.model;

import javax.swing.table.AbstractTableModel;

public class NonEditableTableModel extends AbstractTableModel{

	private Object[][] data;
	private final String[] columnNames = {"Action","KeyBinding"};
	
	
	public NonEditableTableModel(Object[][] data) {
		this.data = data;
	}
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return data[0].length;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return data.length;
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return data[arg0][arg1];
	}
	@Override
	public boolean isCellEditable(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public String getColumnName(int arg0) {
		// TODO Auto-generated method stub
		return columnNames[arg0];
	}
	
}
