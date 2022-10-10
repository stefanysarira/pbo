import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JOptionPane;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class MainSceneController {

    @FXML
    private Button hitung;

    @FXML
    private RadioButton kendaraan_1;

    @FXML
    private RadioButton kendaraan_2;

    @FXML
    private TextField nomorkendaraan;

    @FXML
    private Button reset;

    @FXML
    private Button simpan;

    @FXML
    private DatePicker tgl_keluar;

    @FXML
    private DatePicker tgl_masuk;

    @FXML
    private TextField total_Tagihan;

    int harga, date1, date2, days, total;

    @FXML
    void Hitung(ActionEvent event) {
        if(kendaraan_1.isSelected()){
            harga = 80000;
        }else if (kendaraan_2.isSelected()){
            harga = 60000;
        } else {
            harga = 0;
        }

        if(nomorkendaraan.getText() == null || harga == 0 || tgl_keluar.getValue() == null || tgl_masuk.getValue() == null){
            JOptionPane.showMessageDialog(null, "ada yang belum diisi");
        } else {
            date1 = tgl_keluar.getValue().getDayOfYear();
            date2 = tgl_masuk.getValue().getDayOfYear();
            days = (int) Math.abs(date1 - date2);
            if(kendaraan_1.isSelected()){
                total = harga * days;
                total_Tagihan.setText("Rp." + String.valueOf(total));
            }
            else if (kendaraan_2.isSelected()){
                total = harga * days;
                total_Tagihan.setText("Rp." + String.valueOf(total));
            }
        }
    }
    
    @FXML
    void Reset(ActionEvent event) {
        harga = 0;
        days = 0;
        total_Tagihan.setText(null);
        tgl_keluar.setValue(null);
        tgl_masuk.setValue(null);
    }

    @FXML
    void Save(ActionEvent event) throws IOException {
        if(nomorkendaraan.getText() == null || harga == 0 || tgl_keluar.getValue() == null || tgl_masuk.getValue() == null){
            JOptionPane.showMessageDialog(null, "ada yang belum diisi");
        } else {
            String namaFile = nomorkendaraan.getText() + ".txt";
            File file = new File(namaFile);
            FileWriter fw = new FileWriter(file);
            PrintWriter pw = new PrintWriter(fw);
    
            pw.println("Nomor Kendaraan\t: " + nomorkendaraan.getText());
            pw.println("Tanggal Masuk\t: " + tgl_masuk.getValue().getYear() + "/" + tgl_masuk.getValue().getMonth() + "/" + tgl_masuk.getValue().getDayOfMonth());
            pw.println("Tanggal Keluar\t: " + tgl_keluar.getValue().getYear() + "/" + tgl_keluar.getValue().getMonth() + "/" + tgl_keluar.getValue().getDayOfMonth());
            pw.println("Total Biaya\t: " + total_Tagihan.getText());
            pw.close();
        }
    }
