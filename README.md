# Testing-Assignment
Assumptions:
1.Both the files will have same number of lines.Extra lines will be skipped.
2.Both the files are .txt files.
3.All the requests are GET requests.
4.All the responses will be in JSON format.
5.Base URI can be anything.

Steps:
1.Read the .txt file using buffered reader and saved to a list.
2.Extracted response for each entry in the list in JSONObject format. In case api gives a runtime exception saving response as null.
3.Converted JSON to a HashMap.
4.Compared the responses(HashMaps) in case requests are valid.


In order to run the test via commandline go to project directory and run mvn test.
