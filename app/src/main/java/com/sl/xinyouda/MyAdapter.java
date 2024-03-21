package com.sl.xinyouda;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.sl.xinyouda.data.entry.ItemTest;
import com.sl.xinyouda.databinding.ItemTestBinding;

import java.util.List;

public class MyAdapter extends ArrayAdapter<ItemTest> {
    private List<ItemTest> gondarList;
    private Context context;
    public MyAdapter(Context context, List<ItemTest> objects) {
        super(context, 0, objects);
        this.gondarList = objects;
        this.context = context;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ItemTestBinding binding;

        if (convertView == null) {
            binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.item_test, parent, false);
            convertView = binding.getRoot();
        } else {

            binding = DataBindingUtil.getBinding(convertView);
        }

        assert binding != null;
        binding.setGondar(gondarList.get(position));
        return convertView;
    }
}
