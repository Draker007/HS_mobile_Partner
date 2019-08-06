package service.com.surebot.info.serviceperson.ApiClient;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import service.com.surebot.info.serviceperson.RequestClass.ListOfServices_Request;
import service.com.surebot.info.serviceperson.RequestClass.ListOfSubServices_Request;
import service.com.surebot.info.serviceperson.ResponseClass.ListOfServices_Response;
import service.com.surebot.info.serviceperson.ResponseClass.ListOfSubServices_Response;

public interface ApiInterface {

    //User List Of Services

    @POST("list_services")
    Call<ListOfServices_Response> Services_list(@Body ListOfServices_Request request);


    @POST("list_sub_services")
    Call<ListOfSubServices_Response> SubServices_list(@Body ListOfSubServices_Request request);

}
