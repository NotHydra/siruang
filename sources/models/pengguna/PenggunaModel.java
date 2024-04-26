package models.pengguna;


import java.security.Timestamp;
import java.util.logging.Level;

import global.base.BaseModel;

public class PenggunaModel extends BaseModel {
    private final String Nama;
    private final String Username;
    private final String Password;
    private final boolean Aktif;
    private final Level LevelEnum;
    private final Timestamp Dibuat;
    private final Timestamp Diubah;

    public PenggunaModel(int id, String Nama, String Username, String Password, boolean Aktif, Level LevelEnum, Timestamp Dibuat, Timestamp Diubah) {
        super(-1);

        validate(Nama, Username, Password, Aktif, LevelEnum);

        this.Nama = Nama;
        this.Password = Password;
        this.Aktif = Aktif;
        this.LevelEnum = LevelEnum; 
    }

    public PenggunaModel(String Nama, String Username, String Password, boolean Aktif, Level LevelEnum) {
        super(id);

        validate(Nama, Username, Password, Aktif, LevelEnum);

        this.Nama = Nama;
        this.Password = Password;
        this.Aktif = Aktif;
        this.LevelEnum = LevelEnum;
    }

    private void validate(String Nama, String Username, String password, boolean aktif, Level LevelEnum) {
        if (Nama == null || Nama.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }

        if (Username == null || Username.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        if (Aktif == null || Aktif.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        if (LevelEnum == null || LevelEnum.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        if (Dibuat == null || Dibuat.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        if (Diubah == null || Diubah.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
    }

    public String getNama() {
        return this.Nama;
    }

    public int getUsername() {
        return this.Username;
    }

    public String getPassword() {
        return this.Password;
    }

    public boolean getAktif() {
        return this.Aktif;
    }

    public boolean getLevel() {
        return this.LevelEnum;
    }

    public boolean getDibuat() {
        return this.Timestamp;

    }

    public boolean getDiubah() {
        return this.Timestamp;
    }
    @Override
    public String toString() {
        return "AuthorModel("
                + "id=" + this.id + ", "
                + "name=" + this.name + ", "
                + "idBook=" + this.idBook
                + ")";
    }
}
