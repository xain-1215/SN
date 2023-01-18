package com.example.sn.scan;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.example.sn.BottomSheetDialog;
import com.example.sn.R;
import com.example.sn.home_container.HomeVPFragment;

import java.io.File;
import java.io.FileOutputStream;

public class ScanFragment extends Fragment {
    protected FragmentActivity fragmentActivity = null;
    CodeScanner codeScanner;

    public ScanFragment(HomeVPFragment fragment) {
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
        return inflater.inflate(R.layout.fragment_scan, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ImageButton back = view.findViewById(R.id.back);
        CodeScannerView scannerView = view.findViewById(R.id.scanner_view);

        codeScanner = new CodeScanner(requireContext(), scannerView);
        codeScanner.setDecodeCallback(result -> {
            try {
                String SN = result.toString();
                File tmp = new File(fragmentActivity.getFilesDir().toString() + "/tmp.txt");
                if (tmp.exists())
                    tmp.delete();
                tmp.createNewFile();
                FileOutputStream fos = new FileOutputStream(tmp);
                fos.write(SN.getBytes());
                Toast.makeText(fragmentActivity, "Success", Toast.LENGTH_SHORT).show();
//                dialog.dismiss();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        codeScanner.startPreview();
//        back.setOnClickListener(v -> dialog.dismiss());
    }

    @Override
    public void onStart() {
        super.onStart();
    }


    @Override
    public void onStop() {
        super.onStop();
        if (codeScanner != null) {
            codeScanner.stopPreview();
            codeScanner.releaseResources();
        }
    }
}
