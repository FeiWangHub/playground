#!/usr/bin/env python3
import sys
import os
from pypdf import PdfReader

def extract_pdf_info(file_path):
    try:
        reader = PdfReader(file_path)
        
        print(f"--- Document Information ---")
        print(f"File: {os.path.basename(file_path)}")
        print(f"Pages: {len(reader.pages)}")
        
        if reader.metadata:
            print("\n--- Metadata ---")
            for key, value in reader.metadata.items():
                print(f"{key[1:]}: {value}")
                
        print("\n--- Content Extract (First 1000 chars) ---")
        full_text = ""
        for page in reader.pages:
            full_text += page.extract_text() + "\n"
            
        print(full_text[:1000] + ("..." if len(full_text) > 1000 else ""))
        
    except Exception as e:
        print(f"Error reading PDF: {str(e)}")

if __name__ == "__main__":
    if len(sys.argv) < 2:
        print("Usage: python extract_pdf.py <input.pdf>")
        sys.exit(1)
        
    input_file = sys.argv[1]
    
    if not os.path.exists(input_file):
        print(f"Error: Input file {input_file} not found")
        sys.exit(1)
        
    extract_pdf_info(input_file)
