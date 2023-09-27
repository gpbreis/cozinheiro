package dev.gpbreis.cozinheiro;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import dev.gpbreis.cozinheiro.model.Contractor;
import dev.gpbreis.cozinheiro.persistance.ContractorDatabase;

public class ContractorActivity extends AppCompatActivity {
    public static final String ID = "ID";
    public static final String MODE = "MODE";
    public static final String NAME = "NAME";
    public static final String SEX = "SEX";
    public static final String PHONE = "PHONE";
    public static final String CELLPHONE = "CELLPHONE";
    public static final String EMAIL = "EMAIL";
    public static final String PRIORITY = "PRIORITY";
    public static final String CONTACTPREFERECE = "CONTACTPREFERENCE";
    public static final int NEW = 1;
    public static final int CHANGE = 2;

    private EditText editTextContractorName, editTextContractorPhone, editTextContractorCellphone, editTextContractorEmail;
    private CheckBox checkBoxContractorPriority;
    private RadioGroup radioGroupContractorContactPreference;
    private Spinner spinnerContractorSex;

    private int mode;
    int requestCode;
    private String originalName;
    private int originalSex;
    private String originalPhone;
    private String originalCellphone;
    private String originalEmail;
    private Boolean originalPriority;
    private int originalContactPreference;
    private Contractor contractor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contractor);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        editTextContractorName = findViewById(R.id.editTextContractorName);
        spinnerContractorSex = findViewById(R.id.spinnerContractorSex);
        editTextContractorPhone = findViewById(R.id.editTextContractorPhone);
        editTextContractorCellphone = findViewById(R.id.editTextContractorCellphone);
        editTextContractorEmail = findViewById(R.id.editTextContractorEmail);
        checkBoxContractorPriority = findViewById(R.id.checkBoxContractorPriority);
        radioGroupContractorContactPreference = findViewById(R.id.radioGroupContractorContactPreference);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if (bundle != null) {
            mode = bundle.getInt(MODE, NEW);

            if (mode == NEW) {
                setTitle(getString(R.string.new_contractor));

                contractor = new Contractor("","","","","",false,-1);
            } else {
                int id = bundle.getInt(ID);

                ContractorDatabase contractorDatabase = ContractorDatabase.getDatabase(this);

                contractor = contractorDatabase.contractorDao().queryById(id);

                originalName = bundle.getString(NAME);
                originalSex = bundle.getInt(SEX);
                originalPhone = bundle.getString(PHONE);
                originalCellphone = bundle.getString(CELLPHONE);
                originalEmail = bundle.getString(EMAIL);
                originalPriority = bundle.getBoolean(PRIORITY);
                originalContactPreference = bundle.getInt(CONTACTPREFERECE);

                editTextContractorName.setText(originalName);
                spinnerContractorSex.setSelection(originalSex);
                editTextContractorPhone.setText(originalPhone);
                editTextContractorCellphone.setText(originalCellphone);
                editTextContractorEmail.setText(originalEmail);
                checkBoxContractorPriority.setChecked(originalPriority);
                RadioButton button;
                switch (originalContactPreference) {
                    case Contractor.PHONE:
                        button = findViewById(R.id.radioButtonContractorContactPreferencePhone);
                        button.setChecked(true);
                        break;
                    case Contractor.CELLPHONE:
                        button = findViewById(R.id.radioButtonContractorContactPreferenceCellphone);
                        button.setChecked(true);
                        break;
                    case Contractor.EMAIL:
                        button = findViewById(R.id.radioButtonContractorContactPreferenceEmail);
                        button.setChecked(true);
                        break;
                }



                setTitle(getString(R.string.change_contractor));
            }
        }

    }

    public void cleanFields() {
        editTextContractorName.setText(null);
        spinnerContractorSex.setSelection(0);
        editTextContractorPhone.setText(null);
        editTextContractorCellphone.setText(null);
        editTextContractorEmail.setText(null);
        checkBoxContractorPriority.setChecked(false);
        radioGroupContractorContactPreference.clearCheck();
        editTextContractorName.requestFocus();

        Toast.makeText(this,R.string.contractorCleanMessage, Toast.LENGTH_SHORT).show();
    }

    public void saveFields() {
        Intent intent = new Intent(this, ContractorListActivity.class);

        String contractorName = editTextContractorName.getText().toString();
        String contractorSex = spinnerContractorSex.getSelectedItem().toString();
        String contractorPhone = editTextContractorPhone.getText().toString();
        String contractorCellphone = editTextContractorCellphone.getText().toString();
        String contractorEmail = editTextContractorEmail.getText().toString();
        boolean contractorPriority = checkBoxContractorPriority.isChecked();
        int contractorContactPreference = radioGroupContractorContactPreference.getCheckedRadioButtonId();

        if (contractorName == null || contractorName.trim().isEmpty()) {
            Toast.makeText(this, R.string.contractorNameEmpty, Toast.LENGTH_SHORT).show();
            editTextContractorName.requestFocus();
            return;
        }

        if (contractorPhone == null || contractorPhone.trim().isEmpty()) {
            Toast.makeText(this, R.string.contractorPhoneEmpty, Toast.LENGTH_SHORT).show();
            editTextContractorPhone.requestFocus();
            return;
        }

        if (contractorCellphone == null || contractorCellphone.trim().isEmpty()) {
            Toast.makeText(this, R.string.contractorCellphoneEmpty, Toast.LENGTH_SHORT).show();
            editTextContractorCellphone.requestFocus();
            return;
        }

        if (contractorEmail == null || contractorEmail.trim().isEmpty()) {
            Toast.makeText(this, R.string.contractorEmailEmpty, Toast.LENGTH_SHORT).show();
            editTextContractorEmail.requestFocus();
            return;
        }

        if (contractorContactPreference < 0) {
            Toast.makeText(this, R.string.contractorContactPreferenceNotChecked, Toast.LENGTH_SHORT).show();
            radioGroupContractorContactPreference.requestFocus();
            return;
        }

        int contactPreference;

        switch (radioGroupContractorContactPreference.getCheckedRadioButtonId()) {
            case R.id.radioButtonContractorContactPreferencePhone:
                contactPreference = Contractor.PHONE;
                break;
            case R.id.radioButtonContractorContactPreferenceCellphone:
                contactPreference = Contractor.CELLPHONE;
                break;
            case R.id.radioButtonContractorContactPreferenceEmail:
                contactPreference = Contractor.EMAIL;
                break;
            default:
                contactPreference = -1;
        }

        ContractorDatabase contractorDatabase = ContractorDatabase.getDatabase(this);

        intent.putExtra(NAME, contractorName);
        intent.putExtra(SEX, contractorSex);
        intent.putExtra(PHONE, contractorPhone);
        intent.putExtra(CELLPHONE, contractorCellphone);
        intent.putExtra(EMAIL, contractorEmail);
        intent.putExtra(PRIORITY, contractorPriority);
        intent.putExtra(CONTACTPREFERECE, contactPreference);

        contractor.setName(contractorName);
        contractor.setSex(contractorSex);
        contractor.setPhone(contractorPhone);
        contractor.setCellphone(contractorCellphone);
        contractor.setEmail(contractorEmail);
        contractor.setPriority(contractorPriority);
        contractor.setContractPreference(contractorContactPreference);

        if (mode == NEW) {
            contractorDatabase.contractorDao().insert(contractor);
        } else {
            contractorDatabase.contractorDao().update(contractor);
        }

        setResult(Activity.RESULT_OK, intent);

        finish();

        //Toast.makeText(this, R.string.contractorSaved, Toast.LENGTH_SHORT).show();
    }

    public static void newContractor(AppCompatActivity activity) {
        Intent intent = new Intent(activity, ContractorActivity.class);
        intent.putExtra(MODE, NEW);
        activity.startActivityForResult(intent, NEW);
    }

    @Override
    public void onBackPressed() {
        setResult(Activity.RESULT_CANCELED);
        finish();
    }



    public static void changeContractor(AppCompatActivity activity, Contractor contractor) {

        Intent intent= new Intent(activity, ContractorActivity.class);

        intent.putExtra(MODE, CHANGE);
        intent.putExtra(NAME, contractor.getName());
        intent.putExtra(SEX, contractor.getSex());
        intent.putExtra(PHONE, contractor.getPhone());
        intent.putExtra(CELLPHONE, contractor.getCellphone());
        intent.putExtra(EMAIL, contractor.getEmail());
        intent.putExtra(PRIORITY, contractor.getPriority());
        intent.putExtra(CONTACTPREFERECE, contractor.getContractPreference());

        activity.startActivityForResult(intent, CHANGE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.contractor_options, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.menuItemContractorSave:
                saveFields();
                return true;
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.menuItemContractorClean:
                cleanFields();
                return true;
            default:
                return super.onContextItemSelected(menuItem);
        }
    }


}