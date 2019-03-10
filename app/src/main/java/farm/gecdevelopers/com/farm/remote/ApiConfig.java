package farm.gecdevelopers.com.farm.remote;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiConfig {


    @Multipart
    @POST("recordact.php")
    Call<String> uploadFile(
            @Part MultipartBody.Part file,
            @Part("docfile") RequestBody name,
            @Part("act") String actId,
            @Part("loan_type") String plotId,
            @Part("date") String date,
            @Part("hect") String areaCovered,
            @Part("det") String resource,
            @Part("cmts") String comment,
            @Part("user_id") String userId

    );


}
