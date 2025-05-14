import json
import sys
import requests
from .BaseUrl import POTTERAPI_BASE_URL

# DONT CHANGE INPUT PART START
try:
    input_json = input_json
except:
    input_json = json.loads(sys.argv[1])
env = input_json.pop('openv', {})
# DONT CHANGE INPUT PART END

# Extract parameters
max_houses = input_json.get('max')
page = input_json.get('page')
index = input_json.get('index')
search = input_json.get('search')

# Prepare query parameters
params = {}
if max_houses is not None:
    params['max'] = max_houses
if page is not None:
    params['page'] = page
if index is not None:
    params['index'] = index
if search is not None:
    params['search'] = search

# Make API request
url = POTTERAPI_BASE_URL + 'houses'
try:
    response = requests.get(url, params=params)
    response.raise_for_status()
    houses_data = response.json()
    
    # Format the output
    result = {
        'houses': houses_data,
        'count': len(houses_data)
    }
    
    # Print the result as a JSON string
    print(json.dumps(result, indent=2))
except requests.exceptions.RequestException as e:
    error_message = f"Error retrieving Hogwarts houses: {str(e)}"
    print(json.dumps({"error": error_message}))
except Exception as e:
    error_message = f"Unexpected error: {str(e)}"
    print(json.dumps({"error": error_message}))
