/**
 * 
 */
package com.sample;

import java.util.ArrayList;
import java.util.List;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.project.droolsdemo.propertyinsurance.model.Insurer;
import com.project.droolsdemo.propertyinsurance.model.PropertyDescription;
import com.project.droolsdemo.propertyinsurance.model.PropertyDetails;
import com.project.droolsdemo.propertyinsurance.model.PropertyInsuranceDetails;

/**
 * @author sivasaiv
 *
 */
public class TestRules {

	public static final void main(String[] args) {
        try {
            // load up the knowledge base
	        KieServices ks = KieServices.Factory.get();
    	    KieContainer kContainer = ks.getKieClasspathContainer();
        	KieSession kSession = kContainer.newKieSession();

        	PropertyDetails  details = new PropertyDetails();
        	details.setCountry("india");
        	
        	
        	PropertyDescription description = new PropertyDescription();
        	description.setBuiltIn("1991");
        	description.setElevators(3);
        	description.setFloors(2);
        	description.setIsClaimMade("no");
        	description.setIsElectricUpdated("yes");
        	description.setIsRoofUpdated("yes");
        	description.setNearestCoast(11);
        	description.setNearestFireStation(9);
        	description.setPropertyType("office");
        	description.setPropertyValue("3000");
        	
        	
        	
        	PropertyInsuranceDetails insuranceDetails = new PropertyInsuranceDetails();
        	insuranceDetails.setPropertyDetails(details);
        	insuranceDetails.setPropertyDescription(description);
           
        	List<Insurer> insurer = new ArrayList<Insurer>();
        	
            kSession.insert(insuranceDetails);
            kSession.insert(insurer);
            kSession.getAgenda().getAgendaGroup("default").setFocus();
            kSession.fireAllRules();
           
            insurer.forEach(System.out:: println);
            
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}
