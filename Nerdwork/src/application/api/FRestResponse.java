package application.api;

import java.util.List;
import java.util.Map;

public class FRestResponse {
    public int statusCode;
    public String responseContent;
    public Map<String, List<String>> responseHeaders;
   
    FRestResponse(int status, String response, Map<String, List<String>> headers){
    	statusCode = status;
    	responseContent = response;
    	responseHeaders = headers;
    }
}
