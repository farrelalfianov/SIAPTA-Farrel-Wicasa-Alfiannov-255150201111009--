package SIAPTA;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class SiaptaApp extends JFrame {

    private JTextField namaField, nimField;
    private JTextArea outputArea;
    private Dosen dosen;

    public SiaptaApp() {

        JTextField namaDosenField = new JTextField();
        JTextField nipDosenField = new JTextField();
        Object[] loginForm = {
                "Nama Dosen:", namaDosenField,
                "NIP Dosen:", nipDosenField
        };

        int option = JOptionPane.showConfirmDialog(
                null,
                loginForm,
                "Login Dosen - SIAPTA",
                JOptionPane.OK_CANCEL_OPTION
        );

        if (option == JOptionPane.OK_OPTION) {
            String namaDosen = namaDosenField.getText().trim();
            String nipDosen = nipDosenField.getText().trim();

            if (namaDosen.isEmpty() || nipDosen.isEmpty()) {
                JOptionPane.showMessageDialog(
                        null,
                        "Nama dan NIP dosen tidak boleh kosong!",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
                System.exit(0);
            }

            dosen = new Dosen(namaDosen, nipDosen);
        } else {
            JOptionPane.showMessageDialog(null, "Login dibatalkan. Program ditutup.");
            System.exit(0);
        }


        setTitle("SIAPTA - Sistem Informasi Pembimbingan Tugas Akhir");
        setSize(600, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        setLocationRelativeTo(null);

        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 8, 8));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Tambah Mahasiswa Bimbingan"));

        inputPanel.add(new JLabel("Nama Mahasiswa:"));
        namaField = new JTextField();
        inputPanel.add(namaField);

        inputPanel.add(new JLabel("NIM Mahasiswa:"));
        nimField = new JTextField();
        inputPanel.add(nimField);

        JButton tambahButton = new JButton("Tambah Mahasiswa");
        JButton tampilButton = new JButton("Lihat Daftar Mahasiswa");
        JButton lihatDosenButton = new JButton("Lihat Dosen Pembimbing"); // Fitur baru US-004

        inputPanel.add(tambahButton);
        inputPanel.add(tampilButton);
        inputPanel.add(lihatDosenButton);

        add(inputPanel, BorderLayout.NORTH);

        outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Consolas", Font.PLAIN, 13));
        outputArea.setBorder(BorderFactory.createTitledBorder("Daftar Mahasiswa Bimbingan"));
        add(new JScrollPane(outputArea), BorderLayout.CENTER);


        tambahButton.addActionListener(e -> {
            String nama = namaField.getText().trim();
            String nim = nimField.getText().trim();

            if (nama.isEmpty() || nim.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Nama dan NIM tidak boleh kosong!",
                        "Peringatan",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            Mahasiswa mhs = new Mahasiswa(nama, nim);
            boolean sukses = dosen.tambahMahasiswaBimbingan(mhs);

            if (sukses) {
                JOptionPane.showMessageDialog(this,
                        "Mahasiswa " + nama + " berhasil ditambahkan!",
                        "Sukses",
                        JOptionPane.INFORMATION_MESSAGE);
                namaField.setText("");
                nimField.setText("");
            } else {
                JOptionPane.showMessageDialog(this,
                        "Mahasiswa dengan NIM " + nim + " sudah ada!",
                        "Duplikasi Data",
                        JOptionPane.ERROR_MESSAGE);
            }
        });


        tampilButton.addActionListener(e -> tampilkanDaftarMahasiswa());


        lihatDosenButton.addActionListener(e -> {
            if (dosen != null) {
                JOptionPane.showMessageDialog(this,
                        "Dosen Pembimbing:\n" +
                                "Nama: " + dosen.getNama() + "\n" +
                                "NIP: " + dosen.getNip(),
                        "Informasi Dosen Pembimbing",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this,
                        "Data dosen pembimbing belum tersedia!",
                        "Peringatan",
                        JOptionPane.WARNING_MESSAGE);
            }
        });
    }


    private void tampilkanDaftarMahasiswa() {
        outputArea.setText("");
        outputArea.append("Dosen Pembimbing: " + dosen.getNama() + "\n");
        outputArea.append("NIP: " + dosen.getNip() + "\n");
        outputArea.append("====================================\n");

        if (dosen.getMahasiswaBimbingan().isEmpty()) {
            outputArea.append("Belum ada mahasiswa bimbingan.\n");
        } else {
            for (Mahasiswa m : dosen.getMahasiswaBimbingan()) {
                outputArea.append("- " + m.getNama() + " (" + m.getNim() + ")\n");
            }
        }
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SiaptaApp().setVisible(true));
    }
}