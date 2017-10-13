Converter
====================

A simple application which converts sentences from txt file into csv and xml file

To run simple execute mvn command: 'mvn exec:java'  
Default file which should be located in execution directory should be named small.in  
If you want to provide name of file, please add argument: mvn exec:java -DfilePath=path_to_file

To start from command line: java -jar converter.jar input.in

Known issues:
Non ASCII character ’ is replaced by '