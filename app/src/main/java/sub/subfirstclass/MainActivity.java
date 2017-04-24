package sub.subfirstclass;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    Button buttonSave;
    EditText eTUserName, eTPassword, eTEmail, eTCGPA, eTBloodGroup, eTPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();
    }

    public void saveData(View view){
        Boolean error = false;
        String userName = eTUserName.getText().toString().trim();
        String userEmail=eTEmail.getText().toString().trim();
        String usercGpa=eTCGPA.getText().toString().trim();
        String userBloodGroup=eTBloodGroup.getText().toString().trim();
        String userPhoneNumber=eTPhone.getText().toString().trim();
        Double cgpa = null;

        if(userName.isEmpty()){
            error = true;
            eTUserName.setError("UserName field is empty");
        }else if(userName.length()<6){
            error = true;
            eTUserName.setError("UserName should at least 6 character!");
        }
        String password = eTPassword.getText().toString();
        if(password.isEmpty()){
            error = true;
            eTPassword.setError("Password is missing!");
        }
        else if(eTPassword.length()<6){
            error = true;
            eTPassword.setError("Password should have minimum 6 chars!");
        }
        if (userEmail.isEmpty()){
            error=true;
            eTEmail.setError("Email field is empty");
        }
        else if (emailValidator(userEmail)==false){
           error=true;
           eTEmail.setError("Email is not valid");
        }
        if(usercGpa.isEmpty()){
            error = true;
            eTCGPA.setError("CGPA is missing!");
        }else{
            cgpa = Double.parseDouble(usercGpa);
        }
        if (userBloodGroup.isEmpty()){
            error=true;
            eTBloodGroup.setError("Blood Group field is empty");
        }
        else if(bloodgroupValidator(userBloodGroup)==false){
            error=true;
            eTBloodGroup.setError("Blood Group is not valid");
        }
        if (userPhoneNumber.isEmpty()){
            error=true;
            eTPhone.setError("Phone Number field is empty");
        }
        else if(userPhoneNumber.length()<11){
            error=true;
            eTPhone.setError("Phone number should contain 11 digit");
        }
        String data = userName+"\n"+userEmail+"\n"+cgpa+"\n"+userBloodGroup+"\n"+userPhoneNumber;

        if(error == false) {
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setCancelable(false);
            dialog.setTitle("Student Details");
            dialog.setMessage(data);

            dialog.setPositiveButton(
                    "Yes",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog1, int id) {
                            dialog1.cancel();
                        }
                    });

            dialog.setNegativeButton(
                    "No",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog1, int id) {
                            dialog1.cancel();
                        }
                    });

            AlertDialog alert11 = dialog.create();
            alert11.show();

        }else{
            Toast.makeText(MainActivity.this, "Data is not saved!", Toast.LENGTH_LONG).show();

        }

    }

    private void initialize() {
        buttonSave = (Button) findViewById(R.id.buttonSave);
        eTUserName = (EditText) findViewById(R.id.editTextUserName);
        eTPassword = (EditText) findViewById(R.id.editTextPassword);
        eTEmail = (EditText) findViewById(R.id.editTextEmail);
        eTCGPA = (EditText) findViewById(R.id.editTextCGPA);
        eTBloodGroup = (EditText) findViewById(R.id.editTextBloodGroup);
        eTPhone = (EditText) findViewById(R.id.editTextPhone);
    }
    private boolean emailValidator(String email)
    {
        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }
    private boolean bloodgroupValidator(String bGroup){
        boolean checkBlood=false;
        final String[] BLOOD_PATTERN={
                "A+",
                "a+",
                "A-",
                "a-",
                "B+",
                "b+",
                "B-",
                "b-",
                "AB+",
                "ab+",
                "ab-",
                "O+",
                "o+",
                "O-",
                "o-"
        };
        for (int i=0;i<BLOOD_PATTERN.length;i++){
            if(bGroup.equals(BLOOD_PATTERN[i])){
                checkBlood=true;
                break;
            }
        }
      return checkBlood;
    }
}
