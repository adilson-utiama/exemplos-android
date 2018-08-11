package com.asuprojects.webserviceretrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CepService {

    @GET("{cep}/json/")
    Call<Cep> buscarCep(@Path("cep") String cep);

    @GET("{cep}/{formato}")
    Call<Cep> buscarCep(@Path("cep") String cep, @Path("formato") String formato);
}
