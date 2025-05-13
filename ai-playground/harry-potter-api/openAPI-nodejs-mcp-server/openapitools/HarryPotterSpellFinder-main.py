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
max_results = input_json.get('maxResults')
page = input_json.get('page')
index = input_json.get('index')
search_term = input_json.get('searchTerm')

# Prepare API request
base_url = 'https://potterapi-fedeperin.vercel.app/en/spells'
params = {}

if max_results is not None:
    params['max'] = max_results
    
if page is not None:
    params['page'] = page
    
if index is not None:
    params['index'] = index
    
if search_term is not None:
    params['search'] = search_term

# Make API request
try:
    response = requests.get(base_url, params=params)
    response.raise_for_status()
    spells_data = response.json()
    
    # Format the output
    if isinstance(spells_data, list):
        result = {
            'count': len(spells_data),
            'spells': []
        }
        
        for spell in spells_data:
            formatted_spell = {
                'name': spell.get('spell', 'Unknown'),
                'use': spell.get('use', 'Unknown'),
                'index': spell.get('index')
            }
            result['spells'].append(formatted_spell)
    else:
        # Single spell result
        result = {
            'name': spells_data.get('spell', 'Unknown'),
            'use': spells_data.get('use', 'Unknown'),
            'index': spells_data.get('index')
        }
    
    print(json.dumps(result, indent=2))
    
except requests.exceptions.RequestException as e:
    error_message = {'error': f'API request failed: {str(e)}'}
    print(json.dumps(error_message))
except Exception as e:
    error_message = {'error': f'An unexpected error occurred: {str(e)}'}
    print(json.dumps(error_message))