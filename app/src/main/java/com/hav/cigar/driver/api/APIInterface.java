package com.hav.cigar.driver.api;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.hav.cigar.driver.model.Data;

import com.hav.cigar.driver.model.Datum;
import com.hav.cigar.driver.model.Example;
import com.hav.cigar.driver.model.LoginData;
import com.hav.cigar.driver.model.MobileNoExists;
import com.hav.cigar.driver.model.SendOtp;
import com.hav.cigar.driver.model.VerifyOtp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIInterface {
    @GET("/status")
    Call<JsonObject> getStatus(@Header("Authorization") String authHeader);
    @GET("/status")
    Call<JsonObject> getStatus();




    // Login Responce API
    // Link POST: https://piersoncigars.com/api/driver_api.php?apicall=driver_login

    @FormUrlEncoded
    @POST("driver_api.php?apicall=driver_login")
    Call<LoginData> getLoginInfo(@Field("mob") String mob);


        // -- Calling POST API to getDriverInfo() method
    // Link : https://piersoncigars.com/api/driver_api.php?apicall=get_driver
    // Body Parameter Ex: id=3
    @FormUrlEncoded
    @POST("driver_api.php?apicall=get_driver")
    Call<Example> getDriverInfo(@Field("id") String id);


    //-- Check Mobile No Exists
    //https://piersoncigars.com/api/driver_api.php?apicall=check_mobno_exists
    //Method : post
    //params : mob, token
    @FormUrlEncoded
    @POST("driver_api.php?apicall=check_mobno_exists")
    Call<MobileNoExists> mobileNoExists(@Field("mob")String mob,
                                        @Field("token") String token);




    //---- Sending OTP
    // https://piersoncigars.com/api/driver_api.php?apicall=send_otp
    // method :post
    //params : mob, id
    @FormUrlEncoded
    @POST("driver_api.php?apicall=send_otp")
    Call<SendOtp> sendOtp(@Field("mob")String mob,
                          @Field("id") String id);

    // ---- Verifying OTP
    //https://piersoncigars.com/api/driver_api.php?apicall=verify_opt
    //method :post
    //params : otp, id
    @FormUrlEncoded
    @POST("driver_api.php?apicall=verify_opt")
    Call<VerifyOtp> verifyOtp(@Field("otp")String otp,
                            @Field("id") String id);


//    {
//        "status": "200",
//            "data": {
//        "message": "success",
//                "id": "3",
//                "full_name": "Aditya",
//                "dob": "2020-04-28",
//                "email_id": "ad@gmail.com",
//                "mob": "12345678187",
//                "address_1": "Ad1",
//                "address_2": "Ad2",
//                "city": "Hyderabad",
//                "state": "Telengana",
//                "pin_code": "500038",
//                "country": "inadia",
//                "driver_id": "dd01",
//                "driver_licence": "cigar.jpeg"
//    }
//    }

   // @GET("posts/{id}/comments")
    //Call<List<Comment>> getComments(@Path("id") int postId);
   // @GET("api/")
    // Call<Example> getRecepies(@Query("i") String i, @Query("q") String q,@Query("p") String p);


    //@FormUrlEncoded // annotation used in POST type requests
    //@POST("/retrofit/register.php")
        // API's endpoints
    //Call<SignUpResponse> registration(@Field("name") String name,
                                     // @Field("email") String email,
                                      //@Field("password") String password,
                                     // @Field("logintype") String logintype);

    // In registration method @Field used to set the keys and String data type is representing its a string type value and callback is used to get the response from api and it will set it in our POJO class

}
