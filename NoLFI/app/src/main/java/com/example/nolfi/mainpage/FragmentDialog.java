package com.example.nolfi.mainpage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.example.nolfi.R;

public class FragmentDialog extends DialogFragment {
    private Fragment fragment;
    public FragmentDialog() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog, container, false);
        Bundle args = getArguments();
        String value = args.getString("key");
        fragment = getActivity().getSupportFragmentManager().findFragmentByTag("tag");

        if (fragment != null) {
            DialogFragment dialogFragment = (DialogFragment) fragment;
            dialogFragment.dismiss();
        }
        return view;
    }
}
