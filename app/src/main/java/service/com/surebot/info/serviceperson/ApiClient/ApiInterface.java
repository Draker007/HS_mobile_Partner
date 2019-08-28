package service.com.surebot.info.serviceperson.ApiClient;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import service.com.surebot.info.serviceperson.RequestClass.Account_details_Request;
import service.com.surebot.info.serviceperson.RequestClass.AddAboutme_Request;
import service.com.surebot.info.serviceperson.RequestClass.Add_account_details_Request;
import service.com.surebot.info.serviceperson.RequestClass.Category_List_Request;
import service.com.surebot.info.serviceperson.RequestClass.ChangePasswordRequest;
import service.com.surebot.info.serviceperson.RequestClass.CustomerSupport_Request;
import service.com.surebot.info.serviceperson.RequestClass.DeleteProfilePicRequest;
import service.com.surebot.info.serviceperson.RequestClass.ListOfServices_Request;
import service.com.surebot.info.serviceperson.RequestClass.ListOfSubServices_Request;

import service.com.surebot.info.serviceperson.RequestClass.ListofFaqs_Request;
import service.com.surebot.info.serviceperson.RequestClass.NewRequestListDetails_Request;
import service.com.surebot.info.serviceperson.RequestClass.NewRequestList_Request;

import service.com.surebot.info.serviceperson.RequestClass.Notification_Request;

import service.com.surebot.info.serviceperson.RequestClass.PartnerProfileRequest;
import service.com.surebot.info.serviceperson.RequestClass.PartnerSigup_Request;
import service.com.surebot.info.serviceperson.RequestClass.PartnerStartService_Request;
import service.com.surebot.info.serviceperson.RequestClass.Partner_my_task_today_Request;
import service.com.surebot.info.serviceperson.RequestClass.Partner_package_Request;
import service.com.surebot.info.serviceperson.RequestClass.Partner_payment_Request;
import service.com.surebot.info.serviceperson.RequestClass.Partner_set_new_password_Request;
import service.com.surebot.info.serviceperson.RequestClass.Partner_set_new_password_otp_Request;
import service.com.surebot.info.serviceperson.RequestClass.Partnerlogin_Request;
import service.com.surebot.info.serviceperson.RequestClass.About_me_Request;
import service.com.surebot.info.serviceperson.RequestClass.PaymentReceived_Request;
import service.com.surebot.info.serviceperson.RequestClass.Payment_completed_transaction_Request;
import service.com.surebot.info.serviceperson.RequestClass.Select_service_partner_Request;

import service.com.surebot.info.serviceperson.RequestClass.Send_otp_mail_Request;
import service.com.surebot.info.serviceperson.RequestClass.UpcomingRequestList_Request;


import service.com.surebot.info.serviceperson.RequestClass.Send_otp_mail_Request;

import service.com.surebot.info.serviceperson.RequestClass.UpcomingRequestList_Request;


import service.com.surebot.info.serviceperson.ResponseClass.AboutUs_Response;
import service.com.surebot.info.serviceperson.ResponseClass.Account_details_Response;
import service.com.surebot.info.serviceperson.ResponseClass.AddAboutme_Response;
import service.com.surebot.info.serviceperson.ResponseClass.Add_account_details_Response;
import service.com.surebot.info.serviceperson.ResponseClass.Add_partner_personal_details_Response;
import service.com.surebot.info.serviceperson.ResponseClass.Awards_and_CertificateResponse;
import service.com.surebot.info.serviceperson.ResponseClass.CancelledRequestList_Response;
import service.com.surebot.info.serviceperson.ResponseClass.Category_List_Response;
import service.com.surebot.info.serviceperson.ResponseClass.ChangePasswordResponse;
import service.com.surebot.info.serviceperson.ResponseClass.CompletedRequestList_Response;
import service.com.surebot.info.serviceperson.ResponseClass.CustomerSupport_Response;
import service.com.surebot.info.serviceperson.ResponseClass.DeleteProfilePicResponse;
import service.com.surebot.info.serviceperson.ResponseClass.EditPersonalPhotoResponse;
import service.com.surebot.info.serviceperson.ResponseClass.Identity_verification_Response;
import service.com.surebot.info.serviceperson.ResponseClass.ListOfServices_Response;
import service.com.surebot.info.serviceperson.ResponseClass.ListOfSubServices_Response;

import service.com.surebot.info.serviceperson.ResponseClass.ListofFaqs_Response;
import service.com.surebot.info.serviceperson.ResponseClass.NewRequestListDetails_Response;
import service.com.surebot.info.serviceperson.ResponseClass.NewRequestList_Response;

import service.com.surebot.info.serviceperson.ResponseClass.Notification_Response;

import service.com.surebot.info.serviceperson.ResponseClass.PartnerProfileResponse;
import service.com.surebot.info.serviceperson.ResponseClass.PartnerSignup_Response;
import service.com.surebot.info.serviceperson.ResponseClass.PartnerStartService_Response;
import service.com.surebot.info.serviceperson.ResponseClass.Partner_my_task_today_response;
import service.com.surebot.info.serviceperson.ResponseClass.Partner_package_Response;
import service.com.surebot.info.serviceperson.ResponseClass.Partner_payment_Response;
import service.com.surebot.info.serviceperson.ResponseClass.Partner_set_new_password_Response;
import service.com.surebot.info.serviceperson.ResponseClass.Partner_set_new_password_otp_Response;
import service.com.surebot.info.serviceperson.ResponseClass.Partnerlogin_Response;
import service.com.surebot.info.serviceperson.ResponseClass.About_me_Response;
import service.com.surebot.info.serviceperson.ResponseClass.PaymentReceived_Response;
import service.com.surebot.info.serviceperson.ResponseClass.Payment_completed_transaction_Response;
import service.com.surebot.info.serviceperson.ResponseClass.Select_service_partner_Response;


import service.com.surebot.info.serviceperson.ResponseClass.Send_otp_mail_Response;

import service.com.surebot.info.serviceperson.ResponseClass.UpcomingRequestList_Response;



import service.com.surebot.info.serviceperson.ResponseClass.Send_otp_mail_Response;
import service.com.surebot.info.serviceperson.ResponseClass.UpcomingRequestList_Response;

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


    //Add About me
    @POST("add_about_me")
    Call<AddAboutme_Response> add_Aboutme (@Body AddAboutme_Request request);

  //Get About me
    @POST("about_me")
    Call<About_me_Response> getAboutme (@Body About_me_Request request);

    @POST("add_partner_personal_details")
    Call<Add_partner_personal_details_Response>  add_personal_details (@Body RequestBody requestBody);

    @POST("edit_photo_personal_details")
    Call<EditPersonalPhotoResponse>  add_personal_photo  (@Body RequestBody requestBody);

    @POST("partner_change_password")
    Call<ChangePasswordResponse> ChangePassword (@Body ChangePasswordRequest request);

    @POST("partner_account_details")
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

    //Payment
    @POST("partner_pay_completed")
    Call<Partner_payment_Response> CompletedPayment (@Body Partner_payment_Request request);


    @POST("partner_pay_pending")
    Call<Partner_payment_Response> PendingPayment (@Body Partner_payment_Request request);
   //For Getting List OfTodays Task
    @POST("partner_my_task_today")
    Call<Partner_my_task_today_response> Get_TodaysTaskList (@Body Partner_my_task_today_Request request);

    //For Getting Code
    @POST("partner_start_service")
    Call<PartnerStartService_Response> Get_partner_start_servicecode (@Body PartnerStartService_Request request);


    //ResetPassword
    @POST("partner_set_new_password_otp")
    Call<Partner_set_new_password_otp_Response> ResetPassOTP(@Body Partner_set_new_password_otp_Request request);

    @POST("send_otp_mail")
    Call<Send_otp_mail_Response> SendMailGetOTP (@Body Send_otp_mail_Request request);


    @POST("partner_set_new_password")
    Call<Partner_set_new_password_Response> SetNew_Password (@Body Partner_set_new_password_Request request);






    //4 Request Lists

    //New Request Lists

        @POST("services_requests_new")
        Call<NewRequestList_Response>  get_NewServiceRequestList  (@Body NewRequestList_Request request);

    //New Request Details
        @POST("servicesrequests_new_details")
        Call<NewRequestListDetails_Response>  get_NewServiceRequestDetails  (@Body NewRequestListDetails_Request request);


        //Get Upcoming Service Request List
        @POST("servicesrequests_upcoming_details")
        Call<UpcomingRequestList_Response>  get_UpcomingServiceRequestList  (@Body UpcomingRequestList_Request request);



        //Get Completed Service Request List
        @POST("servicesrequests_completed_details")
        Call<CompletedRequestList_Response>  get_CompletedServiceRequestList  (@Body UpcomingRequestList_Request request);

    //Get Cancelled Service Request List
        @POST("servicesrequests_cancelled_details")
        Call<CancelledRequestList_Response>  get_CancelledServiceRequestList  (@Body UpcomingRequestList_Request request);



        //Get Payment Received Details

    @POST("payment_confirmation")
    Call<PaymentReceived_Response>  get_PaymentReceivedDetails  (@Body PaymentReceived_Request request);

    //Get List Of FAQS

    @POST("listing_faqs")
    Call<ListofFaqs_Response>  get_FaqList  (@Body ListofFaqs_Request request);

    //Get Customer Support Details

    @POST("support_page")
    Call<CustomerSupport_Response>  get_CustomerSupportDetails  (@Body CustomerSupport_Request request);

    // Add About Us
    @POST("about_us")
    Call<AboutUs_Response> Get_AboutUs (@Body About_me_Request request);

}
