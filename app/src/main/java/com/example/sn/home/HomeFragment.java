package com.example.sn.home;

import android.annotation.SuppressLint;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.sn.BottomSheetDialog;
import com.example.sn.R;
import com.example.sn.home_container.HomeVPFragment;
import com.example.sn.home_container.HomeViewPagerAdapter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class HomeFragment extends Fragment implements HomeView {
    EditText editText;
    protected FragmentActivity fragmentActivity = null;

    public HomeFragment(HomeVPFragment fragment) {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentActivity = getActivity();

    }

    @SuppressLint("InflateParams")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home_fragment, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        editText = view.findViewById(R.id.editText);
        editText.setOnClickListener(v -> editText.getText().clear());
        Button confirm = view.findViewById(R.id.confirm);
        Button clean = view.findViewById(R.id.clean);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.symlink.askSN");
        confirm.setOnClickListener(v -> {
            try {
                String SN = editText.getText().toString();
                File tmp = new File(fragmentActivity.getFilesDir().toString() + "/tmp.txt");
                if (tmp.exists())
                    tmp.delete();
                tmp.createNewFile();
                FileOutputStream fos = new FileOutputStream(tmp);
                fos.write(SN.getBytes());
                Toast.makeText(fragmentActivity, "Success", Toast.LENGTH_SHORT).show();
                editText.getText().clear();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        clean.setOnClickListener(v -> editText.getText().clear());

        ImageButton scan = view.findViewById(R.id.scan);
        scan.setOnClickListener(v -> {
            BottomSheetDialog bottom = new BottomSheetDialog();
            bottom.show(fragmentActivity.getSupportFragmentManager(), "");
        });
    }


    @Override
    public void getResult(String result) {
        fragmentActivity.runOnUiThread(() -> editText.setText(result));
    }
}
