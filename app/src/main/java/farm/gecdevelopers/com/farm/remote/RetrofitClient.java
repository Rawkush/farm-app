package farm.gecdevelopers.com.farm.remote;

import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitClient {


    private static Retrofit retrofitClient = null;

    public static Retrofit getClient(String baseUrl) {

        if (retrofitClient == null) {

            retrofitClient = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .build();
        }

        return retrofitClient;
    }


}
