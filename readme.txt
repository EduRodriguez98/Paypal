Execution steps
1. start the rmiregistry (EasyBooking_Server project util folder, double-click on registry.bat)
2. ant build for compilation
3. ant export for creating a jar file to hold the .class dependencies the client needs
4. Copy the payPal.jar on the dist folder and paste it on the EasyBooking_Server project lib folder
5. ant server for launching the server