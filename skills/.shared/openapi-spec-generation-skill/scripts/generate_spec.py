#!/usr/bin/env python3
import sys
import json
import yaml
import argparse

def generate_openapi(input_file, output_file):
    try:
        with open(input_file, 'r', encoding='utf-8') as f:
            data = json.load(f)
            
        openapi_spec = {
            "openapi": "3.0.0",
            "info": {
                "title": data.get("title", "API Documentation"),
                "version": data.get("version", "1.0.0"),
                "description": data.get("description", "Auto-generated OpenAPI spec")
            },
            "servers": [
                {"url": url} for url in data.get("servers", ["http://localhost:3000"])
            ],
            "paths": {}
        }
        
        for path_data in data.get("paths", []):
            path = path_data.get("path", "/")
            method = path_data.get("method", "get").lower()
            summary = path_data.get("summary", "")
            
            if path not in openapi_spec["paths"]:
                openapi_spec["paths"][path] = {}
                
            openapi_spec["paths"][path][method] = {
                "summary": summary,
                "responses": {
                    "200": {
                        "description": "Successful response",
                        "content": {
                            "application/json": {
                                "schema": {
                                    "type": "object",
                                    "properties": {
                                        "message": {"type": "string"}
                                    }
                                }
                            }
                        }
                    }
                }
            }
            
        with open(output_file, 'w', encoding='utf-8') as f:
            yaml.dump(openapi_spec, f, sort_keys=False)
            
        print(f"OpenAPI spec generated at: {output_file}")
        
    except Exception as e:
        print(f"Error: {str(e)}")

if __name__ == "__main__":
    parser = argparse.ArgumentParser(description="Generate OpenAPI 3.0 Spec")
    parser.add_argument("input", help="Input JSON definition file")
    parser.add_argument("output", help="Output YAML file path")
    
    args = parser.parse_args()
    generate_openapi(args.input, args.output)
