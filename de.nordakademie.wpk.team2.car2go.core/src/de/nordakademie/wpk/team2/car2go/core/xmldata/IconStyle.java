//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.1-b02-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2010.11.24 at 05:13:21 PM MEZ 
//


package de.nordakademie.wpk.team2.car2go.core.xmldata;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.opengis.net/kml/2.2}Icon"/>
 *         &lt;element ref="{http://www.opengis.net/kml/2.2}hotSpot"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "icon",
    "hotSpot"
})
@XmlRootElement(name = "IconStyle")
public class IconStyle {

    @XmlElement(name = "Icon", required = true)
    protected Icon icon;
    @XmlElement(required = true)
    protected HotSpot hotSpot;

    /**
     * Gets the value of the icon property.
     * 
     * @return
     *     possible object is
     *     {@link Icon }
     *     
     */
    public Icon getIcon() {
        return icon;
    }

    /**
     * Sets the value of the icon property.
     * 
     * @param value
     *     allowed object is
     *     {@link Icon }
     *     
     */
    public void setIcon(Icon value) {
        this.icon = value;
    }

    /**
     * Gets the value of the hotSpot property.
     * 
     * @return
     *     possible object is
     *     {@link HotSpot }
     *     
     */
    public HotSpot getHotSpot() {
        return hotSpot;
    }

    /**
     * Sets the value of the hotSpot property.
     * 
     * @param value
     *     allowed object is
     *     {@link HotSpot }
     *     
     */
    public void setHotSpot(HotSpot value) {
        this.hotSpot = value;
    }

}