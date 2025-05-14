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

# Extract parameters
max_books = input_json.get('max')
page = input_json.get('page')
index = input_json.get('index')
search_term = input_json.get('search')

# Prepare query parameters
params = {}
if max_books is not None:
    params['max'] = max_books
if page is not None:
    params['page'] = page
if index is not None:
    params['index'] = index
if search_term is not None:
    params['search'] = search_term

# Make API request
url = BASE_URL + 'books'
try:
    response = requests.get(url, params=params)
    response.raise_for_status()
    books = response.json()
    
    # Format the output
    if isinstance(books, list):
        result = {
        'count': len(books),
        'books': books
        }
    else:
        # If a single book is returned (when using index parameter)
        result = {'book': books}
    
    # Print the formatted result
    print(json.dumps(result, indent=2))
except requests.exceptions.RequestException as e:
    error_message = {'error': f'API request failed: {str(e)}'}
    print(json.dumps(error_message))
except json.JSONDecodeError:
    error_message = {'error': 'Failed to parse API response'}
    print(json.dumps(error_message))
except Exception as e:
    error_message = {'error': f'An unexpected error occurred: {str(e)}'}
    print(json.dumps(error_message))