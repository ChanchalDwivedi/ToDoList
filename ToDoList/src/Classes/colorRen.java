package Classes;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class colorRen extends DefaultTableCellRenderer{
		/**
	 * 
	 */
	private static final long serialVersionUID = 3L;

		@Override
		public Component getTableCellRendererComponent(JTable table,Object value,boolean isselected, boolean hasFocus, int row, int column) {
			final Component c = super.getTableCellRendererComponent(table, value, isselected, hasFocus, row, column);
			int st_val=Integer.parseInt(table.getValueAt(row, 3).toString());
			int req_val=1;
			int req_val2=2;
			if(st_val==req_val)
			{
				c.setBackground(Color.green);;
			}
			else if(st_val==req_val2)
			{
				c.setBackground(Color.orange);
			}
			else {
				c.setBackground(Color.red);;
			}
			
			return c;
		}
		
}
