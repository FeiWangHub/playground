---
name: openapi-spec-generation-skill
description: "Generate OpenAPI 3.0 YAML specifications from simple JSON definitions."
---

# OpenAPI Spec Generation Skill

This skill allows you to quickly scaffold OpenAPI 3.0 documentation by providing a simple JSON definition of your API structure.

## Capabilities

- Convert simplified JSON path definitions to valid OpenAPI 3.0 YAML.
- Automatically scaffolds standard response structures.

## Usage

1. Create a `api_def.json` file:

```json
{
  "title": "My API",
  "version": "1.0.0",
  "paths": [
    {"path": "/users", "method": "get", "summary": "List users"},
    {"path": "/users", "method": "post", "summary": "Create user"}
  ]
}
```

2. Run the generator:

```bash
python3 .shared/openapi-spec-generation-skill/scripts/generate_spec.py api_def.json openapi.yaml
```
