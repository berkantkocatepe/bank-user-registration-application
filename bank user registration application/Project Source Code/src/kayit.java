import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Arrays;

public class kayit  extends JFrame{
    private JTextField adTextField;
    private JPanel panel1;
    private JTextField soyadTextField;
    private JButton kayıtOlButton;
    private JPasswordField kayitPasswordField;
    private JTextField hesapnotextfield;
    private JLabel hesapno;
    private JTextField sifreTextField;

    public kayit(){
        String db_connect_url = "jdbc:sqlite:" + System.getProperty("user.dir") + "\\src\\db\\bankofbk.db";
        add(panel1);
        setSize(500,500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        kayıtOlButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ad = adTextField.getText();
                String soyad = soyadTextField.getText();
                String sifre = Arrays.toString(kayitPasswordField.getPassword());
                String Hesapno = hesapnotextfield.getText();


                try {
                    Connection baglanti = DriverManager.getConnection( db_connect_url );
                    Statement sorgu_nesnesi = baglanti.createStatement();
                    String sorgu ="INSERT INTO Uyeler(uyeAd,uyeSoyad,uyeSifre,HesapNo) VALUES ('"+ad+"','"+soyad+"','"+sifre+"','"+Hesapno+"')";
                    System.out.println(sorgu);
                    sorgu_nesnesi.executeUpdate(sorgu);
                    //executeUpdate Select hariç bütün sorgularda kullanlır
                    //executeQuery sadece select sorgusunu çalıştırır
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }


            }
        });
        kayıtOlButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Yeni formun gösterilmesi için gerekli kodlar
                System.out.println("giriş sayfası giriş");
                giris giris = new giris();
                giris.setVisible(true);
            }
            });
    }
}

