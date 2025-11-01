package SIAPTA;

public class Mahasiswa {
    private String nama;
    private String nim;
    private Dosen pembimbing;

    public Mahasiswa(String nama, String nim) {
        this.nama = nama;
        this.nim = nim;
    }

    public void setPembimbing(Dosen pembimbing) {
        this.pembimbing = pembimbing;
    }

    public String getNama() {
        return nama;
    }

    public String getNim() {
        return nim;
    }

    public Dosen getPembimbing() {
        return pembimbing;
    }
}