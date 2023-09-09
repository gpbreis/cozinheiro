package dev.gpbreis.cozinheiro;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editTextContractorName, editTextContractorPhone, editTextContractorCellphone, editTextContractorEmail;
    private CheckBox checkBoxContractorPriority;
    private RadioGroup radioGroupContractorContactPreference;
    private Spinner spinnerContractorSex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextContractorName = findViewById(R.id.editTextContractorName);
        spinnerContractorSex = findViewById(R.id.spinnerContractorSex);
        editTextContractorPhone = findViewById(R.id.editTextContractorPhone);
        editTextContractorCellphone = findViewById(R.id.editTextContractorCellphone);
        editTextContractorEmail = findViewById(R.id.editTextContractorEmail);
        checkBoxContractorPriority = findViewById(R.id.checkBoxContractorPriority);
        radioGroupContractorContactPreference = findViewById(R.id.radioGroupContractorContactPreference);
    }

    public void cleanFields(View view) {
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

    public void saveFields(View view) {
        String contractorName = editTextContractorName.getText().toString();
        String contractorSex = spinnerContractorSex.getSelectedItem().toString();
        String contractorPhone = editTextContractorPhone.getText().toString();
        String contractorCellphone = editTextContractorCellphone.getText().toString();
        String contractorEmail = editTextContractorEmail.getText().toString();
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

        Toast.makeText(this, R.string.contractorSaved, Toast.LENGTH_SHORT).show();
    }
}