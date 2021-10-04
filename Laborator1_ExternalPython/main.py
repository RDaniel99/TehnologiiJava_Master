# importing the requests library
import requests

# api-endpoint
URL = "http://localhost:8080/Laborator1-1.0-SNAPSHOT/hello-servlet?mock=false&value=3&key=test&sync=true"

# defining a params dict for the parameters to be sent to the API
PARAMS = {}

# sending get request and saving the response as response object
r = requests.get(url=URL, params=PARAMS)

# extracting data in json format
print(r)
