package models.pengguna;
import java.util.logging.Level;
import java.sql.Time;
import java.sql.Timestamp;
import global.base.BaseModel;

public class PenggunaModel extends BaseModel {
    private final String namaPengguna;
    private final String username;
    private final String password;
    private final boolean aktif;
    private final Level levelEnum;
    private final Timestamp tanggalDibuat;
    private final Timestamp tanggalDiubah;

    public PenggunaModel(int id, String namaPengguna, String username, String password, boolean aktif, Level levelEnum, Timestamp tanggalDibuat, Timestamp tanggalDiubah) {
        super(-1);

        validate(namaPengguna, username, password, aktif, levelEnum);

        this.namaPengguna = namaPengguna;
        this.password = password;
        this.aktif = aktif;
        this.levelEnum = levelEnum; 
    }

    public PenggunaModel(String namaPengguna, String username, String password) {
        super(id);

        validate(namaPengguna, username, password, aktif, levelEnum);

        this.namaPengguna = namaPengguna;
        this.password = password;
    }

    private void validate(String namaPengguna, String username, String password) {
        if (namaPengguna == null || namaPengguna.trim().isEmpty()) {
            throw new IllegalArgumentException("Nama Pengguna tidak boleh kosong!");
        }

        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Username tidak boleh kosong!");
        }

        if (password == null || password.trim().isEmpty()) {
            throw new IllegalArgumentException("Password tidak boleh kosong");
        }
    }

    public String namaPengguna() {
        return this.namaPengguna;
    }

    public Timestamp gettanggaldibuat() {
        return this.tanggalDibuat;
    }

    public Timestamp gettanggaldiubah() {
        return this.tanggalDiubah;
    }

    public int getusername() {
        return this.username;
    }

    public String getpassword() {
        return this.password;
    
    }
    @Override
    public String toString() {
        return "PenggunaModel("
                + "id=" + this.id + ", "
                + "namaPengguna=" + this.namaPengguna + ", "
                + "password=" + this.password
                + ")";
    }
}
