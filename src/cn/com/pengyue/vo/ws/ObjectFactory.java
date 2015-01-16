
package cn.com.pengyue.vo.ws;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the cn.com.pengyue.vo.ws package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _UpdatePageResponse_QNAME = new QName("http://www.pengyue.com.cn/", "updatePageResponse");
    private final static QName _SavePage_QNAME = new QName("http://www.pengyue.com.cn/", "savePage");
    private final static QName _SavePageResponse_QNAME = new QName("http://www.pengyue.com.cn/", "savePageResponse");
    private final static QName _GetRules_QNAME = new QName("http://www.pengyue.com.cn/", "getRules");
    private final static QName _GetRulesResponse_QNAME = new QName("http://www.pengyue.com.cn/", "getRulesResponse");
    private final static QName _UpdatePage_QNAME = new QName("http://www.pengyue.com.cn/", "updatePage");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: cn.com.pengyue.vo.ws
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SavePageResponse }
     * 
     */
    public SavePageResponse createSavePageResponse() {
        return new SavePageResponse();
    }

    /**
     * Create an instance of {@link SavePage }
     * 
     */
    public SavePage createSavePage() {
        return new SavePage();
    }

    /**
     * Create an instance of {@link Page }
     * 
     */
    public Page createPage() {
        return new Page();
    }

    /**
     * Create an instance of {@link UpdatePage }
     * 
     */
    public UpdatePage createUpdatePage() {
        return new UpdatePage();
    }

    /**
     * Create an instance of {@link Rule }
     * 
     */
    public Rule createRule() {
        return new Rule();
    }

    /**
     * Create an instance of {@link GetRules }
     * 
     */
    public GetRules createGetRules() {
        return new GetRules();
    }

    /**
     * Create an instance of {@link GetRulesResponse }
     * 
     */
    public GetRulesResponse createGetRulesResponse() {
        return new GetRulesResponse();
    }

    /**
     * Create an instance of {@link UpdatePageResponse }
     * 
     */
    public UpdatePageResponse createUpdatePageResponse() {
        return new UpdatePageResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdatePageResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.pengyue.com.cn/", name = "updatePageResponse")
    public JAXBElement<UpdatePageResponse> createUpdatePageResponse(UpdatePageResponse value) {
        return new JAXBElement<UpdatePageResponse>(_UpdatePageResponse_QNAME, UpdatePageResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SavePage }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.pengyue.com.cn/", name = "savePage")
    public JAXBElement<SavePage> createSavePage(SavePage value) {
        return new JAXBElement<SavePage>(_SavePage_QNAME, SavePage.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SavePageResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.pengyue.com.cn/", name = "savePageResponse")
    public JAXBElement<SavePageResponse> createSavePageResponse(SavePageResponse value) {
        return new JAXBElement<SavePageResponse>(_SavePageResponse_QNAME, SavePageResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetRules }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.pengyue.com.cn/", name = "getRules")
    public JAXBElement<GetRules> createGetRules(GetRules value) {
        return new JAXBElement<GetRules>(_GetRules_QNAME, GetRules.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetRulesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.pengyue.com.cn/", name = "getRulesResponse")
    public JAXBElement<GetRulesResponse> createGetRulesResponse(GetRulesResponse value) {
        return new JAXBElement<GetRulesResponse>(_GetRulesResponse_QNAME, GetRulesResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdatePage }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.pengyue.com.cn/", name = "updatePage")
    public JAXBElement<UpdatePage> createUpdatePage(UpdatePage value) {
        return new JAXBElement<UpdatePage>(_UpdatePage_QNAME, UpdatePage.class, null, value);
    }

}
