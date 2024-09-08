package dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import model.Produk;

@Dao
public interface ProdukDao {

    //mewakili perintah insert,update,delete
    @Insert
    void insert(Produk produk);

    @Update
    void update(Produk produk);

    @Delete
    void delete(Produk produk);

    @Query("SELECT * FROM produk")
    List<Produk> getAll();
}
