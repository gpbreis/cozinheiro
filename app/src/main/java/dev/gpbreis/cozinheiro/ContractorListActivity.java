package dev.gpbreis.cozinheiro;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ContractorListActivity extends AppCompatActivity {

    private ListView contractorListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contractor_list);

        populateContractorListView();
    }

    private void populateContractorListView() {

        String[] names = getResources().getStringArray(R.array.names);
        String[] sex = getResources().getStringArray(R.array.sex);
        String[] phones = getResources().getStringArray(R.array.phones);
        String[] cellphones = getResources().getStringArray(R.array.cellphones);
        String[] emails = getResources().getStringArray(R.array.emails);
        int[] priority = getResources().getIntArray(R.array.priority);
        int[] contactPreference = getResources().getIntArray(R.array.contactPreferences);

        ArrayList<Contractor> contractors = new ArrayList<>();

        for (int i = 0; i < names.length; i++) {
            contractors.add(new Contractor(names[i], sex[i], phones[i], cellphones[i], emails[i], contactPreference[i]));
        }

        ArrayAdapter<Contractor> adapter = new ArrayAdapter<>(this, )

    }
}