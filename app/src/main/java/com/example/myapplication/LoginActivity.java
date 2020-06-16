package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {
    // биндим наши View

    Button signin;

    EditText emailTV;

    EditText passwordTV;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //получение объекта кнопки
        Button openRegistrationButton = findViewById(R.id.button2);
        // биндим наши View
        signin = findViewById(R.id.button2);
        emailTV = findViewById(R.id.editText);
        passwordTV = findViewById(R.id.editText);
        //создание слушателя, который запустится при нажатии на соответствующую кнопку.
        openRegistrationButton.setOnClickListener( new );

        signin.setOnClickListener((v) {
                String email = emailTV.getText().toString();
        //Тут опять же могут быть проверки на валидность, подробнее:

        // создаем ссылку на нужный нам эллемент
        MainActivity.firebaseDatabase.getReference(path: "user".child(Utils.md5Custom(email))
        // создаем слушатель, который сработает когда придет ответ от сервера
        // это единичный запрос, так же есть addvalueEventListener(), который добавляет
        // постоянный слушатель, который будет срабатывать каждый раз при изменении
                // https://www.tutorialspoint.com/validate-email-address-in-iava
                // значения этого и дочерних эллементов
        .addListenerForSingleValueEvent(new ValueEventListener() {
        // в качестве входных данных он получает dataSnapshot
        // в котором содержится как нужные нам данные, которые находятся
        // по выше заданой ссылке, так и дополнительные данные
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        // преобразуем полученные данные в объект пользователя
        User user = (User) dataSnapshot.getValue(User.class);
        //если объект будет null, то значит что такого пользователя нет
        // обрабатываем такой вариант событий
        if(user == null){
        // выводим "тост"
        Toasty.error(getApplicationContext(),
                message: "Неправильное имя пользователя или пароль".show();
            // завершаем работу слушателя
            return;
        }
            // в случае если все верно, возвращаемся к нашей загрузочной activity
            // возвращая её обьект пользователя
            Intent intent = new Intent();
            intent.putExtra(name: "user", user);
            setResult(RESULT_OK, intent);
            finish();
          }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError){
                // если мы не смогли "достучаться" до БД выводим соответствующий тост
                Toasty.error(getApplicationContext(),
                        message:"Нет соединения с сервером".show();
                }
          });
        }
            //метод вызывается когда мы нажимаем кнопку "назад"
            // при этом убрано super.onBackPressed(), в следствии чего эта кнопка больше не исполняет
            // прежних функций. Теперь ее функция - эмулировать кнопку "домой". При приложение
            // остается в пуле запущенных

            @Override
            public void onBackPressed() {
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        }
    }
