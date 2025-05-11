import json
import sys
import requests

# DONT CHANGE INPUT PART START
try:
    input_json = input_json
except:
    input_json = json.loads(sys.argv[1])
env = input_json.pop('openv', {})
# DONT CHANGE INPUT PART END

# Make API call to get random Hogwarts house
api_url = "https://potterapi-fedeperin.vercel.app/en/houses/random"

try:
    response = requests.get(api_url)
    response.raise_for_status()
    house_data = response.json()
    
    # Format the output
    result = {
        "house": house_data.get("house"),
        "emoji": house_data.get("emoji"),
        "animal": house_data.get("animal"),
        "colors": house_data.get("colors"),
        "founder": house_data.get("founder")
    }
    
    # Print the result as a JSON string
    print(json.dumps(result))
    
except requests.exceptions.RequestException as e:
    print(json.dumps({"error": f"API request failed: {str(e)}"}))  
except Exception as e:
    print(json.dumps({"error": f"An error occurred: {str(e)}"}))  
