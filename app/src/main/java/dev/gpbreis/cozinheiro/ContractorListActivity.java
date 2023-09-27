package dev.gpbreis.cozinheiro;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ActionMode;

import java.util.ArrayList;

import dev.gpbreis.cozinheiro.model.Contractor;
import dev.gpbreis.cozinheiro.persistance.ContractorDatabase;
import dev.gpbreis.cozinheiro.utils.UtilsGUI;

public class ContractorListActivity extends AppCompatActivity {
    private static final String SHAREDPREFERENCES = "dev.gpbreis.cozinheiro.sharedpreferences.NIGHTMODE";
    private static final String NIGHTMODE = "NIGHTMODE";
    private Boolean nightMode = false;

    private static final int NEW_CONTRACTOR_REQUEST = 1;
    private static final int CHANGE_CONTRACTOR_REQUEST = 2;

    private ListView contractorListView;
    private ArrayList<Contractor> contractors;
    private ContractorAdapter contractorAdapter;

    private ActionMode actionMode;
    private int selectedPosition = -1;
    private View selectedView;

    private ActionMode.Callback mActionModeCallBack = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {

            MenuInflater inflater = mode.getMenuInflater();
            inflater.inflate(R.menu.contractor_list_selected_options, menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {

            Contractor contractor = (Contractor) contractorListView.getItemAtPosition(selectedPosition);

            switch (item.getItemId()) {
                case R.id.menuItemContractorListEdit:
                    changeContractor();
                    mode.finish();
                    return true;
                case R.id.menuItemContractorListDelete:
                    deleteContractor(contractor);
                    mode.finish();
                    return true;
                default:
                    return false;
            }
        }

        @Override
        public void onDestroyActionMode(androidx.appcompat.view.ActionMode mode) {

            if (selectedView != null) {
                selectedView.setBackgroundColor(Color.TRANSPARENT);
            }

            actionMode = null;
            selectedView = null;

            contractorListView.setEnabled(true);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contractor_list);

        contractorListView = findViewById(R.id.contractorListView);

        contractorListView.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                        if (actionMode != null) {
                            return false;
                        }

                        selectedPosition = position;
                        view.setBackgroundColor(Color.LTGRAY);
                        selectedView = view;
                        contractorListView.setEnabled(false);
                        actionMode = startSupportActionMode(mActionModeCallBack);

                        return true;
                    }
                }
        );

        contractorListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        selectedPosition = position;
                        Contractor contractor = (Contractor) contractorListView.getItemAtPosition(selectedPosition);

                        changeContractor();
//                        Toast.makeText(getApplicationContext(), "O contratante " + contractor.getId() + "\n" + contractor.getName() + " foi selecionado!", Toast.LENGTH_SHORT).show();
                    }
                }
        );

        readNightMode();
        populateContractorListView();
        registerForContextMenu(contractorListView);
    }

    private void populateContractorListView() {

        ContractorDatabase contractorDatabase = ContractorDatabase.getDatabase(this);

        contractors = new ArrayList<>(contractorDatabase.contractorDao().queryAll());

        contractorAdapter = new ContractorAdapter(this, contractors);

        contractorListView.setAdapter(contractorAdapter);
    }

//    public void openAbout(View view) {
//        AboutActivity.about(this);
//    }
//
//    public void addContractor(View view) {
//        ContractorActivity.newContractor(this);
//    }

    private void deleteContractor(final Contractor contractor) {

        String message = "Confirmar a exclusão do contratante?" + "\n" + contractor.getName();

        DialogInterface.OnClickListener listener =
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case DialogInterface.BUTTON_POSITIVE:
                                AsyncTask.execute(new Runnable() {
                                    @Override
                                    public void run() {
                                        ContractorDatabase contractorDatabase = ContractorDatabase.getDatabase(ContractorListActivity.this);
                                        contractorDatabase.contractorDao().delete(contractor);

                                        ContractorListActivity.this.runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                contractors.remove(contractor);
                                                contractorAdapter.notifyDataSetChanged();
                                            }
                                        });
                                    }
                                });
                            break;

                            case DialogInterface.BUTTON_NEGATIVE:
                                break;
                        }
                    }
                };

        UtilsGUI.confirmation(this, message, listener);
    }

    private void changeContractor() {
        Contractor contractor = contractors.get(selectedPosition);
        ContractorActivity.changeContractor(this, contractor);
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

            Contractor contractor = new Contractor(name, sex, phone, cellphone, email, priority, contactPreference);

            contractors.add(contractor);

            contractorAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.contractor_list_options, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuItemContractorListAdd:
                ContractorActivity.newContractor(this);
                return true;
            case R.id.menuItemContractorListAbout:
                AboutActivity.about(this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, view, menuInfo);

        getMenuInflater().inflate(R.menu.contractor_list_selected_options, menu);
    }

    private void readNightMode() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHAREDPREFERENCES, Context.MODE_PRIVATE);
        nightMode = sharedPreferences.getBoolean(NIGHTMODE, nightMode);
    }


    public boolean onContextItemSelected(MenuItem menuItem) {
        AdapterView.AdapterContextMenuInfo info;
        info = (AdapterView.AdapterContextMenuInfo) menuItem.getMenuInfo();

        Contractor contractor = (Contractor) contractorListView.getItemAtPosition(info.position);

        switch (menuItem.getItemId()) {
            case R.id.menuItemContractorListEdit:
                ContractorActivity.changeContractor(this, contractor);
                return true;
            case R.id.menuItemContractorListDelete:
                deleteContractor(contractor);
                return true;
            default:
                return super.onContextItemSelected(menuItem);
        }
    }
}