package dev.gpbreis.cozinheiro;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ContractorListActivity extends AppCompatActivity {

    private ListView contractorListView;
    private ArrayList<Contractor> contractors;
    private ContractorAdapter contractorAdapter;

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

        contractors = new ArrayList<>();

        contractorAdapter = new ContractorAdapter(this, contractors);

        contractorListView.setAdapter(contractorAdapter);
    }

    public void openAbout(View view) {
        AboutActivity.about(this);
    }

    public void addContractor(View view) {
        ContractorActivity.newContractor(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            Bundle bundle = data.getExtras();

            String name = bundle.getString(ContractorActivity.NAME);
            String sex = bundle.getString(ContractorActivity.SEX);
            String phone = bundle.getString(ContractorActivity.PHONE);
            String cellphone = bundle.getString(ContractorActivity.CELLPHONE);
            String email = bundle.getString(ContractorActivity.EMAIL);
            boolean priority = bundle.getBoolean(ContractorActivity.PRIORITY);
            int contactPreference = bundle.getInt(ContractorActivity.CONTACTPREFERECE);

            Contractor contractor = new Contractor(name, sex, phone, cellphone, email, contactPreference);

            contractors.add(contractor);

            contractorAdapter.notifyDataSetChanged();
        }
    }
}