package models.pengguna;


import java.util.logging.Level;
import java.sql.Time;
import java.sql.Timestamp;
import global.base.BaseModel;

public class PenggunaModel extends BaseModel {
    private final String camelCase;
    private final String Username;
    private final String Password;
    private final boolean Aktif;
    private final Level LevelEnum;
    private final Timestamp TanggalDibuat;
    private final Timestamp TanggalDiubah;

    public PenggunaModel(int id, String Nama, String Username, String Password, boolean Aktif, Level LevelEnum, Timestamp Dibuat, Timestamp Diubah) {
        super(-1);

        validate(camelCase, Username, Password, Aktif, LevelEnum);

        this.camelCase = camelCase;
        this.Password = Password;
        this.Aktif = Aktif;
        this.LevelEnum = LevelEnum;
    }

    public PenggunaModel(String CamelCase, String Username, String Password, boolean Aktif, Level LevelEnum) {
        super(id);

        validate(camelCase, Username, Password, Aktif, LevelEnum);

        this.camelCase = camelCase;
        this.Password = Password;
        this.Aktif = Aktif;
        this.LevelEnum = LevelEnum;
    }

    private void validate(String camelCase, String Username, String password) {
        if (camelCase == null || camelCase.trim().isEmpty()) {
            throw new IllegalArgumentException("Nama tidak boleh kosong");
        }

        if (Username == null || Username.trim().isEmpty()) {
            throw new IllegalArgumentException("Username tidak boleh kosong");
        }

        if (Password == null || Password.trim().isEmpty()) {
            throw new IllegalArgumentException("Password tidak boleh kosong");
        }
    }

    public String getcamelCase() {
        return this.camelCase;
    }

    public Timestamp getTanggaldibuat() {
        return this.TanggalDibuat;
    }

    public Timestamp getTanggaldiubah() {
        return this.TanggalDiubah;
    }

    public int getUsername() {
        return this.Username;
    }

    public String getPassword() {
        return this.Password;

    }

    @Override
    public String toString() {
        return "PenggunaModel("
                + "id=" + id() +
                ", camelCase='" + camelCase + '\'' +
                ", Username='" + Username + '\'' +
                ", Password='" + Password + '\'' +
                '}';
    }
}
