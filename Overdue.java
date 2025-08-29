import javax.swing.*;
import java.awt.*;
import java.sql.*;

class Overdue extends JFrame {
JTable tbl; final int finePerDay=5;
Overdue(){
setTitle("Overdue"); setSize(720,320);
tbl=new JTable(); add(new JScrollPane(tbl), BorderLayout.CENTER);
load(); setLocationRelativeTo(null); setVisible(true);
}
void load(){
String sql="SELECT t.id, m.name AS member, b.title AS book, t.issue_dt, t.due_dt, DATEDIFF(CURDATE(), t.due_dt) AS days_late, (GREATEST(DATEDIFF(CURDATE(), t.due_dt),0)*?) AS est_fine FROM txn t JOIN members m ON m.id=t.mem_id JOIN books b ON b.id=t.book_id WHERE t.status='ISSUED' AND t.due_dt < CURDATE() ORDER BY t.due_dt";
try(PreparedStatement ps=DB.get().prepareStatement(sql)){
ps.setInt(1,finePerDay);
try(ResultSet rs=ps.executeQuery()){ tbl.setModel(ViewBooks.build(rs)); }
}catch(Exception ex){ JOptionPane.showMessageDialog(this, ex.getMessage()); }
}
}