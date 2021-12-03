package com.ramossoft.ipixelinfinito.Service;

import com.ramossoft.ipixelinfinito.Model.PayDetails;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface PaymentInformation {
    @Headers({"PayID-Version: 1.0",
            "Accept: ",
            "Content-Type: text/plain"
    })
    @GET("{PayString}")
    Call<PayDetails> getInfo(@Header("Accept") String app, @Path("PayString") String paystring);
}
