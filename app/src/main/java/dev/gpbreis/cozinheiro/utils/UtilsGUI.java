package dev.gpbreis.cozinheiro.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.EditText;

import dev.gpbreis.cozinheiro.R;

public class UtilsGUI {

    public static void errorWarning(Context context, int textId) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setTitle(R.string.warningTitle);
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        builder.setMessage(textId);

        builder.setNeutralButton(context.getString(R.string.warningNeutralButtonText),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public static void confirmation(Context context, String message, DialogInterface.OnClickListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setTitle(R.string.confirmationTitle);
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        builder.setMessage(message);
        builder.setPositiveButton(R.string.confirmationYes, listener);
        builder.setNegativeButton(R.string.confirmationNo, listener);

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public static String textFieldValidation(Context context, EditText editText, int errorMessageId) {
        String text = editText.getText().toString();

        if (UtilsString.emptyString(text)) {
            UtilsGUI.errorWarning(context, errorMessageId);
            editText.setText(null);
            editText.requestFocus();
            return null;
        } else {
            return text.trim();
        }
    }
}
