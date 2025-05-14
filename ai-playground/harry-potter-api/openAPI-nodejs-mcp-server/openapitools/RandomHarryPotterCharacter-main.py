import json
import sys
import requests
from BaseUrl import BASE_URL

# DONT CHANGE INPUT PART START
try:
    input_json = input_json
except:
    input_json = json.loads(sys.argv[1])
env = input_json.pop('openv', {})
# DONT CHANGE INPUT PART END

# Make API call to get random Harry Potter character
try:
    response = requests.get(BASE_URL + 'characters/random')
    response.raise_for_status()
    character = response.json()
    
    # Format the output
    result = {
        'fullname': character.get('fullname', 'Unknown'),
        'nickname': character.get('nickname', 'Unknown'),
        'hogwartsHouse': character.get('hogwartsHouse', 'Unknown'),
        'birthDate': character.get('birthDate', 'Unknown'),
        'interpretedBy': character.get('interpretedBy', 'Unknown'),
        'children': character.get('children', []),
        'image': character.get('image', '')
    }
    
    # Create a formatted output string
    output = f"Random Harry Potter Character:\n"
    output += f"Name: {result['fullname']} ('{result['nickname']}')\n"
    output += f"House: {result['hogwartsHouse']}\n"
    output += f"Birth Date: {result['birthDate']}\n"
    output += f"Portrayed by: {result['interpretedBy']}\n"
    
    if result['children']:
        output += f"Children: {', '.join(result['children'])}\n"
    
    output += f"Image: {result['image']}"
    
    print(output)
except requests.exceptions.RequestException as e:
    print(f"Error fetching character data: {str(e)}")
except Exception as e:
    print(f"Unexpected error: {str(e)}")
