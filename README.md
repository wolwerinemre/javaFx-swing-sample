# javaFx-swing-sample

Description:
create a graphical application with java (javaFX, Swing), which will read data on start-up from an input file containing information about refuelings.
User can define data file as an input parameter, for example a command line parameter, dialog etc.
Data file contains information about refuelings for one single year.
All entries in the data file must be positive values, negative values should be validated as errors.
Application must have basic error handling against broken input etc.
User can select the fuel type from a combo box.

Technical requirements:
java version 7 or higher
for testing use JUnit

Example data structure:
0..n rows.

Data format:
fuelName|fuelPrice|fuelAmount|refuellingDate

Legend:
fuelName - a string

fuel fuelPrice - can be with . or , (1.345; 1,345)

fuel fuelAmount - can be with . or , (50.53; 50,53)

refuellingDate - format is dd.mm.yyyy

Example data:
98|1.319|50.56|01.01.2016

98|1.319|45,32|15.01.2016

95|1.21|30.4|02.02.2016

98|1.319|50.30|09.02.2016

98|1.392|45.25|11.03.2016

95|1.319|5.00|01.04.2016

D|1.219|5.00|01.02.2016

E85|0.95|15,12|12.11.2016

 