package com.example.foodwise;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.fragment.app.Fragment;
import com.example.foodwise.R;
//import com.example.foodwise.AddByPicActivity;
import com.example.foodwise.AddManuallyActivity;

public class AddFragment extends Fragment {

    private Button manualBtn;

    public AddFragment() { }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_add, container, false);

        manualBtn = view.findViewById(R.id.addManuallyBTN);

        getActivity().setTitle("Choose a method");

        manualBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                startActivity(new Intent(getActivity(), AddManuallyActivity.class));
            }
        });

        return view;
    }

}