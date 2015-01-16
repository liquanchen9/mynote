
package cn.com.pengyue.vo.ws;

import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for rule complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="rule">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="addDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="cateId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="enRuleExclude" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="enRuleInclude" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="rule" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ruleEnName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ruleExclude" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ruleInclude" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "rule", propOrder = {
    "addDate",
    "cateId",
    "enRuleExclude",
    "enRuleInclude",
    "id",
    "rule",
    "ruleEnName",
    "ruleExclude",
    "ruleInclude",
    "status"
})
public class Rule {

    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Date addDate;
    protected Integer cateId;
    protected String enRuleExclude;
    protected String enRuleInclude;
    protected Integer id;
    protected String rule;
    protected String ruleEnName;
    protected String ruleExclude;
    protected String ruleInclude;
    protected Integer status;

    /**
     * Gets the value of the addDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getAddDate() {
        return addDate;
    }

    /**
     * Sets the value of the addDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddDate(Date value) {
        this.addDate = value;
    }

    /**
     * Gets the value of the cateId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCateId() {
        return cateId;
    }

    /**
     * Sets the value of the cateId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCateId(Integer value) {
        this.cateId = value;
    }

    /**
     * Gets the value of the enRuleExclude property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEnRuleExclude() {
        return enRuleExclude;
    }

    /**
     * Sets the value of the enRuleExclude property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEnRuleExclude(String value) {
        this.enRuleExclude = value;
    }

    /**
     * Gets the value of the enRuleInclude property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEnRuleInclude() {
        return enRuleInclude;
    }

    /**
     * Sets the value of the enRuleInclude property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEnRuleInclude(String value) {
        this.enRuleInclude = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setId(Integer value) {
        this.id = value;
    }

    /**
     * Gets the value of the rule property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRule() {
        return rule;
    }

    /**
     * Sets the value of the rule property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRule(String value) {
        this.rule = value;
    }

    /**
     * Gets the value of the ruleEnName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRuleEnName() {
        return ruleEnName;
    }

    /**
     * Sets the value of the ruleEnName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRuleEnName(String value) {
        this.ruleEnName = value;
    }

    /**
     * Gets the value of the ruleExclude property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRuleExclude() {
        return ruleExclude;
    }

    /**
     * Sets the value of the ruleExclude property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRuleExclude(String value) {
        this.ruleExclude = value;
    }

    /**
     * Gets the value of the ruleInclude property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRuleInclude() {
        return ruleInclude;
    }

    /**
     * Sets the value of the ruleInclude property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRuleInclude(String value) {
        this.ruleInclude = value;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setStatus(Integer value) {
        this.status = value;
    }

}
