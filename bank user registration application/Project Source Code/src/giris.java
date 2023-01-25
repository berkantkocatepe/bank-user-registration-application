import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Arrays;

public class giris extends JFrame{
    private JTextField girisAdTextField;
    private JPanel panel1;
    private JButton girisYapButton;
    private JPasswordField girisPasswordField1;
    private JButton kayitButton;

    public giris(){
        String db_connect_url = "jdbc:sqlite:" + System.getProperty("user.dir") + "\\src\\db\\bankofbk.db";
        add(panel1);
        setSize(500,500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        girisYapButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ad = girisAdTextField.getText();
                String sifre = Arrays.toString(girisPasswordField1.getPassword());

                Connection baglanti = null;
                try {
                    baglanti = DriverManager.getConnection( db_connect_url );
                    Statement sorgu_nesnesi = baglanti.createStatement();
                    ResultSet sonuc = sorgu_nesnesi.executeQuery("SELECT * FROM Uyeler");
                    //executeQuery ile select sorgusu çalıştırıyoruz
                    //sonuc.getString("uyeAd") burada uyeAd bilgisini çağırıyoruz.
                while (sonuc.next()){
                    if (sonuc.getString("uyeAd").equals(ad) && sonuc.getString("uyeSifre").equals(sifre))
                    {
                        //anasayfa giriş kodları
                        System.out.println("anasayfa giriş");
                        anasayfa anasayfa = new anasayfa();
                        anasayfa.setVisible(true);
                        //burada giris sayfasını görünmez yap setVisible(false)

                    }else
                    {
                        System.out.println("giriş yapılamadı");
                    }
                }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });


        kayitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Yeni formun gösterilmesi için gerekli kodlar
                System.out.println("kayıt ekranına giriş");
                kayit kayit = new kayit();
                kayit.setVisible(true);
            }
        });

    }
}
