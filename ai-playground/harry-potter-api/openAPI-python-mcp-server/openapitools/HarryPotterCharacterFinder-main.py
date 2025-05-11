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

# Extract parameters
search_term = input_json.get('searchTerm')
max_results = input_json.get('maxResults')
page_number = input_json.get('pageNumber')
character_index = input_json.get('characterIndex')

# Prepare API request
base_url = 'https://potterapi-fedeperin.vercel.app/characters'
params = {}

# Add query parameters if provided
if search_term:
    params['search'] = search_term
if max_results is not None:
    params['max'] = max_results
if page_number is not None:
    params['page'] = page_number
if character_index is not None:
    params['index'] = character_index

# Make API request
try:
    response = requests.get(base_url, params=params)
    response.raise_for_status()
    characters = response.json()
    
    # Format the output
    if not characters:
        result = "No characters found matching your criteria."
    elif isinstance(characters, list):
        if len(characters) == 1:
            char = characters[0]
            result = f"Found 1 character:\n\nName: {char.get('fullname', 'Unknown')}\nNickname: {char.get('nickname', 'N/A')}\nHouse: {char.get('hogwartsHouse', 'N/A')}\nBirth Date: {char.get('birthDate', 'N/A')}\nPlayed by: {char.get('interpretedBy', 'N/A')}"
        else:
            result = f"Found {len(characters)} characters:\n\n"
            for i, char in enumerate(characters[:10], 1):
                result += f"{i}. {char.get('fullname', 'Unknown')} - {char.get('hogwartsHouse', 'N/A')}\n"
            
            if len(characters) > 10:
                result += f"\n...and {len(characters) - 10} more characters."
    else:
        # Single character returned as object
        char = characters
        result = f"Character Details:\n\nName: {char.get('fullname', 'Unknown')}\nNickname: {char.get('nickname', 'N/A')}\nHouse: {char.get('hogwartsHouse', 'N/A')}\nBirth Date: {char.get('birthDate', 'N/A')}\nPlayed by: {char.get('interpretedBy', 'N/A')}"
    
    print(result)
except requests.exceptions.RequestException as e:
    print(f"Error fetching character data: {str(e)}")
except Exception as e:
    print(f"An unexpected error occurred: {str(e)}")