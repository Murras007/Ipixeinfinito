package com.ramossoft.ipixelinfinito.UI;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ramossoft.ipixelinfinito.Model.PayDetails;
import com.ramossoft.ipixelinfinito.R;
import com.ramossoft.ipixelinfinito.RestClient.RestClient;
import com.ramossoft.ipixelinfinito.Service.DeleteUser;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DeleteUserFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DeleteUserFragment extends Fragment {
    DeleteUser deleteUserService;
    Call<String>stringCall;
    Button delete;
    EditText edtname;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DeleteUserFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DeleteUserFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DeleteUserFragment newInstance(String param1, String param2) {
        DeleteUserFragment fragment = new DeleteUserFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_delete_user, container, false);
        delete=view.findViewById(R.id.btnDelete);
        edtname=view.findViewById(R.id.edtDName);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=edtname.getText().toString();
                PayDetails payDetails=new PayDetails();
                payDetails.setPayId(name+"$ipixelinfinito.sandbox.paystring.org");

                deleteUserService= RestClient.getClient().create(DeleteUser.class);
                stringCall=deleteUserService.getDeleted(name+"$ipixelinfinito.sandbox.paystring.org");
                stringCall.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if(response.isSuccessful()){
                            String result=response.body();
                            Toast.makeText(getContext(),result,Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(getContext(),"please verify your PayString Id entered correctly seen like a mistake and try again",Toast.LENGTH_LONG).show();

                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Toast.makeText(getContext(),"Ops! there is a problem with the connection, please check your connection and try again ",Toast.LENGTH_LONG).show();

                    }
                });

            }
        });


        return view;
    }
}