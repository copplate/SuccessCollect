package com.example.diffutiltest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.diffutiltest.adapter.ItemAdapter;
import com.example.diffutiltest.adapter.ItemAdapterChange;
import com.example.diffutiltest.bean.Item;

import java.util.ArrayList;
import java.util.List;

public class DiffActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ItemAdapter itemAdapter;
    private ItemAdapterChange itemAdapterChange;
    private Button btnAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diff);

        recyclerView = findViewById(R.id.recyclerView);
        btnAdd = findViewById(R.id.btn_add);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<Item> itemList = new ArrayList<>();
        List<String> strings = new ArrayList<>();
        itemAdapter = new ItemAdapter(itemList);
        itemAdapterChange = new ItemAdapterChange(strings);
        recyclerView.setAdapter(itemAdapterChange);

        // 模拟更新数据
        List<Item> newItems = generateNewItems();
        itemAdapter.updateItems(newItems);
        // 模拟更新数据
        List<String> newStrings = generateNewStrings();
        itemAdapterChange.updateItems(newStrings);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*newItems.add(new Item(1, "Item 4"));
                newItems.add(new Item(2, "Item 5"));
                newItems.add(new Item(3, "Item 6"));*/
                newStrings.add("Item 4");
                newStrings.add("Item 5");
                newStrings.add("Item 6");

//                itemAdapter.updateItems(newItems);
                itemAdapterChange.updateItems(newStrings);

            }
        });
    }
    private List<Item> generateNewItems() {
        // 生成新的列表项数据
        List<Item> newItems = new ArrayList<>();
        newItems.add(new Item(1, "Item 1"));
        newItems.add(new Item(2, "Item 2"));
        newItems.add(new Item(3, "Item 3"));
        return newItems;
    }
    private List<String> generateNewStrings() {
        // 生成新的列表项数据
        List<String> newItems = new ArrayList<>();
        newItems.add("Item 1");
        newItems.add("Item 2");
        newItems.add("Item 3");
        return newItems;
    }

}