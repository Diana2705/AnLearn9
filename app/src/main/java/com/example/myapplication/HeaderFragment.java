package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class HeaderFragment extends Fragment {
    // поле для нашего рута
    View root;
    // биндинги
    @BindView(R.id.counterALL)
    TextView counterAll;
    @BindView(R.id.counterNew)
    TextView counterNew;
    @BindView(R.id.counterold)
    TextView counterOld;

    // метод вызывается при создании представления
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState)
      //при создании фрагмента мы задаем руту значение
      root = inflater.inflate(R.layout.fragment_header, container, attachToRoot: false);
      //биндим все, как источник указываем рут
      ButterKnife.bind(target: this, root);
      //возвращаем рут
      return root;
     // устанавливает значение для счетчиков
     public void setCounterAll(int value) {counterAll.setText(String.valueOf(value));}

     public void setCounterNew(int value) {counterNew.setText(String.valueOf(value));}

     public void setCounterOld(int value) {counterOld.setText(String.valueOf(value));}
}
