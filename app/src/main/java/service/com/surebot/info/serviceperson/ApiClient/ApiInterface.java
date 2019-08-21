package service.com.surebot.info.serviceperson.ApiClient;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import service.com.surebot.info.serviceperson.RequestClass.Account_details_Request;
import service.com.surebot.info.serviceperson.RequestClass.Add_account_details_Request;
import service.com.surebot.info.serviceperson.RequestClass.Category_List_Request;
import service.com.surebot.info.serviceperson.RequestClass.ChangePasswordRequest;
import service.com.surebot.info.serviceperson.RequestClass.DeleteProfilePicRequest;
import service.com.surebot.info.serviceperson.RequestClass.ListOfServices_Request;
import service.com.surebot.info.serviceperson.RequestClass.ListOfSubServices_Request;
import service.com.surebot.info.serviceperson.RequestClass.Notification_Request;
import service.com.surebot.info.serviceperson.RequestClass.PartnerProfileRequest;
import service.com.surebot.info.serviceperson.RequestClass.PartnerSigup_Request;
import service.com.surebot.info.serviceperson.RequestClass.Partner_package_Request;
import service.com.surebot.info.serviceperson.RequestClass.Partner_payment_Request;
import service.com.surebot.info.serviceperson.RequestClass.Partnerlogin_Request;
import service.com.surebot.info.serviceperson.RequestClass.About_me_Request;
import service.com.surebot.info.serviceperson.RequestClass.Payment_completed_transaction_Request;
import service.com.surebot.info.serviceperson.RequestClass.Select_service_partner_Request;
import service.com.surebot.info.serviceperson.ResponseClass.Account_details_Response;
import service.com.surebot.info.serviceperson.ResponseClass.Add_account_details_Response;
import service.com.surebot.info.serviceperson.ResponseClass.Add_partner_personal_details_Response;
import service.com.surebot.info.serviceperson.ResponseClass.Awards_and_CertificateResponse;
import service.com.surebot.info.serviceperson.ResponseClass.Category_List_Response;
import service.com.surebot.info.serviceperson.ResponseClass.ChangePasswordResponse;
import service.com.surebot.info.serviceperson.ResponseClass.DeleteProfilePicResponse;
import service.com.surebot.info.serviceperson.ResponseClass.EditPersonalPhotoResponse;
import service.com.surebot.info.serviceperson.ResponseClass.Identity_verification_Response;
import service.com.surebot.info.serviceperson.ResponseClass.ListOfServices_Response;
import service.com.surebot.info.serviceperson.ResponseClass.ListOfSubServices_Response;
import service.com.surebot.info.serviceperson.ResponseClass.Notification_Response;
import service.com.surebot.info.serviceperson.ResponseClass.PartnerProfileResponse;
import service.com.surebot.info.serviceperson.ResponseClass.PartnerSignup_Response;
import service.com.surebot.info.serviceperson.ResponseClass.Partner_package_Response;
import service.com.surebot.info.serviceperson.ResponseClass.Partner_payment_Response;
import service.com.surebot.info.serviceperson.ResponseClass.Partnerlogin_Response;
import service.com.surebot.info.serviceperson.ResponseClass.About_me_Response;
import service.com.surebot.info.serviceperson.ResponseClass.Payment_completed_transaction_Response;
import service.com.surebot.info.serviceperson.ResponseClass.Select_service_partner_Response;

public interface ApiInterface {

    //User List Of Services

    @POST("list_services")
    Call<ListOfServices_Response> Services_list(@Body ListOfServices_Request request);


    @POST("list_sub_services")
    Call<ListOfSubServices_Response> SubServices_list(@Body ListOfSubServices_Request request);

    //Log in And Sign UP
    @POST("partner_login")
    Call<Partnerlogin_Response> partnerlogin(@Body Partnerlogin_Request request);

    @POST("partner_signup")
    Call<PartnerSignup_Response> partnerSignup (@Body PartnerSigup_Request request);


    @POST("list_category")
    Call<Category_List_Response> categoryList(@Body Category_List_Request request);


    //Profile

    @POST("partner_profile")
    Call<PartnerProfileResponse> profileDetails(@Body PartnerProfileRequest request);


    @POST("about_me")
    Call<About_me_Response> getAboutme (@Body About_me_Request request);

    @POST("add_partner_personal_details")
    Call<Add_partner_personal_details_Response>  add_personal_details (@Body RequestBody requestBody);

    @POST("edit_photo_partner_personal_details")
    Call<EditPersonalPhotoResponse>  add_personal_photo  (@Body RequestBody requestBody);

    @POST("partner_change_password")
    Call<ChangePasswordResponse> ChangePassword (@Body ChangePasswordRequest request);

    @POST("account_details")
    Call<Account_details_Response> AccountDetails (@Body Account_details_Request request);

    @POST("delete_photo_personal_details")
    Call<DeleteProfilePicResponse> DeleteProfilePic (@Body DeleteProfilePicRequest request);

    @POST("partner_payment_completed_transaction")
    Call<Payment_completed_transaction_Response>  PaymentCompletedTransaction (@Body Payment_completed_transaction_Request request);

    @POST("identity_verification_upload_image")
    Call<Identity_verification_Response>  IdentityVerif  (@Body RequestBody Identity);

        @POST("award_and_certificate_photos_upload")
    Call<Awards_and_CertificateResponse>  AwardsAndCertificate  (@Body RequestBody Identity);

    @POST("Add_account_details")
    Call<Add_account_details_Response> Add_account_details  (@Body Add_account_details_Request request);


    //Notification API

    @POST("notification_request")
    Call<Notification_Response> NotificationCall (@Body Notification_Request request);


    //Add Service Partner And Location
    @POST("select_service_partner")
    Call<Select_service_partner_Response> SelectSeviceAndLocation (@Body Select_service_partner_Request request);


    //Premium Pacakge
    @POST("List_packages")
    Call<Partner_package_Response> List_packages (@Body Partner_package_Request request);


    //Completed Payment
    @POST("List_packages")
    Call<Partner_payment_Response> CompletedPayment (@Body Partner_payment_Request request);

}
