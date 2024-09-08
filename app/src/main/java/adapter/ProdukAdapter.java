package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.belajarroomdb.R;

import java.util.List;

import model.Produk;

public class ProdukAdapter extends BaseAdapter {

    Context context;
    List<Produk> produkList;


    public ProdukAdapter(Context context, List<Produk> produkList) {
        this.context = context;
        this.produkList = produkList;
    }


    @Override
    public int getCount() {
        return produkList.size();
    }


    @Override
    public Object getItem(int position) {
        return produkList.get(position);
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.item,null);
        TextView tvNama = view.findViewById(R.id.tv_nama);
        TextView tvDeskripsi = view.findViewById(R.id.tv_deskripsi);

        tvNama.setText(produkList.get(position).nama);
        tvDeskripsi.setText(produkList.get(position).deskripsi);
        return null;
    }
}
