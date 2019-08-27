package service.com.surebot.info.serviceperson.ResponseClass;

public class ListofFaqs_Response {

    private  ListofFaqs_Records[]  faqs_response;

    public ListofFaqs_Records[] getFaqs_response() {
        return faqs_response;
    }

    public void setFaqs_response(ListofFaqs_Records[] faqs_response) {
        this.faqs_response = faqs_response;
    }

    public class ListofFaqs_Records {
         private  String  Question_ID;

        private  String  Question;

        private  String  Answer;

        private  String  Question_Status;

        public String getQuestion_ID() {
            return Question_ID;
        }

        public void setQuestion_ID(String question_ID) {
            Question_ID = question_ID;
        }

        public String getQuestion() {
            return Question;
        }

        public void setQuestion(String question) {
            Question = question;
        }

        public String getAnswer() {
            return Answer;
        }

        public void setAnswer(String answer) {
            Answer = answer;
        }

        public String getQuestion_Status() {
            return Question_Status;
        }

        public void setQuestion_Status(String question_Status) {
            Question_Status = question_Status;
        }
    }
}


