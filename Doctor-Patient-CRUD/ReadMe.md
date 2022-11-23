Database: Hospital

Table: Doctor (One-To-One with patient mapping) 

    - dId: Doctor unique ID
    - dName: Doctor name
    - pId: Current Pateint ID, if '0' no patient assigned. 


Table: Patient (One-To-One with doctor mapping)
    
    - pId: Pateint unique ID.
    - pName: Pateint name.
    - dId: Current doctor assigned to patient, if '0' no doctor assigned.
