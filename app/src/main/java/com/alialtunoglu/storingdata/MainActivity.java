package com.alialtunoglu.storingdata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    TextView textView;
    SharedPreferences sharedPreferences; // böyle tanımlamamızın sebebi ise fonksiyonlarda da kullanabilmemiz
    // için ortak bir değişken global değişken oluşturuyoruz
    /*
    * Android’de SharedPreferences, uygulamaların küçük çaplı verilerini uygulama kapansa bile tutmak için
    * kullanılan bir yöntemdir. Key-value mantığı ile çalışır ve Int, Boolean, Float, Long, String
    * değişkenlerindeki değerleri tutabilirsiniz1. Genelde programların ayarlar bölümündeki veriler ve
    * seçenekler bu yol ile kaydedilmektedir
    * */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //numarayı almak için id leri buluyoruz ve içini eşitliyoruz.
        editText = findViewById(R.id.editText2);
        textView = findViewById(R.id.textView);

        //mode private -> Bu saklayacağım verilere sadece benim minik veri tabanına sadece benim uygulamamdan ulaşılsın
        sharedPreferences = this.getSharedPreferences("com.atilsamancioglu.storingdata", Context.MODE_PRIVATE);

        //edit.putInt() ile kaydettiğim veriyi nasıl geri alırım aşağıdaki gibi geri alırım
        //defValue -> eğer kayıtlı bir şey yoksa default olarak hangi değeri veriyim diyorsa
        //ve bunu bir değişkene eşitlememiz lazım kayıtlı bir şey yoksa storedAge=0 olur;
        int storedAge = sharedPreferences.getInt("storedAge",0);

        //Kullanıcıdan yaşı istedik ve onu kaydetmek istedik
        if (storedAge == 0) {
            textView.setText("Your age: ");
        } else {
            textView.setText("Your age: " + storedAge);
        }

    }

    /*arayüzdeki butonun onClickteki name'i ile butona tıklayınca ne yapacağı ile ilgili  şu şekilde
    fonksiyon oluşturulur  public void save(View view){} */
    public void save(View view) {

        //eğer kullanıcı girdi olarak hiçbir şey vermediyse herhangi bir işlem yapmama gerek yok
        //bir girdi verirse ifin altındakini yap
        if (!editText.getText().toString().matches("")) {
            int userAge = Integer.parseInt(editText.getText().toString());
            textView.setText("Your age: " + userAge);
            //kaydetmemiz için bir tane anahtar kelime(Key) istiyor ve birde ne kaydedilceği(Value)
            sharedPreferences.edit().putInt("storedAge",userAge).apply();



        }

    }


    public void delete(View view ) {

        int storedData = sharedPreferences.getInt("storedAge",0);

        if (storedData != 0) {
            sharedPreferences.edit().remove("storedAge").apply();
            textView.setText("Your age: ");
        }
    }
}