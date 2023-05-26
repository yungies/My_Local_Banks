package sg.edu.rp.c364.id22037903.mylocalbanks;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button dbsButton;
    private Button ocbcButton;
    private Button uobButton;

    private TextView selectedBankTextView;
    private String selectedBank;

    private String wordClicked = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbsButton = findViewById(R.id.DBSbk);
        ocbcButton = findViewById(R.id.OCBCbk);
        uobButton = findViewById(R.id.UOBbk);

        selectedBankTextView = null;

        registerForContextMenu(dbsButton);
        registerForContextMenu(ocbcButton);
        registerForContextMenu(uobButton);

        dbsButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                selectedBankTextView = dbsButton;
                selectedBank = getString(R.string.dbs_bank);
                return false;
            }
        });

        ocbcButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                selectedBankTextView = ocbcButton;
                selectedBank = getString(R.string.ocbc_bank);
                return false;
            }
        });

        uobButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                selectedBankTextView = uobButton;
                selectedBank = getString(R.string.uob_bank);
                return false;
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        if (v == selectedBankTextView) {
            menu.add(0, 0, 0, "Show Website");
            menu.add(0, 1, 1, "Contact Bank");
            menu.add(0, 2, 2, "Translate to English");
            menu.add(0, 3, 3, "Translate to Chinese");

            if (v == dbsButton) {
                wordClicked = getString(R.string.dbs_bank);
            } else if (v == ocbcButton) {
                wordClicked = getString(R.string.ocbc_bank);
            } else if (v == uobButton) {
                wordClicked = getString(R.string.uob_bank);
            }
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getItemId() == 0) {
            openBankWebsite(getBankWebsiteUrl(selectedBank));
            return true;
        } else if (item.getItemId() == 1) {
            dialBankContactNumber(getBankContactNumber(selectedBank));
            return true;
        } else if (item.getItemId() == 2) {
            translateToEnglish(selectedBankTextView);
            return true;
        } else if (item.getItemId() == 3) {
            translateToChinese(selectedBankTextView);
            return true;
        }

        return super.onContextItemSelected(item);
    }

    private void openBankWebsite(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }

    private void dialBankContactNumber(String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        startActivity(intent);
    }

    private String getBankWebsiteUrl(String bank) {
        switch (bank) {
            case "DBS":
                return getString(R.string.dbs_website_url);
            case "OCBC":
                return getString(R.string.ocbc_website_url);
            case "UOB":
                return getString(R.string.uob_website_url);
            default:
                return "";
        }
    }

    private String getBankContactNumber(String bank) {
        switch (bank) {
            case "DBS":
                return getString(R.string.dbs_contact_number);
            case "OCBC":
                return getString(R.string.ocbc_contact_number);
            case "UOB":
                return getString(R.string.uob_contact_number);
            default:
                return "";
        }
    }

    private void translateToEnglish(TextView textView) {
        if (textView == dbsButton) {
            textView.setText(getString(R.string.dbs_bank));
        } else if (textView == ocbcButton) {
            textView.setText(getString(R.string.ocbc_bank));
        } else if (textView == uobButton) {
            textView.setText(getString(R.string.uob_bank));
        }

        Toast.makeText(MainActivity.this, "Translated to English", Toast.LENGTH_SHORT).show();
    }

    private void translateToChinese(TextView textView) {
        if (textView == dbsButton) {
            textView.setText(getString(R.string.dbs_bank_chinese));
        } else if (textView == ocbcButton) {
            textView.setText(getString(R.string.ocbc_bank_chinese));
        } else if (textView == uobButton) {
            textView.setText(getString(R.string.uob_bank_chinese));
        }

        Toast.makeText(MainActivity.this, "Translated to Chinese", Toast.LENGTH_SHORT).show();
    }
}
