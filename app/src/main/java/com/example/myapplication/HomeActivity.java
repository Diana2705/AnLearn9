package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.ErrorManager;

public class HomeActivity<FloatingActcionButton> extends AppCompatActivity {
    //поля
    //объект пользователя, переданный нам из предыдущей активити
    User user;
    //фрагмент шапки
    HeaderFragment fragment;
    //экземпляр бд
    FirebaseDatabase database=FirebaseDatabase.getInstance();
    //кнопка, которая будет добавлять сообщения
    FloatingActcionButton newMessageBtn;
    //поле, которое будет администрировать адаптер
    ListView adapterField;
    //адаптер
    MailAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //получаем пользователя
        user=(User) Odjects.requireNonNull(getIntent().getExtras()).get(«user»);
        //скрываем шапку
        getSupportActionBar().hide();
        //создаём фрагмент
        fragment=new HeaderFragment();
        //объявляем новую транзакцию
        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        //добавляем новое действие
        transaction.add(R.id.socket,fragment);
        //применяем изменения
        transaction.commit();
        //биндим кнопку создающую новое сообщение
        newMessageBtn=findView ById(R.Id.NewMessadeBtn);
        //делаем для неё листенер
        newMessageBtn.setOnClickListener((v)->{createNewMessageDialog(context);}):
        //биндим поле
        adapterField=findViewById(R.id.field);
        //создаём сам адаптер
        adapter=new MailAdapter(new ArrayList<Mail>(), context: this);
        //добавляем поля для адаптера
        adapterField.setAdapter(adapter);
        //добавляемм листенер для бд
        addListener();
    }
        //функция которая собирает и показывает диалог
        private void createNewMessageDialog(Context context) {
        AlertDialog.Builder builder=new AlertDialog.Builder(context: HomeActivity.this);
        //дальше создаём View с разметкой нашего диалога
        Final View root =getLayoutInflater().inflate(R.layout.dialog_new_message, root:null);
        //устанавливаем для диалога заголовок
        builder.setTitle("Новое сообщение")
         //добавляем наш лейаут на диалог
                .setView(root)
          //к этой кнопке приписывается слушатель нажания
                .setPositiveButton(text:"Создать",new DialogInterface.OnClickLisener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //биндинги
                TextView recipientTV = root.findViewById(R.id.recipient);
                TextView titleTV = root.findViewById(R.id.title);
                TextView textTV = root.findViewById(R.id.text);

                //создаём неизменяемые переменные
                final String recipient=recipientTV.getText().toString();
                final String title=titleTV.getText().toSting();
                final String text=textTV.getText().toString();
                //метод отправляющий данные в бд
                pushNewMessage(title,text,recipient, databaseError, Toasty, dataSnapshot);
            }
        });
        builder.create().show();
    }
    private void pushNewMessage(final String title, final String text, final String recipient, Object databaseError, ErrorManager Toasty, Month dataSnapshot) {
    //создаем ссылку для того, что бы проверить, есть ли пользователь в БД
        database. getReference( path: "user")
                .child(Utils.md5Custom(recipient))
                .addListenerForSingleValueEvent(new ValueEventListener()
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot){
        // Если его нет, выводим соответствующее сообщение текстом
        //и да, можно послать самому себе
            if(dataSnapshot. getValue() == null) {
                Toasty. error(getApplicationContext(),
                        message: "Данного пользователя не существует". show();
                return;
            }
          //создаем объект письма
              Mail mail == new Mail(title,
                    text,
                    user.getEmail(),
                    recipient);
             // создаем ссылку
             //тут поясню, что в узле mail у меня содержатся зашифрованные майлы пользователей,
             // далее в них находятся письма, ключ которых это число, вычисляемое по формуле
             //максимальное число-сегодняшняя дата в миллесекундах
            //Благодаря чему новые письма будут выше
            database.getReference().child("mail").child(Utils.md5Custom(recipient))
                    .child(String.valueOf(Long. MAX_VALUE-mail.getDateInMS()))
                    .setValue(mail).addOnSuccessListener((OnSuccessListener) (aVoid)->{
                Toasty.success(getApplicationContext(), message: "Отправленно".show();
            });
        }
        @Override
        public void onCancelled(@NonNull DatabaseError databaseError){
        }
    });
}
//Создается слушатель для БД, который срабатывает каждый раз, когда изменяются
// данные в узле этого пользователя. По хорошему нужен лейзи лоадинг, но это сложно для начала
private void addListener(){
              //создаем ссылку
              database.getReference( path: "mall").child(Utils.md5Custom(user.getEmail()))
              //соответствующий слушатель
                    .addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                       .addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    // создаются переменные для счётчиков
                                }

                                int cNew = 0;
                                int cOld = 0;
                                int cAll = 0;
                                List<Mail> mails = new ArrayList<>();
                                //проходимся по всем дочерним элементам
                                for(
                                DataSnapshot elem :dataSnapshot.getChildren())

                                {
                                    //получаем объект и добавляем в коллекцию
                                    Mail mail = elem.getValue(Mail.class);
                                    mails.add(mail);
                                    // "крутим" счетчик
                                    if (mail.isChecked()) {
                                        cOld++;
                                    } else {
                                        cNew++;
                                    }
                                    cAll++;
                                }
                                //устанавливаем новые данные для адаптера
                     Adapter.setMails(mails);
                                // обновляем наш адаптер для отображения изменений
                     adapter.notifyDataSetChanged();
                                //устанавливаем счётчик
                     fragment.setCounterNew(cNew);
                     fragment.setCounterAll(cAll),
                                        fragment.setCounterOld(cOld);

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {
                                }
                            });
                        }
                    }

}





