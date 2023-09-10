package dev.gpbreis.cozinheiro;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class ContractorAdapter extends BaseAdapter {

    Context context;
    List<Contractor> contractors;

    private static class ContractorHolder {
        public TextView textViewNome;
    }

    public ContractorAdapter(Context context, List<Contractor> contractors) {
        this.context = context;
        this.contractors = contractors;
    }

    @Override
    public int getCount() {
        return contractors.size();
    }

    @Override
    public Object getItem(int position) {
        return contractors.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ContractorHolder contractorHolder;

        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout)
        }
    }
}
