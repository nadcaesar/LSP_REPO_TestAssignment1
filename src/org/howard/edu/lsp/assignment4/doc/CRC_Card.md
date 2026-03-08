package org.howard.edu.lsp.assignment4
# Air Traffic Control System

# Class Name | Job Description 

- TransponderReceiver   | Receives raw aircraft broadcast 
- PacketDecoder         | Decodes packed signal into readable data
- FlightData            | Holds one aircraft's live data snapshot
- AircraftRegistry      | Stores and retrieves all FlightData records
- SituationAnalyzer     | Detects dangerous flight situations
- RadarDisplay          | Draws and renders the radar screen
- DisplayScheduler      | Triggers the 10-second refresh cycle 
- ControllerQuery       | Handles controller requests for aircraft detail
- ControllerWorkstation | The controller's interface for queries and alerts 


## CRC Card | Class: FlightData
**Responsibilities:**
    - Store speed, heading, position, and altitude from the most recent packet
    - Record the timestamp of the most recent update
    - Provide flight attributes to RadarDisplay for rendering
    - Provide flight attributes to SituationAnalyzer for hazard analysis
    - Provide flight attributes to ControllerQuery for detail display
  
**Collaborators**:
  - RadarDisplay
  - SituationAnalyzer
  - ControllerQuery

**Assumptions**:
  - None


## CRC Card | Class: TransponderReceiver 
**Responsibilities:**
    - Receive raw broadcasts from aircraft transponders
    - Validate incoming signals and discard corrupted packets
    - Pass validated packets to PacketDecoder for unpacking
  
**Collaborators**:
  - PacketDecoder

**Assumptions**:
  - None


## CRC Card | Class: PacketDecoder 
**Responsibilities:**
  - Unpack packet into flight data fields 
  - Parse aircraft type, aircraft id, altitude, speed, heading, and position
  - Forward decoded FlightDataRecords to AircraftRegistry for storage
  
**Collaborators**:
  - TransponderReceiver
  - FlightData
  - AircraftRegistry

**Assumptions**:
  - Fixed packet format


## CRC Card | Class: AircraftRegistry 
**Responsibilities:**
  - Store and index FlightData Records keyed by aircraft identifier 
  - Update an existing aircraft record when new packet arrives 
  - Add new aircraft entries when unknown identifiers are received 
  - Remove old entries for aircrafts that dont broadcast within the timeout period
  - Provide retrieval of single aircraft record on query
  - Supply full set of current aircraft records to requesting classes 
  
**Collaborators**:
  - FlightData
  - RadarDisplay
  - SituationAnalyzer
  - ControllerQuery

**Assumptions**:
  - None


## CRC Card | Class: SituationAnalyzer 
**Responsibilities:**
  - Retrieve all current FlightData records from AircraftRegistry
  - Compute projected flight paths for each aircraft over a short time horizon
  - Detect potential collision conflicts (aircraft on converging paths within safe separation)
  - Detect altitude constraint violations
  - Produce and update a list of alerts records for confirmed hazards
  - Produce an alert when a new danger is detected

  
**Collaborators**:
  - AircraftRegistry
  - FlightData
  - ControllerWorkstation

**Assumptions**:
  - None



## CRC Card | Class: RadarDisplay 
**Responsibilities:**
  - It retrieves current records from AircraftRegistry every 10 seconds
  - Highlights aircrafts flagged as dangerous from SituationAnalyzer
  - Renders aircraft identify, position, and flight labels on screen 
  - Refresh the display triggered from DisplayScheduler 
  
**Collaborators**:
  - AircraftRegistry
  - DisplayScheduler
  - SituationAnalyzer 

**Assumptions**:
  - None




## CRC Card | Class: DisplayScheduler 
**Responsibilities:**
  - Holds the 10-second periodic timer
  - Triggers RadarDisplay to refresh 
  - Triggers SituationAnalyzer to re-evaluate hazards
  
**Collaborators**:
  - RadarDisplay
  - SituationAnalyzer
 

**Assumptions**:
  - Reliable clock


## CRC Card | Class: ControllerQuery 
**Responsibilities:**
  - Accepts query about aircraft details from ControllerWorkstation
  - Retrieve matching FlightData from AircraftRegistry 
  - Displays on ControllerWorkstation
  - Display a not found message to ControllerWorkstation if aircraft is unknonw
  
**Collaborators**:
  - FlightData
  - AircraftRegistry
  - ControllerWorkstation


**Assumptions**:
  - Queries are initiated by controller clicking on aircraft on radar screen



## CRC Card | Class: ControllerWorkstation 
**Responsibilities:**
  - Capture controller aircraft selection input
  - Forward aircraft query request to ControllerQuery
  - Display flight details returned by ControllerQuery
  - Display active danger alerts received from SituationAnalyzer
  - Present RadarDisplay to controller as part of interface
  - Displays danger alerts in a dedicated panel 
  
**Collaborators**:
  - RadarDisplay
  - SituationAnalyzer
  - ControllerQuery

**Assumptions**:
  - Workstation integrates radar, query panel, and alert panel in one UI

