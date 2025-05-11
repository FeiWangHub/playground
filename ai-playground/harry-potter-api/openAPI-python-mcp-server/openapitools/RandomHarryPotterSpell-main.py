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

# Make API call to get random spell
try:
    response = requests.get('https://potterapi-fedeperin.vercel.app/spells/random')
    response.raise_for_status()
    spell_data = response.json()
    
    # Format the output
    result = f"Spell: {spell_data['spell']}\nUse: {spell_data['use']}"
    
    # Print the result
    print(result)
except requests.exceptions.RequestException as e:
    print(f"Error fetching spell: {str(e)}")
except (KeyError, json.JSONDecodeError) as e:
    print(f"Error processing spell data: {str(e)}")
