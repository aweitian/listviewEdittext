package cn.wx.listviewedit;

import android.content.Context;

import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.util.List;

/**
 * @author dwj  2017/8/28 14:49
 */

public class MyAdapter extends ArrayAdapter<ItemTest> implements View.OnClickListener, View.OnTouchListener, View.OnFocusChangeListener {

    private int selectedEditTextPosition = -1;

    private TextWatcher mTextWatcher = new SimpleTextWatcher() {
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (selectedEditTextPosition != -1) {
                Log.w("MyAdapter", "onTextCha " + selectedEditTextPosition);
                ItemTest itemTest = getItem(selectedEditTextPosition);
                itemTest.setText(s.toString());
            }
        }
    };

    public MyAdapter(Context context, List<ItemTest> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        VH vh;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_test, null);
            vh = new VH(convertView);
            convertView.setTag(vh);
        } else {
            vh = (VH) convertView.getTag();
        }

        vh.editText.setOnTouchListener(this); // 正确写法
        vh.editText.setOnFocusChangeListener(this);
        vh.editText.setTag(position);

        if (selectedEditTextPosition != -1 && position == selectedEditTextPosition) { // 保证每个时刻只有一个EditText能获取到焦点
            vh.editText.requestFocus();
        } else {
            vh.editText.clearFocus();
        }

        String text = getItem(position).getText();
        vh.editText.setText(text);
        vh.editText.setSelection(vh.editText.length());

        convertView.setTag(R.id.item_root, position); // 应该在这里让convertView绑定position
        convertView.setOnClickListener(this);

        return convertView;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.item_root) {
            int position = (int) v.getTag(R.id.item_root);
            Toast.makeText(getContext(), "点击第 " + position + " 个item", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            EditText editText = (EditText) v;
            selectedEditTextPosition = (int) editText.getTag();
            Log.w("MyAdapter", "clicked position: " + selectedEditTextPosition);
        }
        return false;
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        EditText editText = (EditText) v;
        if (hasFocus) {
            editText.addTextChangedListener(mTextWatcher);
        } else {
            editText.removeTextChangedListener(mTextWatcher);
        }
    }

    public class VH {
        EditText editText;

        public VH(View convertView) {
            editText = (EditText) convertView.findViewById(R.id.et_test);
        }
    }
}
