package me.xdream.fsql;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import me.xdream.fsql.services.DataBaseServices;
import me.xdream.fsql.vo.Person;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
   Button saveR;
   Button resume;
   EditText edName;
   EditText edAge;
   Button save2dba;
   Button resumeFdba;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        saveR=findViewById(R.id.s2sharer);
        resume=findViewById(R.id.resumeFshare);
        edName=findViewById(R.id.edName);
        edAge=findViewById(R.id.edAge);
       saveR.setOnClickListener(this);
       resume.setOnClickListener(this);
       save2dba=findViewById(R.id.s2dba);
       resumeFdba=findViewById(R.id.resumeFdba);
       save2dba.setOnClickListener(this);
       resumeFdba.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.s2sharer:
                String name=edName.getText().toString();
                int age= Integer.parseInt(edAge.getText().toString());
                SharedPreferences preferences=getSharedPreferences("shared", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor=preferences.edit();
                editor.putString("name", name);
                editor.putInt("age",age);
                editor.commit();
                Toast.makeText(MainActivity.this, "参数文件保存成功", Toast.LENGTH_SHORT).show();

                break;
            case  R.id.resumeFshare:
                SharedPreferences sharedPreferences=getSharedPreferences("shared", Context.MODE_PRIVATE);
                String name1=sharedPreferences.getString("name","");
                int age1=sharedPreferences.getInt("age",0);
                edName.setText(name1);
                edAge.setText(String.valueOf(age1));
                break;
            case R.id.s2dba:
                DataBaseServices dataBaseServices=new DataBaseServices(MainActivity.this);
                dataBaseServices.save(new Person(0,edName.getText().toString(),Integer.parseInt(edAge.getText().toString())));
                break;
            case R.id.resumeFdba:
                DataBaseServices dataBaseServices1=new DataBaseServices(MainActivity.this);
                Person p1=dataBaseServices1.findById(0);
                edName.setText(p1.getName());
                edAge.setText(p1.getAge()+"");
                break;
        }
    }
}
