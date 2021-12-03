package com.ramossoft.ipixelinfinito.UI;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ramossoft.ipixelinfinito.Model.Address;
import com.ramossoft.ipixelinfinito.Model.Details;
import com.ramossoft.ipixelinfinito.Model.PayDetails;
import com.ramossoft.ipixelinfinito.R;
import com.ramossoft.ipixelinfinito.RestClient.RestClient;
import com.ramossoft.ipixelinfinito.Service.UpdateUser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ReplaceUserFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ReplaceUserFragment extends Fragment {
    UpdateUser updateUserService;
    Call<PayDetails> detailsCall;
    Button btnUpdate;
    EditText edtOldPayString;
    EditText edtname;
    EditText edtTag;
    EditText edtAddress;
    Spinner spinnerNetwork;
    CardView cvTestMainnet;
    CardView cvEth;

    CardView cvReplace;
    TextView txtPID;
    TextView txtNetwork;
    TextView txtEnv;
    TextView txtAddress;
    TextView txtTag;

    RadioGroup rgMAinTest;
    RadioButton rbMainTest;

    RadioGroup rgEth;
    RadioButton rbEth;

    List<String> Type;

    int rgMAinTestID=0;
    int rgEthID=0;

    String nameT=null;
    String networkS=null;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ReplaceUserFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ReplaceUserFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ReplaceUserFragment newInstance(String param1, String param2) {
        ReplaceUserFragment fragment = new ReplaceUserFragment();
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
        View view=inflater.inflate(R.layout.fragment_replace_user, container, false);

        btnUpdate=view.findViewById(R.id.btnUpdate);
        edtname=view.findViewById(R.id.edtNewName);
        edtAddress=view.findViewById(R.id.edtRAddress);
        edtOldPayString=view.findViewById(R.id.edtOldName);


        spinnerNetwork=view.findViewById(R.id.spinnerNetworkR);
        cvTestMainnet=view.findViewById(R.id.CvMainTestR);
        cvEth=view.findViewById(R.id.CvEthR);
        rgMAinTest=view.findViewById(R.id.RGMainTestR);
        rgEth=view.findViewById(R.id.RGEthR);

        cvReplace=view.findViewById(R.id.CvR);
        txtPID=view.findViewById(R.id.txtPIDR);
        txtNetwork=view.findViewById(R.id.txtNetworkR);
        txtEnv=view.findViewById(R.id.txtEnvR);
        txtAddress=view.findViewById(R.id.txtAddressR);
        txtTag=view.findViewById(R.id.txtTagR);

        String [] Networks=new String[]{
                "Choose your Network","XRPL","INTERLEDGER","BTC","ETH"
        };

        Type= new ArrayList<>();
        Type.add("Choose your Network");
        Type.add("XRPL");
        Type.add("INTERLEDGER");
        Type.add("BTC");
        Type.add("ETH");

        ArrayAdapter<String> adapter=new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item,Type);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerNetwork.setAdapter(adapter);
        spinnerNetwork.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                networkS=Type.get(i);

                if(networkS.equalsIgnoreCase("XRPL")){
                    cvTestMainnet.setVisibility(View.VISIBLE);
                    edtTag.setVisibility(View.VISIBLE);
                    rgMAinTestID=rgMAinTest.getCheckedRadioButtonId();
                    rbMainTest=view.findViewById(rgMAinTestID);
                    cvEth.setVisibility(View.GONE);

                } else if(!networkS.equalsIgnoreCase("ETH")){
                    cvTestMainnet.setVisibility(View.VISIBLE);
                    edtTag.setVisibility(View.GONE);
                    rgMAinTestID=rgMAinTest.getCheckedRadioButtonId();
                    rbMainTest=view.findViewById(rgMAinTestID);
                    cvEth.setVisibility(View.GONE);



                }else{
                    cvTestMainnet.setVisibility(View.GONE);
                    edtTag.setVisibility(View.GONE);
                    rgEthID=rgEth.getCheckedRadioButtonId();
                    rbEth=view.findViewById(rgEthID);
                    cvEth.setVisibility(View.VISIBLE);


                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        rgMAinTest.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.RBTestnetR:

                        rgMAinTestID=rgMAinTest.getCheckedRadioButtonId();
                        rbMainTest=view.findViewById(rgMAinTestID);
                        nameT=rbMainTest.getText().toString();
                        Toast.makeText(getContext(),nameT,Toast.LENGTH_SHORT).show();


                        break;
                    case R.id.RBMainnetR:

                        rgMAinTestID=rgMAinTest.getCheckedRadioButtonId();
                        rbMainTest=view.findViewById(rgMAinTestID);
                        nameT=rbMainTest.getText().toString();
                        Toast.makeText(getContext(),nameT,Toast.LENGTH_SHORT).show();

                        break;
                }

            }
        });

        rgEth.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.RBRinkebyR:
                        rgEthID=rgEth.getCheckedRadioButtonId();
                        rbEth=view.findViewById(rgEthID);
                        nameT=rbEth.getText().toString();
                        Toast.makeText(getContext(),nameT,Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.RBRopstenR:
                        rgEthID=rgEth.getCheckedRadioButtonId();
                        rbEth=view.findViewById(rgEthID);
                        nameT=rbEth.getText().toString();
                        Toast.makeText(getContext(),nameT,Toast.LENGTH_SHORT).show();

                        break;
                    case R.id.RBKovanR:
                        rgEthID=rgEth.getCheckedRadioButtonId();
                        rbEth=view.findViewById(rgEthID);
                        nameT=rbEth.getText().toString();
                        Toast.makeText(getContext(),nameT,Toast.LENGTH_SHORT).show();

                        break;
                    case R.id.RBMAinnetER:
                        rgEthID=rgEth.getCheckedRadioButtonId();
                        rbEth=view.findViewById(rgEthID);
                        nameT=rbEth.getText().toString();
                        Toast.makeText(getContext(),nameT,Toast.LENGTH_SHORT).show();

                        break;
                }
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newPay=edtname.getText().toString();
                String oldPay=edtOldPayString.getText().toString();
             String address=edtAddress.getText().toString();


                PayDetails payDetails=new PayDetails();
                Address address1=new Address();
                Details details=new Details();
                details.setAddress(address);
                address1.setEnvironment(nameT);
                address1.setPaymentNetwork(networkS);
                address1.setDetails(details);
                payDetails.setPayId(newPay+"$ipixelinfinito.sandbox.paystring.org");
                payDetails.setAddresses(Collections.singletonList(address1));

                updateUserService= RestClient.getClient().create(UpdateUser.class);
                detailsCall=updateUserService.getUpdate(oldPay+"$ipixelinfinito.sandbox.paystring.org");
                detailsCall.enqueue(new Callback<PayDetails>() {
                    @Override
                    public void onResponse(Call<PayDetails> call, Response<PayDetails> response) {
                        if(response.isSuccessful()){
                            PayDetails payDetails1=response.body();

                            cvReplace.setVisibility(View.VISIBLE);
                            txtPID.setText(payDetails1.getPayId());
                            txtNetwork.setText(payDetails1.getAddresses().get(0).getPaymentNetwork());
                            txtEnv.setText(payDetails1.getAddresses().get(0).getEnvironment());
                            txtAddress.setText(payDetails1.getAddresses().get(0).details.getAddress());


                        }else{
                            Toast.makeText(getContext(),"please, verify all the details Entered Correctly seen like there is a mistake on your details, and try again",Toast.LENGTH_LONG).show();

                        }
                    }

                    @Override
                    public void onFailure(Call<PayDetails> call, Throwable t) {
                        Toast.makeText(getContext(),"Ops! there is a problem with the connection, please check your connection and try again ",Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        return view;
    }
}