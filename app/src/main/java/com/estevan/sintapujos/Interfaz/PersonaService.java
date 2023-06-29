package com.estevan.sintapujos.Interfaz;

import com.estevan.sintapujos.Models.Persona;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PersonaService {

    @GET("persona/{id}")
    Call<Persona> getPersona(@Path("id") int id);

}
