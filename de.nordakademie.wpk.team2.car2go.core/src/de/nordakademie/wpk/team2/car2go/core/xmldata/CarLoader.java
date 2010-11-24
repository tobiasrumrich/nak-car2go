package de.nordakademie.wpk.team2.car2go.core.xmldata;

import java.net.URL;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class CarLoader {

	
	
	public static void main(String[] args) {

		JAXBContext context;
		try {
			context = JAXBContext.newInstance(Kml.class);
		//Marshaller m = context.createMarshaller();
		//m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		// m.marshal(myPM, handler)

		Unmarshaller um = context.createUnmarshaller();
		Kml kml = (Kml) um.unmarshal(new URL(
				"http://www.car2go.com/api/V1.0/ulm/vacant"));
		
		System.out.println(kml.getDocument().getPlacemark().get(0).getName());
		
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (Exception e) {
		}


	}
}
