
package cn.com.pengyue.vo.ws;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.6 in JDK 6
 * Generated source version: 2.1
 * 
 */
@javax.jws.WebService(name = "WebService", targetNamespace = "http://www.pengyue.com.cn/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface WebService {


    /**
     * 
     * @return
     *     returns java.util.List<cn.com.pengyue.vo.ws.Rule>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getRules", targetNamespace = "http://www.pengyue.com.cn/", className = "cn.com.pengyue.vo.ws.GetRules")
    @ResponseWrapper(localName = "getRulesResponse", targetNamespace = "http://www.pengyue.com.cn/", className = "cn.com.pengyue.vo.ws.GetRulesResponse")
    public List<Rule> getRules();

    /**
     * 
     * @param arg0
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "updatePage", targetNamespace = "http://www.pengyue.com.cn/", className = "cn.com.pengyue.vo.ws.UpdatePage")
    @ResponseWrapper(localName = "updatePageResponse", targetNamespace = "http://www.pengyue.com.cn/", className = "cn.com.pengyue.vo.ws.UpdatePageResponse")
    public boolean updatePage(
        @WebParam(name = "arg0", targetNamespace = "")
        Page arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns cn.com.pengyue.vo.ws.Page
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "savePage", targetNamespace = "http://www.pengyue.com.cn/", className = "cn.com.pengyue.vo.ws.SavePage")
    @ResponseWrapper(localName = "savePageResponse", targetNamespace = "http://www.pengyue.com.cn/", className = "cn.com.pengyue.vo.ws.SavePageResponse")
    public Page savePage(
        @WebParam(name = "arg0", targetNamespace = "")
        Page arg0);

}
