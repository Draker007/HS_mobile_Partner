package service.com.surebot.info.serviceperson.ResponseClass;

public class GetDocumentslist_Response {
    GetDocumentslist_Records[]  verification_document_details_response;

    private  String DocumentHint= "Select documents";

    public String getDocumentHint() {
        return DocumentHint;
    }

    public void setDocumentHint(String documentHint) {
        DocumentHint = documentHint;
    }

    public GetDocumentslist_Records[] getVerification_document_details_response() {
        return verification_document_details_response;
    }

    public void setVerification_document_details_response(GetDocumentslist_Records[] verification_document_details_response) {
        this.verification_document_details_response = verification_document_details_response;
    }

    public class GetDocumentslist_Records {
        private String Document_Category_ID;

        private String Document_Category_Name;



        public String getDocument_Category_ID() {
            return Document_Category_ID;
        }

        public void setDocument_Category_ID(String document_Category_ID) {
            Document_Category_ID = document_Category_ID;
        }

        public String getDocument_Category_Name() {
            return Document_Category_Name;
        }

        public void setDocument_Category_Name(String document_Category_Name) {
            Document_Category_Name = document_Category_Name;
        }
    }
}
