import java.util.HashMap;

public class LoginPasswordData {

    //���������� ������ � ������
    HashMap<String,String> logininfo = new HashMap<String,String>();

    LoginPasswordData(){

        logininfo.put("GeneralManager","UjRTqz");
        logininfo.put("ManagerAssistant1","MmQ45g");
        logininfo.put("ManagerAssistant2","3wNKsV");
    }

    public HashMap getLoginInfo(){
        return logininfo;
    }

}
