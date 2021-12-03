package com.ramossoft.ipixelinfinito.Service;

import com.ramossoft.ipixelinfinito.Model.PayDetails;

import retrofit2.Call;
import retrofit2.http.Headers;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface UpdateUser {
    @Headers({"PayID-API-Version: 2020-06-18",
            "Authorization: Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6ImM0MTIwYzE5ODlhNWQzNmQxZjAxODQxYjM5YTVmNjE3NTM4ZTk2M2I1NDFmMTBmODRmYjExNzQ5YjEzOTQ3YjkifQ.eyJqdGkiOiJjNGFlNTIyYi1jNmY1LTRmMzEtODFhYi0zMWY0MDIwYjFlNjMiLCJzdWIiOiJlODBlMjgwNC1hMDc2LTQ4MWUtYjQ0MS04YmViZGQ3Nzc0ZWIiLCJpZCI6ImU4MGUyODA0LWEwNzYtNDgxZS1iNDQxLThiZWJkZDc3NzRlYiIsImdpdGh1YklkIjoiODE3NzIwMTkiLCJzYW5kYm94SWQiOiJkZWFjMGQzYy04MTVlLTQ5ODUtYjA1Ni02NzhhZjk2OWVhNWUiLCJhdXRoVHlwZSI6InRva2VuIiwiaWF0IjoxNjM4MDkyNjY3LCJhdWQiOiJodHRwczovL3BheXN0cmluZy5vcmcvc2FuZGJveCIsImlzcyI6Imh0dHBzOi8vcGF5c3RyaW5nLm9yZy9zYW5kYm94LyJ9.FBqApSIHSZzNFnTaE4cP6S5zXfrVH70R3f6uby7_8MRDWWLedVR2JkAlleZn-Xp1CHfaFIiQ11XdIX7cfAX39MmEd405prL-r4mV3FVC3EdbaWWQXNx-0v1sVgLC54eUycMK8GVp4wEQScMaBqKqvIpFaR0aocdRA7UdS-wVxsNhguL0rehP7bpoeMb_ZNnPyv4deV0LtFAnT09_UpqtZrfyIhv0kuj8iQpIWmaqNpUVrjV9tdla3PRL1Rqbhxc5cDjmnrgvabe7ub041v_DTj28yTa-C5ozRCOfBO9WHaL-MZA-dUTL5FpplCAWagfcT88xIqvcvpg3hhihoHkPFZa6rgj0-eujVmIrqKDNR9liLefrNHEobKbNGGQulwlzQ8B94hPLuf5vi01Awe1gMpdzjci35rl0jeQ7vZYpcUjpMT6arCmSrIlbXJvtMG592ydNtuDzCqhot6F4jSGKErAkkcPQovEn6nZ58hPerwf19XH9ZOUBXE1plBqXbD-olZqXVWF1-I8bSS3I64iroYj4OZjhpDas7VPlIvzTwXGDdXkENwQSR8oIPiexTRE83aI5dEbOg5EVSaiSyQo_JJ8xpmfu62xE3fbRkY7dNPtkfpVA66HHYWtnsYcpfXTgIMp-XMV6k0r6uFLo6UHqOJ6rr43C_zEjO-dOxkqWjPM",
            "Content-Type: application/json"
    })

   // @PUT("users/ETH3$ipixelinfinito.sandbox.paystring.org")
    @PUT("users/{OldPayString}")
    Call<PayDetails> getUpdate(@Path("OldPayString") String oldPayString);
}
