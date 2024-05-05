package models.pengguna;


import java.sql.Timestamp;

import enums.LevelEnum;

import global.base.BaseModel;

public class PenggunaModel extends BaseModel {
    private final String nama;
    private final String username;
    private final String password;
    private final boolean aktif;
    private final LevelEnum level;
    private final Timestamp dibuat;
    private final Timestamp diubah;

    public PenggunaModel(String nama, String username, String password, boolean aktif, LevelEnum level) {
        super(-1);

        if (nama == null || nama.trim().isEmpty()) {
            throw new IllegalArgumentException("Nama Pengguna tidak boleh kosong");
        }

        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Username tidak boleh kosong");
        }

        if (password == null || password.trim().isEmpty()) {
            throw new IllegalArgumentException("Password tidak boleh kosong");
        }

        this.nama = nama;
        this.username = username;
        this.password = password;
        this.aktif = aktif;
        this.level = level;
        this.dibuat = null;
        this.diubah = null;
    }

    public PenggunaModel(int id, String nama, String username, String password, boolean aktif, LevelEnum level, Timestamp dibuat, Timestamp diubah) {
        super(id);

        this.nama = nama;
        this.username = username;
        this.password = password;
        this.aktif = aktif;
        this.level = level;
        this.dibuat = dibuat;
        this.diubah = diubah;
    }

    public PenggunaModel(String nama, String username, boolean aktif, LevelEnum level) {
        super(-1);

        if (nama == null || nama.trim().isEmpty()) {
            throw new IllegalArgumentException("Nama Pengguna tidak boleh kosong");
        }

        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Username tidak boleh kosong");
        }

        this.nama = nama;
        this.username = username;
        this.password = null;
        this.aktif = aktif;
        this.level = level;
        this.dibuat = null;
        this.diubah = null;
    }

    public PenggunaModel(int id, String nama, String username, boolean aktif, LevelEnum level, Timestamp dibuat, Timestamp diubah) {
        super(id);

        this.nama = nama;
        this.username = username;
        this.password = null;
        this.aktif = aktif;
        this.level = level;
        this.dibuat = dibuat;
        this.diubah = diubah;
    }

    public String getNama() {
        return this.nama;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public boolean getAktif() {
        return this.aktif;
    }

    public LevelEnum getLevel() {
        return this.level;
    }

    public Timestamp getDibuat() {
        return this.dibuat;
    }

    public Timestamp getDiubah() {
        return this.diubah;
    }

    @Override
    public String toString() {
        return "PenggunaModel("
                + "id=" + this.id + ", "
                + "nama=" + this.nama + ", "
                + "username=" + this.username + ", "
                + "password=" + this.password + ", "
                + "aktif=" + this.aktif + ", "
                + "level=" + this.level + ", "
                + "dibuat=" + this.dibuat + ", "
                + "diubah=" + this.diubah
                + ")";
    }
}
