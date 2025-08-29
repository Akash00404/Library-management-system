import javax.swing.*;
import java.awt.*;
import java.sql.*;

class ViewBooks extends JFrame {
JTable tbl;
ViewBooks(){
setTitle("Books"); setSize(600,300);
tbl=new JTable(); add(new JScrollPane(tbl), BorderLayout.CENTER);
load(); setLocationRelativeTo(null); setVisible(true);
}
void load(){
try(Statement st=DB.get().createStatement(); ResultSet rs=st.executeQuery("SELECT id,title,author,qty FROM books ORDER BY id DESC")){
tbl.setModel(build(rs));
}catch(Exception ex){ JOptionPane.showMessageDialog(this, ex.getMessage()); }
}
static javax.swing.table.TableModel build(ResultSet rs) throws Exception{
java.sql.ResultSetMetaData m=rs.getMetaData(); int c=m.getColumnCount();
java.util.Vector<String> cols=new java.util.Vector<>();
for(int i=1;i<=c;i++) cols.add(m.getColumnName(i));
java.util.Vector<java.util.Vector<Object>> rows=new java.util.Vector<>();
while(rs.next()){
java.util.Vector<Object> row=new java.util.Vector<>();
for(int i=1;i<=c;i++) row.add(rs.getObject(i));
rows.add(row);
}
return new javax.swing.table.DefaultTableModel(rows, cols);
}
}
