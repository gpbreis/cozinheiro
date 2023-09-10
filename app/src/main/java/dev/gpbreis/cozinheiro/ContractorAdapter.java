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
        public TextView textViewName;
        public TextView textViewSex;
        public TextView textViewCellphone;
        public TextView textViewEmail;
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
            view = layoutInflater.inflate(R.layout.contractor_list_line, viewGroup, false);

            contractorHolder = new ContractorHolder();

            contractorHolder.textViewName = view.findViewById(R.id.textViewContractorNameListContent);
            contractorHolder.textViewSex = view.findViewById(R.id.textViewContractorSexListContent);
            contractorHolder.textViewCellphone = view.findViewById(R.id.textViewContractorCellphoneListContent);
            contractorHolder.textViewEmail = view.findViewById(R.id.textViewContractorEmailListContent);

            view.setTag(contractorHolder);
        } else {
            contractorHolder = (ContractorHolder) view.getTag();
        }

        contractorHolder.textViewName.setText(contractors.get(position).getName());
        contractorHolder.textViewSex.setText(contractors.get(position).getSex());
        contractorHolder.textViewCellphone.setText(contractors.get(position).getCellphone());
        contractorHolder.textViewEmail.setText(contractors.get(position).getEmail());

        return view;
    }
}
