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
import com.ramossoft.ipixelinfinito.Service.CreateUser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CreateUserFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CreateUserFragment extends Fragment {
    CreateUser createUserService;
    Call<PayDetails> PayCall;
    Button btnSave;
    EditText edtPayString;
    EditText edtAddress;
    EditText edtTag;
    Spinner spinnerNetwork;
    CardView cvTestMainnet;
    CardView cvEth;

    CardView cvSuccess;
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

    public CreateUserFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CreateUserFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CreateUserFragment newInstance(String param1, String param2) {
        CreateUserFragment fragment = new CreateUserFragment();
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
        View view=inflater.inflate(R.layout.fragment_create_user, container, false);
        btnSave=view.findViewById(R.id.btnSave);

        edtPayString =view.findViewById(R.id.editPayString);
        edtAddress=view.findViewById(R.id.editAddress);



        spinnerNetwork=view.findViewById(R.id.spinnerNetwork);

        cvTestMainnet=view.findViewById(R.id.CvMainTestInfo);
        cvEth=view.findViewById(R.id.CvEthInfo);

        rgMAinTest=(RadioGroup)view.findViewById(R.id.RGMainTestInfo);


        rgEth=view.findViewById(R.id.RGEthInfo);

        cvSuccess=view.findViewById(R.id.CvSuccess);
        txtPID=view.findViewById(R.id.txtPIDC);
        txtNetwork=view.findViewById(R.id.txtNetworkC);
        txtEnv=view.findViewById(R.id.txtEnvC);
        txtAddress=view.findViewById(R.id.txtAddressC);
        txtTag=view.findViewById(R.id.txtTagC);




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


                }else if(!networkS.equalsIgnoreCase("ETH")){
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
                    case R.id.RBTestnetInfo:

                    rgMAinTestID=rgMAinTest.getCheckedRadioButtonId();
                    rbMainTest=view.findViewById(rgMAinTestID);
                        nameT=rbMainTest.getText().toString();


                        break;
                    case R.id.RBMainnetInfo:

                    rgMAinTestID=rgMAinTest.getCheckedRadioButtonId();
                    rbMainTest=view.findViewById(rgMAinTestID);
                    nameT=rbMainTest.getText().toString();

                        break;
                }

            }
        });

        rgEth.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.RBRinkebyInfo:
                        rgEthID=rgEth.getCheckedRadioButtonId();
                        rbEth=view.findViewById(rgEthID);
                        nameT=rbEth.getText().toString();
                        break;
                    case R.id.RBRopstenInfo:
                        rgEthID=rgEth.getCheckedRadioButtonId();
                        rbEth=view.findViewById(rgEthID);
                        nameT=rbEth.getText().toString();

                        break;
                    case R.id.RBKovanInfo:
                        rgEthID=rgEth.getCheckedRadioButtonId();
                        rbEth=view.findViewById(rgEthID);
                        nameT=rbEth.getText().toString();

                        break;
                    case R.id.RBMAinnetEInfo:
                        rgEthID=rgEth.getCheckedRadioButtonId();
                        rbEth=view.findViewById(rgEthID);
                        nameT=rbEth.getText().toString();

                        break;
                }
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String payString= edtPayString.getText().toString();
                String address=edtAddress.getText().toString();
                String tag=edtTag.getText().toString();



                PayDetails payDetails=new PayDetails();
                Address address1=new Address();
                Details details=new Details();
                details.setAddress(address);
                address1.setEnvironment(nameT);
                address1.setPaymentNetwork(networkS);
                address1.setDetails(details);

                payDetails.setPayId(payString+"$ipixelinfinito.sandbox.paystring.org");
                payDetails.setAddresses(Collections.singletonList(address1));

                createUserService= RestClient.getClient().create(CreateUser.class);
                PayCall=createUserService.getDetails(payDetails);
                PayCall.enqueue(new Callback<PayDetails>() {
                    @Override
                    public void onResponse(Call<PayDetails> call, Response<PayDetails> response) {
                        if(response.isSuccessful()){
                            PayDetails payDetails1=response.body();
                            cvSuccess.setVisibility(View.VISIBLE);
                            txtPID.setText("PayString ID: "+payDetails1.getPayId());
                            txtNetwork.setText("Network: "+payDetails1.getAddresses().get(0).getPaymentNetwork());
                            txtEnv.setText("Environment: "+payDetails1.getAddresses().get(0).getEnvironment());
                            txtAddress.setText("Address: "+payDetails1.getAddresses().get(0).getDetails().getAddress());
                        }else {
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