# OODesignFinal2021
Ericsson Object Oriented Design Pattern course

Exam Exercise – Wireless Network Management Systems 
1.	The Market Requirements (MRs)
A Telco Company,  Company-B,  has the following MRs to comply with:
  1.1.	Wires Network Management Systems
  Deploy and Manage a Wireless Network System that is made up of the following systems:
  •	A Network Management system to manage radio units.
  •	A Network Alarm Monitoring System.
  •	A Carrier Management system.
  1.2.	Data Integrity
  All  the Management and Monitoring services should be able to handle multiple admins (telco operators) performing management and monitoring services info without any risk of data inconsistencies.
  1.3.	Carrier Signal Requirements
  1.3.1.	Carrier Data Components
   A Carrier signal must have the following data components:
  	carrierId   // Integer – Unique Identifier
  	RFPorts  //   Valid range(A to H)
  	FrequencyBand   // Details in the following sections
  	TransmittingPower   //Double 

  1.3.2.	Carrier APIs
   A Carrier signal has the following APIs:
  	The needed set of setters and getters.

  1.3.3.	Carrier Management APIs
  	createLteCarrier (<set of applicable attributes>);
  	createWcdmaCarrier (<set of applicable attributes>);

  1.3.4.	LTE Carrier Frequencies
  LTE Frequency Bands:
  •	LTE_BAND_1  = "1920 MHz"
  •	LTE_BAND_2  = "1850 MHz"
  •	LTE_BAND_3  = "1710 MHz"
  •	LTE_BAND_4  = "1755 MHz"
  •	LTE_BAND_5  = "824 MHz"
  •	LTE_BAND_6  = "830 MHz"
  •	LTE_BAND_7  = "2500 MHz"
  •	LTE_ BAND_8 = "880 MHz"
      In the context of the exam, an LTE Carrier requires 4 RF ports.		
  1.3.5.	WCDMA Carrier Frequencies
  WCDMA Frequency Bands:
  •	WCDMA_BAND_1  = "2,100 MHz"
  •	WCDMA_BAND_2  = "1,900 MHz" 
  •	WCDMA_BAND_3  = "1,800 MHz" 
  •	WCDMA_BAND_4  = "2,100 / 1,700 MHz"
  •	WCDMA_BAND_5  = "850 MHz - for the U.S."
  •	WCDMA_BAND_6  = "850 MHz - for Japan"
  •	WCDMA_BAND_7  = "2,500 MHz"
  •	WCDMA_BAND_8  = "900 MHz"

  In the context of the exam, a WCDMA Carrier requires 2 RF ports.

  1.3.6.	RF Ports
  Supported RF Ports on all Rus:
  •	RF_0 = "A"
  •	RF_1 = "B"
  •	RF_2 = "C"
  •	RF_3 = "D"
  •	RF_4 = "E"
  •	RF_5 = "F"
  •	RF_6 = "G"
  •	RF_7 = "H"

  1.4.	Radio Access Technology (RAT) Requirements
  A Radio Access Technology or (RAT) is the underlying physical connection method for a radio-based communication network. 
  Many modern mobile phones support several RATs in one device such as:
  •	Bluetooth  	= “Wireless Communication Protocol over short distances”
  •	Wi-Fi         	= “Wireless Fidelity” 
  •	LTE_FDD   	= "Long Term Evolution Frequency-Division Duplexing "
  •	GSM          	= "Global System for Mobile Communications"
  •	WCDMA   	= "Wideband Code Division Multiple Access"

In the context of the exam:
•	Only the LTE_FDD and the WCDMA Rat Types are supported.
•	An Ru can support only one RAT type (no mixed mode Rus).

  1.5.	Radio Unit (RU) Vendors
  In the context of the exam, the following are the affiliated vendors:
  •	ERICSSON 	= "Ericsson AB Sweden"
  •	NOKIA 	= "Nokia OY Finland"
  Both vendors support both the LTE_FDD and the WCDMA RAT types.
  1.6.	Radio Unit Alarm Status Levels
  In the context of the exam, an Ru can have a max of one alarm at any moment in time. The following are the supported  Alarm status levels and flavors:
  •	CRITICAL
  •	MAJOR
  •	MINOR
  •	NO_ALARM
  •	CRITICAL_ACKNOWLEDGED
  •	MAJOR_ACKNOWLEDGED
  •	MINOR_ACKNOWLEDGED

  1.7.	Radio Unit Requirements 
  In telecommunications management, a Managed Object (MO) is a resource within the telecommunications environment that may be managed through the use of operation, administration, maintenance, and provisioning (OAMP) application protocols.
  In the context of this exam, the Managed Radio Unit (RU) is the resource to be managed by our wireless network protocols.
  1.7.1.	Managed RU Requirements
  A Managed RU is the one which must have a number of data components and support a set of APIs which the network management system uses to manage.
  1.7.1.1.	Managed RU Data Components
  A Managed Radio Unit in the network is required to have the following data components:
  	Ru State 
  	Ip Address   //String - unique Id
  	Radio Unit Name (String)
  	Radio Vendor
  	RatType
  	Carrier(s)
  	Alarm Status
  In the context of this exam, a Managed RU can  support one or more carriers of the same RAT type.

  1.7.1.2.	Managed RU APIs
  Every Managed Ru is required to support the following set of APIs:
  	setup();
  	activate();
  	deactivate();
  	release();
  	setupCarrier(Carrier);
  	signalScaling();
  	modifyCarrier(Integer, Band Frequency); // Carrier Id
  	removeCarrier(Integer);    // Carrier Id
  	removeAllCarriers();
  	postActivation();
  	performSelfDiagnostics();
  	acknlowledgeAlarm();
  	The needed set of setters and getters.


  1.7.2.	Managed RU States
  In the context of the exam, a Managed RU can be in one of the following states. More details on the state-machine diagram:
  •	IDLE = “Idle”  
  •	Deactivated = “Busy-Deactivated”
  •	Activated = “Busy-Activated”

 



Note: In the context of the exam, when a Managed RU is added to the network is will be in the “IDLE” State automatically.

  1.7.3.	Vendor Specific APIs
  In the context of this exam, each RU Vendor has its own set of APIs which are expected to be incompatible with the other vendors.
  1.7.3.1.	Ericsson RUs Data Components
  The following set of Data Components are common to all Ericsson Rus.
  	Ip Address   //String - unique Id
  	Radio Unit Name (String)
  	Radio Vendor  // constant
  	Rat Type           // constant
  	Carrier(s)
  	Alarm Status

  1.7.3.2.	Ericsson LTE RUs Specific APIs
  	setupLteEricsson();
  	activateLteEricsson();
  	deactivateLteEricsson();
  	releaseLteEricsson();
  	setupCarrierLteEricsson(Carrier);
  	signalScalingLteEricsson();
  	modifyCarrierLteEricsson(Integer, BandFrequency);  // Carrier Id
  	postActivationLteEricsson();
  	performSelfDiagnosticsLteEricsson();
  	removeCarrierLteEricsson(Integer);    // Carrier Id
  	removeAllCarriersLteEricsson();

In the context of the exam, each method should have a print statement indicating its invocation plus any other applicable tasks.
For example, to simulate the Signal Scaling task on an Ericsson LTE RU:

public void signalScalingLteEricsson(){
	System.out.println("Performing SignalScaling Procedure on: " + this.toString());
	 Other steps (if applicable) should follow the print statement.
}

  1.7.3.3.	Ericsson WCDMA RUs Specific APIs
  	setupWcdmaEricsson();
  	activateWcdmaEricsson();
  	deactivateWcdmaEricsson();
  	releaseWcdmaEricsson();
  	setupCarrierWcdmaEricsson(Carrier);  
  	modifyCarrierWcdmaEricsson(Integer, Band Frequency) ; // Carrier Id
  	postActivationWcdmaEricsson();
  	performSelfDiagnosticsWcdmaEricsson();
  	removeCarrierWcdmaEricsson(Integer);    // Carrier Id
  	removeAllCarriersWcdmaEricsson();
  1.7.3.4.	Nokia(LTE) Data Components
  The following set of Data Components are common to all Nokia Rus.
  	Ip Address   //String - unique Id
  	Radio Unit Name (String)
  	Radio Vendor  // constant
  	Rat Type           // constant
  	Carrier(s)
  	Alarm Status

  1.7.3.5.	Nokia(LTE) Specific APIs
  	setupLteNokia();
  	activateLteNokia();
  	deactivateLteNokia();
  	releaseLteNokia();
  	setupCarrierLteNokia(Carrier);
  	signalScalingLteNokia();
  	modifyCarrierLteNokia(Integer, Band Frequency);  // Carrier Id
  	postActivationLteNokia();
  	performSelfDiagnosticsLteNokia();
  	removeCarrierLteNokia(Integer);   // Carrier Id
  	removeAllCarriersLteNokia();


  1.7.3.6.	 Nokia(WCDMA) Specific APIs
  	setupWcdmaNokia();
  	activateWcdmaNokia();
  	deactivateWcdmaNokia();
  	releaseWcdmaNokia();
  	setupCarrierWcdmaNokia(Carrier); 
  	modifyCarrierWcdmaNokia(Integer, BandFrequency); // Carrier Id
  	postActivationWcdmaNokia();
  	performSelfDiagnosticsWcdmaNokia();
  	removeCarrierWcdmaNokia(Integer);    // Carrier Id
  	removeAllCarriersWcdmaNokia();






  1.8.	The Network Management System
  Company-B has an existing network management system that has proven to be very reliable, and the company intends to continue to use it. This means that the existing network management APIs cannot be modified and all the Vendor Rus to be added to the network must be managed via these APIs. 
  Also, the existing network management system is expected to able to support new Ru Vendor with minor updates.
  The Network Management system must support and adhere to the operational Procedures and APIs detailed in the following section.
  In the Network Management system, an RU is a Managed RU.
  1.8.1.	The Managed RUs Commissioning Procedure
  The order of the steps of the Commissioning Procedure is given below and cannot be changed across all different Rus:
  commissionRu(String)   // Ru Ip Address
  	setupRU(String);  // Ru Ip Address
  	activateRU(String); // Ru Ip Address
  	postActivation(String);   // Ru Ip Address
  	performSingalScaling(String)); * // Ru Ip Address
  	performSelfDiagnostics(String);  // Ru Ip Address

* SignalScaling  on WCDMA Rus is not needed since it is implicitly performed in the postActivation phase and should be skipped. However, on LTE Rus is a must.

  1.8.2.	The Managed RUs De-Commissioning Procedure
  The order of the steps of the Decommissioning Procedure given below cannot be changed across all different Rus:
  decommissionRu(String )  // Ru Ip Address
  	deactivateRU(String);   // Ru Ip Address
  	removeAllCarriersOnRU(String);  // Ru Ip Address
  	releaseRU(String);  // Ru Ip Address





  1.8.3.	Network Management Required APIs
  The existing Network Management App supports the following APIs:
  	commissionRu(String)   // Ru Ip Address
  	decommissionRu(String)   // Ru Ip Address
  	addRadioUnit(Ru);    // Radio Unit
  	removeRadioUnit (String); // Ru Ip Address
  	setupRU(String); // Ru Ip Address
  	releaseRU(String);   // Ru Ip Address
  	activateRU(String);  // Ru Ip Address
  	deactivateRU(String ruIpAddr);
  	setupCarrierOnRU(String, Carrier);  // Ru Ip Address
  	modifyCarrierOnRU(String, Integer, Band Frequency ); // Ru Ip Address, CarrierId
  	removeCarrierOnRU(String, Integer);   //Ru Ip Address, CarrierId
  	removeAllCarriersOnRU(String);   // Ru Ip Address
  	singalScalingOnRU(String);  // Ru Ip Address
  	postActivation(String);   // Ru Ip Address
  	performSelfDiagnostics(String);  // Ru Ip Address
  	listNetworkInventory();
  	listRUsByStandard(Rat Type );
  	listRUsByState(Ru State );
  	listRUsByBand(Band Frequency );
  	listRadioUnitDetails(String);  // Ru Ip Address
  	setAlarmOnRu(String, Alarm Status); // Ru Ip Address
  	getNetworkAlarms();
  	acknowledgeAlarm(String); // Ru Ip Address

  1.9.	Alarm  Monitoring System
  The Alarm Monitoring servers for Company-B are located on a remote site on dedicated servers.
  1.9.1.	Alarm  Monitoring Required APIs
  The Alarm Monitoring system must support the following APIs:
  	getNetAlarms();
  	acknowledgeAlarmOnRu(String);  // Ru Ip Address
  Ideally, the Alarm Monitoring system should poll the nework periodically and get the alarms on all managed Rus.
  In the context of the exam, and to simplify things, retrieving the alarms and other actions would be realized via a menu-driven client.	
  Also, the network alarms report should be similar to the following:
  Network Alarm Status
  127.12.34.56 <RU Name> MAJOR
  127.12.34.58 <RU Name> CRITICAL_ACKNOWLEDGED
  127.12.34.57 <RU Name> NO_ALARM
  1.10.	Demo Requirements:
  Here are the demo requirements:
  	Network Management Client: A menu-driven implementation of the Network Management APIs stated in section 1.8.3.
  	Alarm Monitoring Client: A menu-driven implementation of the Alarm Monitoring APIs as detailed in section 1.9.1.
  	Each client is expected to run in its own window/terminal/console.
  	Detailed demo scenarios will be communicated later.
