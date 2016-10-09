// $Id$
package com.gt.mesd.integration;

import java.io.IOException;
import java.util.Iterator;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import com.gt.mesd.controller.IncidentController;
import com.gt.mesd.controller.RequestController;
import com.gt.mesd.controller.TaskController;
import com.manageengine.servicedesk.actionplugin.executor.DefaultActionInterface;
import com.manageengine.servicedesk.actionplugin.executor.ExecutorData;
import com.manageengine.servicedesk.utils.CommonUtil;


public class SocialITPlusIntegration extends DefaultActionInterface {
	private static final String KEY_LEVEL = "LEVEL";
	private static final String REQUEST = "Request";
	private static final String INCIDENT = "Incident";
	private static final String TASK = "Task";
	
	private static final Logger LOGGER = Logger.getLogger(SocialITPlusIntegration.class.getName());
    @Override
    public JSONObject execute(ExecutorData data) throws Exception {

    	//JSONObject dataJSON2 = data.getDataJSON();
        JSONObject resultJson = data.getDataJSON();
       
        /*String level = resultJson.getString(KEY_LEVEL);
        switch(level){
        	case REQUEST:
        		RequestController requestController = new RequestController();
        		requestController.makeRequest(resultJson);
        		break;
        	case INCIDENT:
        		IncidentController incidentController = new IncidentController();
        		incidentController.makeRequest(resultJson);
        		break;
        	case TASK:
        		TaskController taskController = new TaskController();
        		taskController.makeRequest(resultJson);
        		break;
        }*/
        
        

        
        HttpClient client = HttpClientBuilder.create().build();
		
		HttpGet httpGet = new HttpGet("http://10.1.1.210:8080/spring/rest/emps");
		HttpResponse response;
		try {
			response = client.execute(httpGet);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
       /* String result = null;
        String menuName = null;
        String xmlPath = System.getProperty("user.dir") + File.separator + ".." + File.separator + "integration" + File.separator + "conf" + File.separator + "Social_IT_Plus.xml";// No I18N
        
        HashMap additionalProps = data. );
        
        if(additionalProps != null  && !additionalProps.isEmpty()){
            menuName = (String)additionalProps.get("MENUNAME");
        }
        
        if(menuName != null ){
            Document socialITDoc = CommonUtil.getInstance().getXmlDocument(xmlPath);
            String findQuery = "menus>menu#name="+menuName+">request";// No I18N
            JSONObject configurationJson = getXMLConfigurations(socialITDoc,findQuery);
            
            String url = (String) configurationJson.get("url");
            String apiKey = (String) configurationJson.get("apikey");
            
            JSONObject dataJson = data.getHtmlDataJson();

            String action = dataJson.getString("OPERATION");// (String) request.getParameter("action");
            
            if ("ADD_POST".equals(action)) 
            {
                String content = dataJson.getString("POST_CONTENT");
                content = java.net.URLEncoder.encode(content, "UTF-8");//NO I18N
                        
                url = url + "api/json/dashboard/addPost";// No I18N
                String post_content = "apiKey="+apiKey+"&post="+content;// No I18N

                result = CommonUtil.getInstance().getResponseObjectFromURL(url, "POST", post_content);//NO I18N

            }
        }*/
      /*  
        try {
            resultJson.put("message", new JSONObject(result));
        } catch (Exception e) {
            try {
                resultJson.put("message", new JSONArray(result));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }*/
		System.out.println("HELLO######################" + resultJson);
        return resultJson;
    }
    public JSONObject getXMLConfigurations(Document doc, String xmlQuery) throws JSONException {

        JSONObject configurationJson = new JSONObject();
        try {
            Node node = CommonUtil.getInstance().findElement(doc, xmlQuery);
            Node childnode = node.getFirstChild();

            while (childnode != null) {
                if (childnode.getNodeType() == Node.ELEMENT_NODE) {
                    if (childnode.getNodeName().equals("url")) {
                        configurationJson.put("url", childnode.getTextContent().trim());
                    } else if (childnode.getNodeName().equals("apikey")) {
                        configurationJson.put("apikey", childnode.getTextContent().trim());
                    } 
                }
                childnode = childnode.getNextSibling();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return configurationJson;
    }

    
}
