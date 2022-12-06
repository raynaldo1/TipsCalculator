package github.comraynaldo1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {

    private static final String CONTA = "CONTA";
    private static final String PERCENTUAL = "PERCENTUAL";

    private double conta;
    private double percentual;

    private EditText contaEditText;
    private EditText gorjeta5EditText;
    private EditText gorjeta10EditText;
    private EditText gorjeta15EditText;
    private SeekBar percentualSeekBar;
    private EditText percentualEditText;
    private EditText gorjetaEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contaEditText = (EditText) findViewById(R.id.contaEditText);
        gorjeta5EditText = (EditText) findViewById(R.id.gorjeta5EditText);
        gorjeta10EditText = (EditText) findViewById(R.id.gorjeta10EditText);
        gorjeta15EditText = (EditText) findViewById(R.id.gorjeta15EditText);
        percentualSeekBar = (SeekBar) findViewById(R.id.percentualSeekBar);
        percentualEditText = (EditText) findViewById(R.id.percentualEditText);
        gorjetaEditText = (EditText) findViewById(R.id.gorjetaEditText);

        contaEditText.addTextChangedListener(ouvinteContaEditText);
        percentualSeekBar.setOnSeekBarChangeListener(ouvintePercentualSeekBar);

        if (savedInstanceState == null) {
            conta = 0;
            percentual = 7;
        } else {
            conta = savedInstanceState.getDouble(CONTA);
            percentual = savedInstanceState.getDouble(PERCENTUAL);
        }

        contaEditText.setText(String.format("%.2f", conta));
        percentualSeekBar.setProgress((int) percentual);

    }

    private void atualizadorGorjetas() {
        double[] gorjetas = Calculadora.gorjeta(conta);
        gorjeta5EditText.setText(String.format("%.2f", gorjetas[0]));
        gorjeta10EditText.setText(String.format("%.2f", gorjetas[1]));
        gorjeta15EditText.setText(String.format("%.2f", gorjetas[2]));
    }

    private void atualizadorGorjetaPersonalizada() {
        gorjetaEditText.setText(String.format("%.2f", Calculadora.gorjeta(conta, percentual)));
    }

    private TextWatcher ouvinteContaEditText = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            try {
                conta = Double.parseDouble(contaEditText.getText().toString());
            } catch (NumberFormatException e) {
                conta = 0;
            }
            atualizadorGorjetas();
            atualizadorGorjetaPersonalizada();

        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    private SeekBar.OnSeekBarChangeListener ouvintePercentualSeekBar = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
            percentual = (double) percentualSeekBar.getProgress();
            percentualEditText.setText(String.format("%.2f", percentual));
            atualizadorGorjetaPersonalizada();

        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };

}