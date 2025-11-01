package SIAPTA;
import java.util.ArrayList;

public class Dosen {
    private String nama;
    private String nip;
    private ArrayList<Mahasiswa> mahasiswaBimbingan;

    public Dosen(String nama, String nip) {
        this.nama = nama;
        this.nip = nip;
        this.mahasiswaBimbingan = new ArrayList<>();
    }

    public boolean tambahMahasiswaBimbingan(Mahasiswa mhs) {
        for (Mahasiswa existing : mahasiswaBimbingan) {
            if (existing.getNim().equalsIgnoreCase(mhs.getNim())) {
                return false;
            }
        }
        mahasiswaBimbingan.add(mhs);
        return true;
    }

    public ArrayList<Mahasiswa> getMahasiswaBimbingan() {
        return mahasiswaBimbingan;
    }

    public String getNama() {
        return nama;
    }

    public String getNip() {
        return nip;
    }
}