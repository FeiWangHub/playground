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

# Make API call to get random Harry Potter book
try:
    response = requests.get('https://potterapi-fedeperin.vercel.app/en/books/random')
    response.raise_for_status()
    book_data = response.json()
    
    # Format the output
    result = {
        'title': book_data.get('title'),
        'originalTitle': book_data.get('originalTitle'),
        'number': book_data.get('number'),
        'pages': book_data.get('pages'),
        'releaseDate': book_data.get('releaseDate'),
        'description': book_data.get('description'),
        'cover': book_data.get('cover')
    }
    
    print(json.dumps(result, indent=2))
    
except requests.exceptions.RequestException as e:
    error_message = {'error': f'API request failed: {str(e)}'}
    print(json.dumps(error_message))
except Exception as e:
    error_message = {'error': f'An unexpected error occurred: {str(e)}'}
    print(json.dumps(error_message))