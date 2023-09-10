package dev.gpbreis.cozinheiro;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ContractorListActivity extends AppCompatActivity {

    private ListView contractorListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contractor_list);

        contractorListView = findViewById(R.id.contractorListView);

        contractorListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Contractor contractor = (Contractor) contractorListView.getItemAtPosition(position);

                        Toast.makeText(getApplicationContext(), "O contratante " + contractor.getName() + " foi selecionado!", Toast.LENGTH_SHORT).show();
                    }
                }
        );

        populateContractorListView();
    }

    private void populateContractorListView() {

        String[] names = getResources().getStringArray(R.array.names);
        String[] sex = getResources().getStringArray(R.array.sexo);
        String[] phones = getResources().getStringArray(R.array.phones);
        String[] cellphones = getResources().getStringArray(R.array.cellphones);
        String[] emails = getResources().getStringArray(R.array.emails);
        int[] priority = getResources().getIntArray(R.array.priority);
        int[] contactPreference = getResources().getIntArray(R.array.contactPreferences);

        ArrayList<Contractor> contractors = new ArrayList<>();

        for (int i = 0; i < names.length; i++) {
            contractors.add(new Contractor(names[i], sex[i], phones[i], cellphones[i], emails[i], contactPreference[i]));
        }

        ContractorAdapter contractorAdapter = new ContractorAdapter(this, contractors);

        contractorListView.setAdapter(contractorAdapter);
    }
}