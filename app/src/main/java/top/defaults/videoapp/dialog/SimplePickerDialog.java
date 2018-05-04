package top.defaults.videoapp.dialog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import top.defaults.videoapp.R;
import top.defaults.view.PickerView;

public class SimplePickerDialog<T extends PickerView.PickerItem> extends PickerDialog<T> {

    private ActionListener<T> actionListener;
    private PickerView pickerView;
    private List<T> items;

    public static <T extends PickerView.PickerItem> SimplePickerDialog<T> create(ActionListener<T> actionListener) {
        SimplePickerDialog<T> dialog = new SimplePickerDialog<>();
        dialog.actionListener = actionListener;
        return dialog;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_simple_picker, container, false);

        pickerView = view.findViewById(R.id.pickerView);
        pickerView.setItems(items, null);

        attachActions(view.findViewById(R.id.done), view.findViewById(R.id.cancel));
        return view;
    }

    private void attachActions(View done, View cancel) {
        cancel.setOnClickListener(v -> {
            if (actionListener != null) {
                actionListener.onCancelClick(this);
            }
            dismiss();
        });
        done.setOnClickListener(v -> {
            if (actionListener != null) {
                actionListener.onDoneClick(this);
            }
            dismiss();
        });
    }

    @Override
    public T getSelectedItem(Class<T> cls) {
        return pickerView.getSelectedItem(cls);
    }
}
