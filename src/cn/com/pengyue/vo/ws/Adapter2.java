
package cn.com.pengyue.vo.ws;

import java.util.Date;
import javax.xml.bind.annotation.adapters.XmlAdapter;

public class Adapter2
    extends XmlAdapter<String, Date>
{


    public Date unmarshal(String value) {
        return (calendar2Date(javax.xml.bind.DatatypeConverter.parseDate(value)));}private Date calendar2Date(java.util.Calendar c){Date value = c.getTime();return ((value));
    }

    public String marshal(Date value) {
        if (value == null) {
            return null;
        }
        return (javax.xml.bind.DatatypeConverter.printDate(date2Calendar(value)));}private java.util.Calendar date2Calendar(Date date){java.util.Calendar value = java.util.Calendar.getInstance();value.setTime(date);return ((value));
    }

}
