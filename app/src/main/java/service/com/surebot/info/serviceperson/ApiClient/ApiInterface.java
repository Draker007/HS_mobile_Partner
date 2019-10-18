package service.com.surebot.info.serviceperson.ApiClient;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import service.com.surebot.info.serviceperson.RequestClass.Account_details_Request;
import service.com.surebot.info.serviceperson.RequestClass.AddAboutme_Request;
import service.com.surebot.info.serviceperson.RequestClass.AddLocationServicesbyCity_Request;
import service.com.surebot.info.serviceperson.RequestClass.AddLocationServicesbyRadius_Request;
import service.com.surebot.info.serviceperson.RequestClass.AddLocationServicesbyState_Request;
import service.com.surebot.info.serviceperson.RequestClass.Add_account_details_Request;
import service.com.surebot.info.serviceperson.RequestClass.BuyPackage_Request;
import service.com.surebot.info.serviceperson.RequestClass.Category_List_Request;
import service.com.surebot.info.serviceperson.RequestClass.ChangePasswordRequest;
import service.com.surebot.info.serviceperson.RequestClass.CustomerSupport_Request;
import service.com.surebot.info.serviceperson.RequestClass.DeleteProfilePicRequest;
import service.com.surebot.info.serviceperson.RequestClass.GetAddressProof_Request;
import service.com.surebot.info.serviceperson.RequestClass.GetAllZipCode_Request;
import service.com.surebot.info.serviceperson.RequestClass.GetAwardsDetails_Request;
import service.com.surebot.info.serviceperson.RequestClass.GetCityList_Request;
import service.com.surebot.info.serviceperson.RequestClass.GetIdentityVerifications_Request;
import service.com.surebot.info.serviceperson.RequestClass.GetListofCountry_Request;
import service.com.surebot.info.serviceperson.RequestClass.GetStateList_Request;
import service.com.surebot.info.serviceperson.RequestClass.GetZipcodeList_Request;
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

import service.com.surebot.info.serviceperson.RequestClass.SendQuotetoUser_Request;
import service.com.surebot.info.serviceperson.RequestClass.Send_otp_mail_Request;
import service.com.surebot.info.serviceperson.RequestClass.SubmitForApproval_Request;
import service.com.surebot.info.serviceperson.RequestClass.UpcomingRequestList_Request;


import service.com.surebot.info.serviceperson.ResponseClass.AboutUs_Response;
import service.com.surebot.info.serviceperson.ResponseClass.Account_details_Response;
import service.com.surebot.info.serviceperson.ResponseClass.AddAboutme_Response;
import service.com.surebot.info.serviceperson.ResponseClass.AddLocationServicesbyCity_Response;
import service.com.surebot.info.serviceperson.ResponseClass.AddLocationServicesbyRadius_Response;
import service.com.surebot.info.serviceperson.ResponseClass.AddLocationServicesbyState_Response;
import service.com.surebot.info.serviceperson.ResponseClass.Add_account_details_Response;
import service.com.surebot.info.serviceperson.ResponseClass.Add_partner_personal_details_Response;
import service.com.surebot.info.serviceperson.ResponseClass.Awards_and_CertificateResponse;
import service.com.surebot.info.serviceperson.ResponseClass.BuyPackage_Response;
import service.com.surebot.info.serviceperson.ResponseClass.CancelledRequestList_Response;
import service.com.surebot.info.serviceperson.ResponseClass.Category_List_Response;
import service.com.surebot.info.serviceperson.ResponseClass.ChangePasswordResponse;
import service.com.surebot.info.serviceperson.ResponseClass.CompletedRequestList_Response;
import service.com.surebot.info.serviceperson.ResponseClass.CustomerSupport_Response;
import service.com.surebot.info.serviceperson.ResponseClass.DeleteProfilePicResponse;
import service.com.surebot.info.serviceperson.ResponseClass.EditPersonalPhotoResponse;
import service.com.surebot.info.serviceperson.ResponseClass.GetAddressProof_Response;
import service.com.surebot.info.serviceperson.ResponseClass.GetAllZipCode_Response;
import service.com.surebot.info.serviceperson.ResponseClass.GetAwardsDetails_Response;
import service.com.surebot.info.serviceperson.ResponseClass.GetCityList_Response;
import service.com.surebot.info.serviceperson.ResponseClass.GetStateList_Response;
import service.com.surebot.info.serviceperson.ResponseClass.GetIdentityVerifications_Response;
import service.com.surebot.info.serviceperson.ResponseClass.GetListofCountry_Response;
import service.com.surebot.info.serviceperson.ResponseClass.GetZipcodeList_Response;
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


import service.com.surebot.info.serviceperson.ResponseClass.SendQuotetoUser_Response;
import service.com.surebot.info.serviceperson.ResponseClass.Send_otp_mail_Response;

import service.com.surebot.info.serviceperson.ResponseClass.SubmitForApproval_Response;
import service.com.surebot.info.serviceperson.ResponseClass.UpcomingRequestList_Response;

public interface ApiInterface {

    //Category List in Signup
    @POST("list_category")
    Call<Category_List_Response> categoryList(@Body Category_List_Request request);

        //SignUp
    @POST("partner_signup")
    Call<PartnerSignup_Response> partnerSignup (@Body PartnerSigup_Request request);

    //Log in
    @POST("partner_login")
    Call<Partnerlogin_Response> partnerlogin(@Body Partnerlogin_Request request);

    //Add Service Partner And Location
    @POST("select_service_partner")
    Call<Select_service_partner_Response> SelectSeviceAndLocation (@Body Select_service_partner_Request request);

    //User List Of Services
    @POST("list_services")
    Call<ListOfServices_Response> Services_list(@Body ListOfServices_Request request);

   //Get List Of Sub Services List
    @POST("list_sub_service_for_partner")
    Call<ListOfSubServices_Response> SubServices_list(@Body ListOfSubServices_Request request);

//Submit For Approval

    @POST("submit_services_for_approval")
    Call<SubmitForApproval_Response> submitFor_Approval(@Body SubmitForApproval_Request request);




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


    //Payment
    @POST("partner_pay_completed")
    Call<Partner_payment_Response> CompletedPayment (@Body Partner_payment_Request request);


    @POST("partner_pay_pending")
    Call<Partner_payment_Response> PendingPayment (@Body Partner_payment_Request request);

    //In Todays Task Fragment In Home Screen

    //Premium Pacakge
    @POST("List_packages")
    Call<Partner_package_Response> List_packages (@Body Partner_package_Request request);

    //To Buy Package

    @POST("partner_buy_package")
    Call<BuyPackage_Response> buy_Package (@Body BuyPackage_Request request);

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

      //Send Quote to use

    @POST("add_request_quotation")
    Call<SendQuotetoUser_Response> sendQuote_toUser (@Body SendQuotetoUser_Request request);


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

    //Getting Address Proof
    @POST("get_address_proof_details")
    Call<GetAddressProof_Response> Get_AddressProofImages (@Body GetAddressProof_Request request);

//Getting Awards Details
@POST("get_award_and_certificate_photos_details")
Call<GetAwardsDetails_Response> Get_AwardsDetails (@Body GetAwardsDetails_Request request);

//Getting Identity Verification Details
@POST("get_identity_verification_details")
Call<GetIdentityVerifications_Response> Get_IdentityVerifications (@Body GetIdentityVerifications_Request request);

//Location Things

    //Get List Of Country
    @POST("get_country_list")
    Call<GetListofCountry_Response> Get_CountryList (@Body GetListofCountry_Request request);

    //Get List Of States
    @POST("get_states_list")
    Call<GetStateList_Response> Get_StatesList (@Body GetStateList_Request request);


    //Get List Of Cities

    @POST("get_cities_list")
    Call<GetCityList_Response> Get_CityList (@Body GetCityList_Request request);

    //Get List Of Zipcodes


    @POST("get_zipcode")
    Call<GetZipcodeList_Response> Get_ZipCodeList (@Body GetZipcodeList_Request request);

    //Get All Zipcode According to Country
    @POST("list_all_zipcode")
    Call<GetAllZipCode_Response> Get_AllZipCodeList (@Body GetAllZipCode_Request request);


    //Add Location and Services by Radius

    @POST("partner_add_location_services_radius")
    Call<AddLocationServicesbyRadius_Response> Add_LocationbyRadius (@Body AddLocationServicesbyRadius_Request request);

    //Add Location and Services by City

    @POST("partner_add_location_services_state_city")
    Call<AddLocationServicesbyCity_Response> Add_LocationbyCity (@Body AddLocationServicesbyCity_Request request);

    //Add Location and Services by City

    @POST("partner_add_location_services_state")
    Call<AddLocationServicesbyState_Response> Add_LocationbyState (@Body AddLocationServicesbyState_Request request);

}
