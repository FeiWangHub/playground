#!/usr/bin/env python3
import csv
import argparse
import sys
import os

def search_prompts(query, max_results=5):
    script_dir = os.path.dirname(os.path.abspath(__file__))
    data_path = os.path.join(script_dir, '../data/prompts.csv')
    
    results = []
    
    try:
        with open(data_path, 'r', encoding='utf-8') as f:
            reader = csv.DictReader(f)
            for row in reader:
                act = row.get('act', '')
                prompt = row.get('prompt', '')
                
                # Simple case-insensitive search
                if query.lower() in act.lower() or query.lower() in prompt.lower():
                    results.append(row)
    except FileNotFoundError:
        print(f"Error: Data file not found at {data_path}")
        return []
        
    return results[:max_results]

def format_output(results, query):
    if not results:
        return f"No prompts found for query: '{query}'"
        
    output = [f"## Awesome ChatGPT Prompts Search Results\n"]
    output.append(f"**Query:** {query} | **Found:** {len(results)}\n")
    
    for i, row in enumerate(results, 1):
        output.append(f"### {i}. {row['act']}")
        output.append(f"**Prompt:**\n> {row['prompt']}\n")
        if row.get('contributor'):
            output.append(f"*Contributor: {row['contributor']}*")
        output.append("")
        
    return "\n".join(output)

if __name__ == "__main__":
    parser = argparse.ArgumentParser(description="Search Awesome ChatGPT Prompts")
    parser.add_argument("query", help="Search keyword")
    parser.add_argument("--max", "-n", type=int, default=5, help="Max results")
    
    args = parser.parse_args()
    
    results = search_prompts(args.query, args.max)
    print(format_output(results, args.query))
