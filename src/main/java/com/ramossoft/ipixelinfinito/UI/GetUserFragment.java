package com.ramossoft.ipixelinfinito.UI;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ramossoft.ipixelinfinito.Model.PayDetails;
import com.ramossoft.ipixelinfinito.R;
import com.ramossoft.ipixelinfinito.RestClient.RestClient;
import com.ramossoft.ipixelinfinito.Service.GetUser;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GetUserFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GetUserFragment extends Fragment {

    TextView txtName;
    TextView txtNetwork;
    TextView txtEnv;
    TextView txtAddress;
    TextView txtTag;
    EditText edtPayStringG;
    CardView cvGetuser;

    Button btnGet;

    Call<PayDetails>payCall;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public GetUserFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GetUserFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GetUserFragment newInstance(String param1, String param2) {
        GetUserFragment fragment = new GetUserFragment();
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
        View view=inflater.inflate(R.layout.fragment_get_user, container, false);
        txtName=view.findViewById(R.id.txtPID);
        txtAddress=view.findViewById(R.id.txtAddress);
        txtEnv=view.findViewById(R.id.txtEnv);
        txtNetwork=view.findViewById(R.id.txtNetwork);
        edtPayStringG=view.findViewById(R.id.edtPayStringG);
        cvGetuser=view.findViewById(R.id.cvGetUser);
        btnGet=view.findViewById(R.id.btnGet);

        btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GetUser getUser= RestClient.getClient().create(GetUser.class);
                String pay=edtPayStringG.getText().toString();
                payCall=getUser.getUSers(pay+"$ipixelinfinito.sandbox.paystring.org");
                payCall.enqueue(new Callback<PayDetails>() {
                    @Override
                    public void onResponse(Call<PayDetails> call, Response<PayDetails> response) {

                        if(response.isSuccessful()){
                            PayDetails details=response.body();
                            cvGetuser.setVisibility(View.VISIBLE);
                            txtName.setText("PayString ID: "+details.getPayId());
                            txtEnv.setText("Environment: "+details.getAddresses().get(0).getEnvironment());
                            txtNetwork.setText("Network: "+details.getAddresses().get(0).getPaymentNetwork());
                            txtAddress.setText("Address: "+details.getAddresses().get(0).details.getAddress());

                        }else {
                            cvGetuser.setVisibility(View.GONE);
                            Toast.makeText(getContext(),"please, verify all the details Entered Correctly seen like there is a mistake on your details, and try again",Toast.LENGTH_LONG).show();

                        }


                    }

                    @Override
                    public void onFailure(Call<PayDetails> call, Throwable t) {
                        cvGetuser.setVisibility(View.GONE);
                        Toast.makeText(getContext(),"Ops! there is a problem with the connection, please check your connection and try again ",Toast.LENGTH_LONG).show();

                    }
                });
            }
        });



        return view;
    }
}