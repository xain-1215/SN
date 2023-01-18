package com.example.sn;

import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;


public class BottomSheetDialog extends BottomSheetDialogFragment {
    CodeScanner codeScanner;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @RequiresApi(api = Build.VERSION_CODES.R)
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState) ;
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_scan,null);
        initView(view, dialog);
        dialog.setContentView(view);
//        dialog.getWindow().findViewById(R.id.wah).setBackgroundResource(android.R.color.transparent);
        BottomSheetBehavior behavior=BottomSheetBehavior.from((View)view.getParent());
        behavior.setPeekHeight(2500);
        return dialog;
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

    private void initView(View view, Dialog dialog) {
        ImageButton back = view.findViewById(R.id.back);
        CodeScannerView scannerView = view.findViewById(R.id.scanner_view);

        codeScanner = new CodeScanner(requireContext(), scannerView);
        codeScanner.setDecodeCallback(result -> {
//            mResultListener.getResult(result.toString());
            dialog.dismiss();
        });
        codeScanner.startPreview();
        back.setOnClickListener(v -> dialog.dismiss());
    }


}
