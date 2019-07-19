package id.co.blogspot.diansano.appmenyimpandatadifile;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    final int READ_BLOCK_SIZE = 100;
    EditText textBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textBox = findViewById(R.id.editText);
    }


    public void onClickSave(View v) {
        String str = textBox.getText().toString();
        try {
            FileOutputStream fOut = openFileOutput("textfile.txt", MODE_PRIVATE);

            OutputStreamWriter osw = new OutputStreamWriter(fOut);

            osw.write(str);
            osw.flush();
            osw.close();
            //display the saved message
            Toast.makeText(this, "File saved successfully!", Toast.LENGTH_SHORT).show();
            //clear the EditText
            textBox.setText("");
        } catch (IOException ioe) {
            Toast.makeText(this, "File can't be saved!", Toast.LENGTH_SHORT).show();
            ioe.printStackTrace();
        }
    }

    public void onClickLoad(View v) {
        try {
            FileInputStream fIn = openFileInput("textfile.txt");
            InputStreamReader isr = new InputStreamReader(fIn);
            char[] inputBuffer = new char[READ_BLOCK_SIZE];
            String s = "";
            int charRead;
            while ((charRead = isr.read(inputBuffer)) > 0) {
                //convert chars to a string
                String readString = String.copyValueOf(inputBuffer, 0, charRead);
                s += readString;
                inputBuffer = new char[READ_BLOCK_SIZE];
            }
            textBox.setText(s);
            Toast.makeText(this, "File loaded successfully", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(this, "File can't be loaded!", Toast.LENGTH_SHORT).show();
            e.printStackTrace();

        }
    }
}
