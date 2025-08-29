import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.time.*;

class ReturnBook extends JFrame {
JTextField tfTxn; JButton b;
final int finePerDay = 5;
ReturnBook(){
setTitle("Return Book"); setSize(320,140); setLayout(new GridLayout(2,2,8,8));
add(new JLabel("Txn ID")); tfTxn=new JTextField(); add(tfTxn);
b=new JButton("Return"); b.addActionListener(e->ret()); add(new JLabel()); add(b);
setLocationRelativeTo(null); setVisible(true);
}
void ret(){
try{
int id=Integer.parseInt(tfTxn.getText().trim());
Connection c=DB.get();
LocalDate due=null; int bid=0;
try(PreparedStatement ps=c.prepareStatement("SELECT book_id,due_dt FROM txn WHERE id=? AND status='ISSUED'")){
ps.setInt(1,id); try(ResultSet rs=ps.executeQuery()){
if(rs.next()) { bid=rs.getInt(1); due=Util.toLd(rs.getDate(2)); }
else { JOptionPane.showMessageDialog(this,"Invalid or already returned"); return; }
}
}
LocalDate now=LocalDate.now(); int late=Util.daysLate(due, now); int fine=late*finePerDay;
try(PreparedStatement ps=c.prepareStatement("UPDATE txn SET ret_dt=?, fine=?, status='RETURNED' WHERE id=?")){
ps.setDate(1, Util.toSql(now)); ps.setInt(2,fine); ps.setInt(3,id); ps.executeUpdate();
}
try(PreparedStatement ps=c.prepareStatement("UPDATE books SET qty=qty+1 WHERE id=?")){
ps.setInt(1,bid); ps.executeUpdate();
}
JOptionPane.showMessageDialog(this, late>0? ("Returned. Fine: "+fine):"Returned. No fine");
}catch(Exception ex){ JOptionPane.showMessageDialog(this, ex.getMessage()); }
}
}
