package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class MyFragment extends Fragment {
    Object param;

    public MyFragment(Object param) {
        this.param = param;
    }

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup
            container, @Nullable Bundle savedInstanceState) {
        //при создании фрагмента мы задаем руту значение
        View root = inflater.inflate(R.layout.fragment_my, container, false);
        //биндим все, как источник указываем рут
        //в проекте мы используем заменитель этого, но канонический бинд выглядит так!
        TextView textView = root.findViewById(R.id.textView);
        //возвращаем наш рут
        return root;
    }

    /*
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                MyFragment fragment = new MyFragment();
                transaction.add(R.id.socket,fragment);
                transaction.commit();
                MyAdapter adapter = new MyAdapter(arrayList<Object>(), getApplicationContext());
                listView.setAdapter(adapter);
    }
     */
    private int counterAll;
    private int counterNew;

    public void setCounterAll(int counterAll) {
        this.counterAll = counterAll;
    }

    public void setCounterNew(int counterNew) {
        this.counterNew = counterNew;
    }

    public void setCounterOld(int counterOld) {
        this.counterOld = counterOld;
    }

    private int counterOld;
}















